import java.util.*;

/**
 *
 * @author santi
 *
 * v1 - Recuperado un envío anterior de 28/12/2023 con PE
 *
 * v2 - Análisis.
 *      El nombre de la serie tiene un espacio al inicio. Hago trim()
 *      Sigue el PE

 *  v3 - Adapto desde https://github.com/Jorgitou98/Acepta-el-Reto/blob/master/Soluciones/AceptaElReto700SeriesMasVistas.cpp
 *      AC
 *
 *  v4 - Comparo con mi original
 *       4.1 --> Cambio BufferedReader por Scanner
 *       4.2 --> Cambio nextLine() por nextInt() + nextLine();  --> PE!!!
 *       4.3 --> Recupero v4.1
 *       4.4 --> Cambio nextLine() de integer por nextInt() + nextLine()
 *       4.5 --> Cambio parse de Int + String por nextInt() + nextLine().trim()  --> PE!!
 *       4.6 --> Recupero v4.4
 *       4.7 --> El problema es que el nombre de la serie ¡¡¡¡puede empezar por espacio!!!!
 *       AC
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class p700_LasSeriesMasVistas {

    public static class Serie {
        public String nombre;
        public int duracion;
        Serie(String nom, int durada) {
            nombre = nom;
            duracion = durada;
        }
    }

    //Comparador de series
    public static class SortSerie implements Comparator<Serie> {
        // Ordena descendentemente por minutos,
        // en caso de empate, ordena ascendentemente por nombre
        @Override
        public int compare(Serie a, Serie b) {
            int compareByMinutes = a.duracion > b.duracion ? -1 : a.duracion == b.duracion ? 0 : 1;
            if (compareByMinutes == 0) {
                return a.nombre.compareTo(b.nombre);
            } else {
                return compareByMinutes;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numSeries = scan.nextInt();
        scan.nextLine();

        while (numSeries > 0) {
            //Declaro el diccionario serie / minutos
            Map<String, Integer> minutosPorSerie = new HashMap<>();

            //Leer los minutos que se ha visionado cada serie
            for (int i = 0; i < numSeries; i++) {
                //El nombre de la serie puede empezar por espacio!!!!
                String line = scan.nextLine();
                int firstSpace = line.indexOf(' ');
                int minutos = Integer.parseInt(line.substring(0, firstSpace));
                String serie = line.substring(firstSpace + 1);
                if (minutosPorSerie.containsKey(serie)) {
                    //Añadir minutos a la serie
                    minutosPorSerie.put(serie, minutosPorSerie.get(serie) + minutos);
                } else {
                    //Primer visionado de la serie
                    minutosPorSerie.put(serie, minutos);
                }
            }

            //Convierte el diccionario a listas
            List<String> listaNombres = new ArrayList<String>(minutosPorSerie.keySet());
            List<Integer> listaMinutos = new ArrayList<Integer>(minutosPorSerie.values());
            List<Serie> listaSeries = new ArrayList<Serie>();

            //Convierte la lista a List<Serie> y elimina aquellas de menos de 30 minutos de visionado
            for (int i = 0; i < listaNombres.size(); i++) {
                if (listaMinutos.get(i) >= 30) {
                    Serie s = new Serie(listaNombres.get(i), listaMinutos.get(i));
                    listaSeries.add(s);
                }
            }

            //Ordena la lista por minutos
            Collections.sort(listaSeries,new SortSerie());

            //Muestra las tres primeras series (si las hay)
            for (int i = 0; i < 3 && i < listaSeries.size(); i++) {
                System.out.println(listaSeries.get(i).nombre);
            }

            //Separador de casos
            System.out.println("---");

            //Lee el siguiente caso
            numSeries = scan.nextInt();
            scan.nextLine();
        }
    }
}