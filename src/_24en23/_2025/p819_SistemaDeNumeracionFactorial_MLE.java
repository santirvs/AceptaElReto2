package _24en23._2025;

import java.io.*;

/**
 *
 * @author santi
 * @date 03/07/2025
 */


// v1. MLE --> Pasar a C++  --> AC


public class p819_SistemaDeNumeracionFactorial_MLE {

        static int N;
        static int[] BIT;

        // BIT suma parcial hasta i
        static int sum(int i) {
            int res = 0;
            while (i > 0) {
                res += BIT[i];
                i -= i & -i;
            }
            return res;
        }

        // Añade val a la posición i
        static void add(int i, int val) {
            while (i <= N) {
                BIT[i] += val;
                i += i & -i;
            }
        }

        // Encuentra el k-ésimo elemento (0-indexado)
        static int findKth(int k) {
            int lo = 1, hi = N, res = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (sum(mid) > k) {
                    res = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            return res;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            int T = Integer.parseInt(in.readLine());

            for (int t = 0; t < T; t++) {
                N = Integer.parseInt(in.readLine());
                String[] tokens = in.readLine().split(" ");
                int[] digits = new int[N];
                for (int i = 0; i < N; i++) {
                    digits[i] = Integer.parseInt(tokens[i]);
                }

                BIT = new int[N + 2];
                // Inicializar BIT: cada número de 1 a N está disponible
                for (int i = 1; i <= N; i++) {
                    add(i, 1);
                }

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < N; i++) {
                    int idx = digits[i];
                    int num = findKth(idx); // Encontrar el idx-ésimo número disponible
                    sb.append(num).append(i == N - 1 ? "\n" : " ");
                    add(num, -1); // Marcar como usado
                }
                out.write(sb.toString());
            }

            out.flush();
        }
    }
