package _24en23._2025;

import java.io.*;
import java.util.*;

/**
 *
 * @author santi
 * @date 24/06/2025
 */

// v1.- Usar programación dinámica en la que se van a buscar dos secuencias de longitud máxima
    // Una LIS (Longest Increasing Subsequence) y otra LDS (Longest Decreasing Subsequence)
    // Una se cargará en el tren por la parte delantera y la otra por la trasera
    // La solución final será la suma de las longitudes de ambas secuencias, menos 1 ya
    // que el coche central se cuenta en ambas secuencias.  --> TLE

// v2.- Encontrar la subsecuencia más larga (en orden de llegada)
// que puede insertarse en un deque (cola doble) por los extremos,
// y dejar el tren estrictamente decreciente al final.

public class p810_ElMozoDeEstacion_WA {

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                int n = Integer.parseInt(br.readLine().trim());
                if (n == 0) break;
                int[] cars = new int[n];
                String[] parts = br.readLine().trim().split(" ");
                for (int i = 0; i < n; i++) {
                    cars[i] = Integer.parseInt(parts[i]);
                }
                System.out.println(longestDecreasingSubsequenceLength(cars));
            }
        }

        static int longestDecreasingSubsequenceLength(int[] arr) {
            ArrayList<Integer> tail = new ArrayList<>();
            for (int x : arr) {
                // Buscamos posición para x en tail, que está manteniendo una secuencia creciente para el array invertido
                int pos = binarySearch(tail, -x);
                if (pos == tail.size()) {
                    tail.add(-x);
                } else {
                    tail.set(pos, -x);
                }
            }
            return tail.size();
        }

        static int binarySearch(ArrayList<Integer> tail, int key) {
            int lo = 0, hi = tail.size() - 1;
            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;
                if (tail.get(mid) < key) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }
    }
