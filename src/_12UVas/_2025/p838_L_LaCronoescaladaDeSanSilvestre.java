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
 * v2.- Usar un FenwickTree para contar las secuencias
 *      MLE
 *
 * v3.- Optimizar
 */

import java.util.*;

public class p838_L_LaCronoescaladaDeSanSilvestre {

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

                // Discretización
                long[] sorted = pref.clone();
                Arrays.sort(sorted);
                int[] idx = new int[numSecciones + 1];
                int rank = 1;
                for (int i = 0; i <= numSecciones; i++) {
                    if (i == 0 || sorted[i] != sorted[i - 1]) rank++;
                }
                // Mapear prefijos a índices discretizados
                for (int i = 0; i <= numSecciones; i++) {
                    idx[i] = Arrays.binarySearch(sorted, pref[i]) + 1; // 1-based para BIT
                }

                long count = countPositiveSubarrays(idx);
                System.out.println(count);


                //Siguiente caso
                numSecciones = sc.nextInt();
            }
        }

    private static long countPositiveSubarrays(int[] idx) {
        int n = idx.length;
        int maxIdx = 0;
        for (int i = 0; i < n; i++) if (idx[i] > maxIdx) maxIdx = idx[i];

        FenwickTree bit = new FenwickTree(maxIdx + 2);
        long total = 0;

        for (int i = 0; i < n; i++) {
            int position = idx[i];
            // Contar prefijos anteriores < pref[i]
            total += bit.query(position - 1);
            // Actualizar BIT con prefijo actual
            bit.update(position, 1);
        }

        return total;
    }

    static class FenwickTree {
        private long[] tree;

        public FenwickTree(int size) {
            tree = new long[size];
        }

        public void update(int i, long delta) {
            while (i < tree.length) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        public long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
