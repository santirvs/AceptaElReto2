package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Calcular las diferencias entre Constantino y Cansino para cada tramo
 *      Calcular cuantas secuencias hay en positivo
 *      TLE
 *
 * v2.-
 *
 *
 */
import java.util.Scanner;


import java.util.TreeMap;

public class p838_L_LaCronoescaladaDeSanSilvestre_TLE {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int numSecciones = sc.nextInt();
            while (numSecciones > 0) {

                //Leer el tiempo de Constantino
                int tiempoConstantino = sc.nextInt();
                //Leer el tiempo de Cansino en cada sección
                long[] tiemposCansino = new long[numSecciones];
                for (int i = 0; i < numSecciones; i++) {
                    tiemposCansino[i] = sc.nextLong();
                }

                // Transformar a array de diferencias
                // D[i] = tiempoConstantino - tiemposCansino[i]
                long[] D = new long[numSecciones];
                for (int i = 0; i < numSecciones; i++) {
                    D[i] = tiempoConstantino - tiemposCansino[i];
                }

                // Distancias acumulados
                long[] pref = new long[numSecciones + 1];
                pref[0] = 0;
                for (int i = 1; i <= numSecciones; i++) {
                    pref[i] = pref[i - 1] + D[i - 1];
                }

                //Contar subarrays positivos
                long count = countPositiveSubarrays(pref);
                System.out.println(count);


                //Siguiente caso
                numSecciones = sc.nextInt();
            }
        }

        private static long countPositiveSubarrays(long[] pref) {
            long total = 0;

            // TreeMap para mantener prefijos vistos y cuántas veces aparecen
            TreeMap<Long, Integer> map = new TreeMap<Long, Integer>();

            // Insertamos pref[0] = 0 inicialmente
            map.put(pref[0], 1);

            for (int i = 1; i < pref.length; i++) {
                long curr = pref[i];

                // Todos los prefijos anteriores < curr
                // headMap(curr, false) devuelve los menores estrictos
                long sumSmaller = 0;
                for (int val : map.headMap(curr, false).values()) {
                    sumSmaller += val;
                }

                total += sumSmaller;

                // Insertar el prefijo actual en el TreeMap
                if (map.containsKey(curr)) {
                    map.put(curr, map.get(curr) + 1);
                } else {
                    map.put(curr, 1);
                }
            }

            return total;
        }
    }
