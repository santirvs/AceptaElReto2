package _24en23._2025;

import java.io.*;

/**
 *
 * @author santi
 * @date 02/07/2025
 */

// v1. Parece ser un claro ConvexHull  --> MLE
// v2. Cambiar el algoritmo a uno más eficiente para evitar MLE
    // Eliminar la clase Point y usar tipos básicos y arrays (int) para las coordenadas.
    // Implementar el algoritmo de Andrew's monotone chain usando índices.
    // MLE
// v3. Cambio de estrategia para evitar MLE.
    // No consigo salir del MLE, así que recupero la primera versión y la paso a C++  --> AC

public class p818_UnPradoParaMisOvejas {

        static final int MAX = 300000;
        static int[] x = new int[MAX];
        static int[] y = new int[MAX];
        static int[] index = new int[MAX];
        static int[] stack = new int[MAX];
        static int pivot;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                int n = Integer.parseInt(line);
                if (n == 0) break;

                String[] tokens;
                do {
                    line = br.readLine();
                } while (line != null && line.trim().isEmpty());
                tokens = line.trim().split(" ");

                for (int i = 0; i < n; i++) {
                    x[i] = Integer.parseInt(tokens[2 * i]);
                    y[i] = Integer.parseInt(tokens[2 * i + 1]);
                    index[i] = i;
                }

                pivot = 0;
                for (int i = 1; i < n; i++) {
                    if (y[i] < y[pivot] || (y[i] == y[pivot] && x[i] < x[pivot])) {
                        pivot = i;
                    }
                }

                // swap pivot to index 0
                swap(x, 0, pivot);
                swap(y, 0, pivot);
                index[0] = 0;
                for (int i = 1; i < n; i++) index[i] = i;

                quickSort(index, 1, n - 1);

                // Graham scan
                int top = 0;
                stack[top++] = 0;
                for (int i = 1; i < n; i++) {
                    while (top >= 2 && cross(stack[top - 2], stack[top - 1], index[i]) <= 0) top--;
                    stack[top++] = index[i];
                }

                // Compute area
                long area2 = 0;
                for (int i = 0; i < top; i++) {
                    int a = stack[i];
                    int b = stack[(i + 1) % top];
                    area2 += (long) x[a] * y[b] - (long) x[b] * y[a];
                }
                area2 = Math.abs(area2);
                if ((area2 & 1) == 0) {
                    System.out.println(area2 / 2);
                } else {
                    System.out.println((area2 / 2) + ".5");
                }
            }
        }

        static long cross(int i, int j, int k) {
            long dx1 = x[j] - x[i];
            long dy1 = y[j] - y[i];
            long dx2 = x[k] - x[i];
            long dy2 = y[k] - y[i];
            return dx1 * dy2 - dy1 * dx2;
        }

        static void swap(int[] arr, int i, int j) {
            int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }

        static boolean angleLess(int i, int j) {
            long c = cross(0, i, j);
            if (c != 0) return c > 0;
            long dx1 = x[i] - x[0], dy1 = y[i] - y[0];
            long dx2 = x[j] - x[0], dy2 = y[j] - y[0];
            return dx1 * dx1 + dy1 * dy1 < dx2 * dx2 + dy2 * dy2;
        }

        static void quickSort(int[] arr, int left, int right) {
            if (left >= right) return;
            int pivotIdx = (left + right) >>> 1;
            int pivotVal = arr[pivotIdx];
            int i = left, j = right;
            while (i <= j) {
                while (angleLess(arr[i], pivotVal)) i++;
                while (angleLess(pivotVal, arr[j])) j--;
                if (i <= j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i++; j--;
                }
            }
            quickSort(arr, left, j);
            quickSort(arr, i, right);
        }
    }
