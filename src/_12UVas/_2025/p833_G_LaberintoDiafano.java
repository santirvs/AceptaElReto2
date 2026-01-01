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
 *      TLE
 *      Agilizo aún más con FastReader()
 *      TLE
 *      No hay manera humana de resolver esto!!!  DFS da TLE y BFS da MLE
 *
 * v4.- Cambio de estrategia para dar con una respuesta en orden lineal.
 *      Suponemos el tablero como si fuera uno de ajedrez con casillas alternas blancas y negras.
 *      En este tipo de tableros, siempre exista un camino que recorra todas las casillas
 *      Con lo que bastaría con sumar todas las casillas
 *      Pero hay una restricción y es que se debe entrar por la casilla superior izquierda
 *      y se debe salir por la casilla inferior derecha.
 *      Y aquí hay que tener en cuenta otra propiedad de estos tableros y es que si el número de celdas es par
 *      se empieza y termina por colores distintos y si es impar se empieza y se acaba por el mismo color.
 *      Las esquinas de entrada y salida están en el mismo color si Tx + Ty es par
 *      y en color distinto si Tx + Ty es impar
 *      Por lo tanto, si no es posible entrar y salir por las casillas que tocan, simplemente hay que saltarse una
 *      casillas (la de menor valor) del color sobrante.
 *
 *      AC!!! Después de 23 intentos!!!
 *
 */

import java.io.IOException;
import java.io.InputStream;

public class p833_G_LaberintoDiafano {

    static int columnas, filas;
    static int[][] comida;
    static boolean[][] visitado;
    static int maxComida;

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 20];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        // Lee un entero (positivo o negativo)
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        // Lee un carácter no blanco
        char nextChar() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');
            return (char) c;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner();

        int columnas = sc.nextInt();
        int filas = sc.nextInt();

        while (!(columnas == 0 && filas == 0)) {

            long sumaTotalComida = 0;

            long minBlanco = Long.MAX_VALUE;
            long minNegro = Long.MAX_VALUE;

            for (int y = 0; y < filas; y++) {
                for (int x = 0; x < columnas; x++) {
                    long comidaCelda = sc.nextInt();
                    sumaTotalComida += comidaCelda;

                    //Determina el color de la celda y mantiene el mínimo de cada color
                    if (((x + y) & 1) == 0) {
                        minBlanco = Math.min(minBlanco, comidaCelda);
                    } else {
                        minNegro = Math.min(minNegro, comidaCelda);
                    }
                }
            }

            //total de celdas
            int totalCeldas = columnas * filas;

            //Color de la casilla de entrada y de salida
            boolean inicioNegro = ((0 + 0) & 1) == 1;
            boolean finNegro = (((columnas - 1) + (filas - 1)) & 1) == 1;

            //Mira a ver si es posible un camino hamiltoniano
            boolean hamiltoniano;
            if ((totalCeldas & 1) == 0) {
                hamiltoniano = inicioNegro != finNegro;
            } else {
                hamiltoniano = inicioNegro == finNegro;
            }

            long resultado;
            if (hamiltoniano) {
                //Si es hamiltoniano, el resultado es la suma de todas las celdas
                resultado = sumaTotalComida;
            } else {
                // Hay que eliminar una celda del color "sobrante"
                // El camino empieza en (0,0), así que ese color tiene una más
                if (((0 + 0) & 1) == 0) {
                    resultado = sumaTotalComida - minNegro;
                } else {
                    resultado = sumaTotalComida - minBlanco;
                }
            }

            System.out.println(resultado);

            //Siguiente caso
            columnas = sc.nextInt();
            filas = sc.nextInt();
        }
    }
}
