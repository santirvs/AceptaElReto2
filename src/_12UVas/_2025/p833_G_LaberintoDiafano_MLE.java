package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Recorrido en anchura mirando de no repetir las celdas visitadas
 *      La lista de visitas provoca MLE
 *
 * v2.- Se ha hecho recorrido en profundidad, pero TLE
 *
 * v3.- Recupero el recorrido en anchura, pero permito repetir las celdas visitadas
 *      Pero solo entraré en la celda si mejora la cantidad de comida, en caso contrario
 *      ese recorrido nunca llevará al óptimo.
 *      Relajar la condición me lleva a un bucle infinito.
 *
 * v4.-
 */

import java.util.*;


public class p833_G_LaberintoDiafano_MLE {

    static int Tx, Ty;
    static int[][] a;
    static boolean[][] vis;
    static long mejor;
    static long total;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            Tx = sc.nextInt();
            Ty = sc.nextInt();
            if (Tx == 0 && Ty == 0) break;

            a = new int[Ty][Tx];
            vis = new boolean[Ty][Tx];
            total = 0;

            for (int i = 0; i < Ty; i++)
                for (int j = 0; j < Tx; j++) {
                    a[i][j] = sc.nextInt();
                    total += a[i][j];
                }

            mejor = 0;
            vis[0][0]=true;
            dfs(0, 0, a[0][0], total - a[0][0]);
            System.out.println(mejor);
        }
    }

    static void dfs(int y, int x, long suma, long restante) {
        if (suma + restante <= mejor) return; // poda

        if (y == Ty - 1 && x == Tx - 1) {
            mejor = Math.max(mejor, suma);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < Ty && nx >= 0 && nx < Tx && !vis[ny][nx]) {
                vis[ny][nx] = true;
                dfs(ny, nx, suma + a[ny][nx], restante - a[ny][nx]);
                vis[ny][nx] = false;
            }
        }
    }
}

