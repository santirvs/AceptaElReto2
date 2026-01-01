package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Más que las cartas en sí, nos interesa identificar qué dígitos contienen
 *      De hecho las cartas 10, 100 y 1000 a efectos del juego son exactamente iguales.
 *      Codificar las cartas con bitmask y almacenarlas en un HashMap
 *      Buscar parejas donde el AND de los bitmask no sea 0
 *      DA MLE!!
 *
 * v2.- Optimizar
 *      - Leer toda la línea no parece buena idea: 300.000 x 10 dígitos (9+espacio) --> 3MB
 *        con el límite siendo 4MB, no queda margen para almacenar los 300.000 integers (1,2 MB)
 *        Por lo tanto habrá que almacenar a medida que se leen
 *        AC!
 *
 */

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class p834_H_UnoParaMuchos {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        //Lectura del número de casos
        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {
            int numCartas = scan.nextInt();
            //Mapa de frecuencias
            HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();

            //Lectura de los valores
            for (int carta=0; carta<numCartas; carta++) {
                //Leer como String
                String num = scan.next();
                //Codificar la carta con los dígitos que contiene (bitMask)
                int mask = 0;
                for (int i = 0; i < num.length(); i++) {
                    mask |= 1 << (num.charAt(i) - '1');
                }

                //Guardar la carta codificada en el diccionario para almacenar
                // las frecuencias de aparición de cada una de ellas
                if (freq.containsKey(mask)) {
                    freq.put(mask, freq.get(mask) + 1);
                } else {
                    freq.put(mask, 1);
                }
            }

            //Pasar el diccionario a un array de claves
            int[] claves = new int[freq.size()];
            int idx = 0;
            for (int key : freq.keySet()) claves[idx++] = key;

            //Buscar las parejas
            long totalParejas = 0;
            //Para cada clave
            for (int i = 0; i < claves.length; i++) {
                //Se obtiene la frecuencia de esa clave (carta codificada)
                int cantidadCartas = freq.get(claves[i]);
                //Se le suma la cantidad de parejas que pueden hacer entre ellas
                totalParejas += (long) cantidadCartas * (cantidadCartas - 1) / 2;

                //Se mira el resto de claves (a partir de la actual para no repetir)
                for (int j = i + 1; j < claves.length; j++) {
                    //Si coindiciden en algún dígito  (AND de máscaras)
                    if ((claves[i] & claves[j]) != 0) {
                        //Se le suma las parejas que hacen, una de aquí (i) con la otra de allá (j) (producto cartesiano)
                        //las que se forman entre las de allá (j) ya se tendrán en cuenta más adelante
                        totalParejas += (long) cantidadCartas * freq.get(claves[j]);
                    }
                }
            }

            System.out.println(totalParejas);
        }

    }
}
