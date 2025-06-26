package _24en23._2025;

import java.io.*;
import java.util.*;

/**
 *
 * @author santi
 * @date 24/06/2025
 */

// v0-Empiezo de nuevo después varios intentos infructuosos combinando LIS

// v1-Procesar los puntos de los coches segun llegan al tren
    // Mantener una lista de los coches que se han cargado en el tren
    // Si el coche es más pesado que el último de la lista, se añade al final de la lista
    // Si el coche es más ligero que el primero de la lista, se añade al principio de la lista
    // Si el coche es más pesado que el primero de la lista y más ligero que el último,
    // se busca su posición en la lista (que estará ordenada)
    // y se reemplaza el coche en esa posición.
    // Si está entre el primer coche y el segundo, se posiciona al principio
    // y sino se reemplaza el coche en la posición encontrada
    // ---->  MLE (Memory Limit Exceeded) por usar una lista de enteros para almacenar los coches en el tren

    // Ideas...  ¿se necesita toda la lista de coches del tren?
    //  Si me guardo el primero, segundo, penúltimo y último coche del tren,
    //  puedo saber si el coche que llega es más pesado o más ligero que ellos y si se encuentra entre el primero y segundo
    // Sigue dando MLE. ¿Es necesario guardar los coches del tren?

    // Ideas.... se pueden ir procesando los coches a medida que se leen?
    // Salvo el MLE, pero tengo WA! No consigo generar un caso de prueba que lo provoque

    // Recupero la versión de LIS a partir de las pistas:
    // 1. En la solución óptima que estamos buscando, habrá un conjunto inicial (quizá vacío) de coches que no suban al tren.
    // 2. Una vez que sube el primer tren de la solución óptima, los coches que suban por la cabecera seguirán una secuencia creciente y los que suban por el final una decreciente.
    // 3. En la cola de espera, tras el primer coche de la solución óptima los coches que suben por la cabecera son los que forman la subsecuencia creciente más larga y los que suben por el final la decreciente más larga.
    // 4. Existen algoritmos muy estudiados para calcular la subsecuencia creciente más larga: es el problema del longest increasing subsequence (LIS). El problema con la subsecuencia decreciente (LDS) se resuelve de igual forma.
    // 5. En la solución óptima el primer coche en subir al tren es aquel cuya suma de las longitudes de la LIS y LDS que comienzan en él es máxima.
    // 6. Los algoritmos habituales de LIS y LDS determinan las subsecuencias más largas que terminan en cada elemento del vector. Si queremos saber las longitudes más largas de las que empiezan en cada elemento, basta con hacer el cálculo sobre el vector dado la vuelta (y cambiar LIS por LDS y viceversa).
    // 7. El número de coches esperando es muy grande; necesitarás un algoritmo de LIS/LDS eficiente, que no sea cuadrático.

public class p810_ElMozoDeEstacion {


        // Busca la posición de inserción con binary search
        private static int lowerBound(int[] dp, int length, int val) {
            int low = 0, high = length;
            while (low < high) {
                int mid = (low + high) / 2;
                if (dp[mid] < val) low = mid + 1;
                else high = mid;
            }
            return low;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("0")) break;
                if (line.isEmpty()) continue;

                int n = Integer.parseInt(line);
                int[] weights = new int[n];
                String[] parts = br.readLine().trim().split(" ");
                for (int i = 0; i < n; i++) {
                    weights[i] = Integer.parseInt(parts[i]);
                }

                // Calcula LIS desde el final hacia el inicio
                int[] dpLIS = new int[n];
                int[] lisFrom = new int[n];
                int lenLIS = 0;
                for (int i = n - 1; i >= 0; i--) {
                    int pos = lowerBound(dpLIS, lenLIS, weights[i]);
                    dpLIS[pos] = weights[i];
                    if (pos == lenLIS) lenLIS++;
                    lisFrom[i] = pos;
                }

                // Calcula LDS desde el final hacia el inicio (equivale a LIS en -weights)
                int[] dpLDS = new int[n];
                int[] ldsFrom = new int[n];
                int lenLDS = 0;
                for (int i = n - 1; i >= 0; i--) {
                    int val = -weights[i];
                    int pos = lowerBound(dpLDS, lenLDS, val);
                    dpLDS[pos] = val;
                    if (pos == lenLDS) lenLDS++;
                    ldsFrom[i] = pos;
                }

                int maxTrain = 0;
                for (int i = 0; i < n; i++) {
                    int total = 1 + lisFrom[i] + ldsFrom[i];
                    if (total > maxTrain) maxTrain = total;
                }

                System.out.println(maxTrain);
            }
        }
    }
