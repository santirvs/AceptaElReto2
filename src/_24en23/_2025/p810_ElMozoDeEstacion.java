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

// A esto, yo le añado:
// 8. No se pueden usar listas de enteros para almacenar los coches en el tren, ya que se superaría el límite de memoria.
// Se deben usar un array de tipo básico int. Pero sigue dando MLE.
    // Si queremos usar LIS y LDS, debemos cargar el conjunto completo de coches
    // Si no se carga por completo, no se puede calcular la LIS y LDS de forma correcta, sólo de forma aproximada

// Llego a este código que me supera mis casos de prueba pero genera MLE.
// Desesperado, pruebo la versión en C++ --> AC !!!!

public class p810_ElMozoDeEstacion {


        public static int[] computeLISFromRight(int[] arr) {
            int n = arr.length;
            int[] lis = new int[n];
            int[] tails = new int[n];
            int len = 0;

            for (int i = n - 1; i >= 0; i--) {
                int pos = Arrays.binarySearch(tails, 0, len, arr[i]);
                if (pos < 0) pos = -(pos + 1);
                tails[pos] = arr[i];
                if (pos == len) len++;
                lis[i] = pos + 1;
            }

            return lis;
        }

        public static int[] computeLDSFromRight(int[] arr) {
            int n = arr.length;
            int[] lds = new int[n];
            int[] tails = new int[n];
            int len = 0;

            for (int i = n - 1; i >= 0; i--) {
                int val = -arr[i]; // Para transformar LDS en LIS
                int pos = Arrays.binarySearch(tails, 0, len, val);
                if (pos < 0) pos = -(pos + 1);
                tails[pos] = val;
                if (pos == len) len++;
                lds[i] = pos + 1;
            }

            return lds;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while (!(line = in.readLine()).equals("0")) {
                int n = Integer.parseInt(line);
                int[] arr = new int[n];
                String[] parts = in.readLine().split(" ");
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(parts[i]);
                }

                int[] lis = computeLISFromRight(arr);
                int[] lds = computeLDSFromRight(arr);

                int max = 0;
                for (int i = 0; i < n; i++) {
                    int total = lis[i] + lds[i] - 1; // combinamos tren por delante y por detrás
                    if (total > max) max = total;
                }

                System.out.println(max);
            }
        }
    }
