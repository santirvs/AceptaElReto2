package _24en23._2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author santi
 * @date 02/07/2025
 */

// v1. Parece ser un claro ConvexHull  --> MLE
// v2. Cambiar el algoritmo a uno más eficiente para evitar MLE
    // Eliminar la clase Point y usar tipos básicos y arrays (int) para las coordenadas.
    // Implementar el algoritmo de Andrew's monotone chain usando índices.
public class p818_UnPradoParaMisOvejas_MLE2 {


    static final int MAX = 300000;
    static int[] x = new int[MAX];
    static int[] y = new int[MAX];
    static Integer[] idx = new Integer[MAX];
    static int[] hull = new int[MAX * 2];
    static int n;

    static int cross(int o, int a, int b) {
        long dx1 = x[a] - x[o];
        long dy1 = y[a] - y[o];
        long dx2 = x[b] - x[o];
        long dy2 = y[b] - y[o];
        long val = dx1 * dy2 - dy1 * dx2;
        return Long.compare(val, 0);
    }

    static void sortPoints() {
        for (int i = 0; i < n; i++) idx[i] = i;

        Arrays.sort(idx, 0, n, new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                if (x[i] != x[j]) return x[i] - x[j];
                return y[i] - y[j];
            }
        });

        int[] tx = new int[n];
        int[] ty = new int[n];
        for (int i = 0; i < n; i++) {
            tx[i] = x[idx[i]];
            ty[i] = y[idx[i]];
        }
        System.arraycopy(tx, 0, x, 0, n);
        System.arraycopy(ty, 0, y, 0, n);
    }

    static int convexHull() {
        sortPoints();
        int k = 0;

        for (int i = 0; i < n; i++) {
            while (k >= 2 && cross(hull[k - 2], hull[k - 1], i) <= 0) k--;
            hull[k++] = i;
        }

        int t = k;
        for (int i = n - 2; i >= 0; i--) {
            while (k > t && cross(hull[k - 2], hull[k - 1], i) <= 0) k--;
            hull[k++] = i;
        }

        return k - 1; // eliminar duplicado
    }

    static long area2(int[] hull, int len) {
        long res = 0;
        for (int i = 0; i < len; i++) {
            int a = hull[i];
            int b = hull[(i + 1) % len];
            res += (long) x[a] * y[b] - (long) x[b] * y[a];
        }
        return Math.abs(res);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0) continue;
            n = Integer.parseInt(line);
            if (n == 0) break;

            String[] tokens;
            while (true) {
                line = br.readLine();
                if (line != null && line.trim().length() > 0) break;
            }
            tokens = line.trim().split(" ");
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(tokens[2 * i]);
                y[i] = Integer.parseInt(tokens[2 * i + 1]);
            }

            int len = convexHull();
            long a2 = area2(hull, len);
            if ((a2 & 1) == 0) {
                System.out.println(a2 / 2);
            } else {
                System.out.println((a2 / 2) + ".5");
            }
        }
    }
}
