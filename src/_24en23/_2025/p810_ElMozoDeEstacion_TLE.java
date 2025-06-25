package _24en23._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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


public class p810_ElMozoDeEstacion_TLE {


    //Calcula la LIS de un array
    public static int[] lis(int[] a) {
        int n = a.length;
        int[] lis = new int[n];
        ArrayList<Integer> seq = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int val = -a[i];  // Convertir a problema de subsecuencia decreciente
            int pos = Collections.binarySearch(seq, val);
            if (pos < 0) pos = -pos - 1;

            if (pos == seq.size()) {
                seq.add(val);
            } else {
                seq.set(pos, val);
            }
            lis[i] = pos + 1;
        }
        return lis;
    }

    public static void main(String[] args) throws IOException {
        // Usar BufferedReader para una entrada más rápida
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // 1 línea pueden ser hasta 500.000 números enteros (4 bytes), por lo tanto 2MB < 16MB de límite de memoria

        while ((line = br.readLine()) != null) {
            // Leer el número de coches en el tren. Si llega un 0, se termina la entrada
            int n = Integer.parseInt(line.trim());
            if (n == 0) break;

            //Leer los pesos de los coches en la cola y almacenarlos en el array pesos
            int[] pesos = new int[n];
            int[] reversed = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int peso = Integer.parseInt(st.nextToken());
                pesos[i] = peso;
                reversed[n - 1 - i] = peso; // Guardar el peso original para la subsecuencia decreciente
            }

            // Calcular la Longest Increasing Subsequence (LIS) de los pesos
            int[] left = lis(pesos);

            // Calcular la Longest Decreasing Subsequence (LDS) de los pesos
            int[] rightReversed = lis(reversed);
            // Invertir el resultado de la LDS para obtener la secuencia decreciente correcta
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                right[i] = rightReversed[n - 1 - i];
            }

            //Buscar la suma máxima de left y right en todo el array
            int max = 0;
            for (int i = 0; i < n; i++) {
                int total = left[i] + right[i] - 1;
                if (total > max) {
                    max = total;
                }
            }

            System.out.println(max);
        }
    }
}





