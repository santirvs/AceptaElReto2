package _24en23._2025;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author santi
 * @date 17/06/2025
 */

// v1.- Usa una List pero da MLE  (1000 Longs * 8 bytes --> 8Kb,  ML: 4096 Kb ????)

// v2.- 1000 és el máximo de ricos, pero no de población!!!
// Nos guardamos como máximo los n más ricos  --> TLE

// v3.- No podemos ordenar continuamente!!!
// Usar Map<Riqueza, Cantidad>  para clasificar a la población por su riqueza y,
    // como pueden repetirse, la cantidad de población con esa riqueza  --> AC

public class p802_IndiceDeGini {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int numIndividuos = scan.nextInt();
            int numRicos = scan.nextInt();

            // Condición de salida
            if (numRicos == 0 && numIndividuos == 0) break;

            //Variables de control
            long sumaTotal = 0L;
            long sumaRicos = 0L;
            long totalRicos = 0L;
            TreeMap<Long,Integer> ahorros = new TreeMap<>();

            //Lectura de datos
            for (int i = 0; i < numIndividuos; i++) {
                Long cantidad = scan.nextLong();
                sumaTotal += cantidad;
                //Añade o crea un nuevo rico con esa cantidad
                if (ahorros.containsKey(cantidad)) {
                    ahorros.put(cantidad, ahorros.get(cantidad) + 1);
                } else {
                    ahorros.put(cantidad, 1);
                }
                //Control del número de ricos, no puede exceder de numRicos
                if (totalRicos == numRicos) {
                    //Eliminar al más pobre
                    Map.Entry<Long,Integer> pobre = ahorros.firstEntry();
                    if (pobre.getValue() == 1) {
                        ahorros.remove(pobre.getKey());
                    } else {
                        ahorros.put(pobre.getKey(), pobre.getValue()-1);
                    }
                    sumaRicos -= pobre.getKey();
                } else {
                    totalRicos += 1;
                }

            }

            //A la suma de los ricos eliminados le sumo la suma Total
            //Para que me quede el total de la suma de los más ricos
            sumaRicos += sumaTotal;


            //Mostrar resultados
            System.out.println(sumaTotal + " " + sumaRicos);

        }
    }
}

