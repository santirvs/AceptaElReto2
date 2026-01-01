package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Guardar la lista de visitados no ha parecido una buena solución, ya que lleva a
 *      MLE
 *
 * v2.- Cambio de planteamiento: dfs
 *     TLE
 *
 * v3.- Miro de optimizarlo.
 *      Ojo que el tiempo límite es 1 seg!!!
 *      El número de filas y columnas no parece grande: 100 x 100 = 10.000 casillas
 *      Agilizo la lectura de datos: En vez de 10000 scan.nextInt() hago 100 scan.nextLine() + split
 *      Sigue el TLE
 *      La búsqueda en profundidad tarda demasiado.
 *      ¿Se puede mejorar?
 *
 *
 *
 *
 *
 */

import java.util.Scanner;


public class p833_G_LaberintoDiafano_TLE {

    static int columnas, filas;
    static int[][] comida;
    static boolean[][] visitado;
    static int maxComida;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Leer las columnas y filas de la matriz
        columnas = sc.nextInt();
        filas = sc.nextInt();

        while (! (columnas ==0 && filas ==0)) {

            sc.nextLine();  //Se come el salto de línea después de leer un Int
            //matrices auxilixares para dp
            comida = new int[filas][columnas];
            visitado = new boolean[filas][columnas];
            maxComida = 0;

            //Lectura de la matriz de datos
            //Agilizar la lectura. Hacerlo por líneas y split
            for (int y = 0; y < filas; y++) {
                String[] linea = sc.nextLine().split(" ");
                for (int x = 0; x < columnas; x++) {
                    comida[y][x] = Integer.parseInt(linea[x]);
                }
            }

            dfs(0, 0, 0);
            System.out.println(maxComida);

            columnas = sc.nextInt();
            filas = sc.nextInt();
        }
    }

    //Busca en profundidad (DFS)
    static void dfs(int y, int x, int acumulado) {
        // Marcar la celda como visitada
        visitado[y][x] = true;
        acumulado += comida[y][x];

        // Si llegamos a la meta
        if (y == filas - 1 && x == columnas - 1) {
            //Actualiza el mejor resultado si es necesario
            if (acumulado > maxComida) maxComida = acumulado;
        } else {
            // Movimientos: arriba, abajo, izquierda, derecha
            int[] dy = {-1, 1, 0, 0};
            int[] dx = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny < filas && nx >= 0 && nx < columnas && !visitado[ny][nx]) {
                    dfs(ny, nx, acumulado);
                }
            }
        }

        // Desmarcar la celda al retroceder
        visitado[y][x] = false;
    }
}





