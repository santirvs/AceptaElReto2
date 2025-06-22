package _24en23._2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 22/06/2025
 */

// v1.- Backtracking para encontrar todas las combinaciones de torres y reinas en un tablero de máximo 8x8
    //  que maximicen la puntuación. --> TLE
// v2.  Realiza una poda para evitar combinaciones que no puedan superar la puntuación máxima actual.
    //  Ordena las celdas por valor descendente para priorizar las más valiosas.


public class p807_TorresYReinas {
    static int N, Q, R;
    static int[][] valores;
    static char[][] tablero;
    static int maxScore;

    static ArrayList<Cell> sortedCells;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Leer hasta que no haya más casos
        while (sc.hasNextInt()) {
            // Leer el número de torres y reinas
            Q = sc.nextInt();
            R = sc.nextInt();
            N = Q + R;
            // Inicializar los valores y el tablero
            valores = new int[N][N];
            tablero = new char[N][N];
            // Leer los valores de las celdas
            for (int i = 0; i < N; i++) {
                // Inicializar el tablero con '.'
                Arrays.fill(tablero[i], '.');
                for (int j = 0; j < N; j++) {
                    // Leer el valor de cada celda
                    valores[i][j] = sc.nextInt();
                }
            }

            // Inicializar y ordenar celdas por valor descendente
            sortedCells = new ArrayList<Cell>();
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    sortedCells.add(new Cell(i, j, valores[i][j]));

            Collections.sort(sortedCells, new Comparator<Cell>() {
                public int compare(Cell a, Cell b) {
                    return b.value - a.value;
                }
            });

            maxScore = 0;
            backtrack(0, Q, R, 0);
            System.out.println(maxScore);
        }
    }

    static void backtrack(int index, int qLeft, int rLeft, int currentScore) {
        // Caso base: si no quedan torres ni reinas por colocar
        if (qLeft == 0 && rLeft == 0) {
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }
            return;
        }

        // Si hemos procesado todas las celdas, no hay más combinaciones
        if (index >= sortedCells.size()) return;

        // Poda por valor máximo alcanzable
        int remaining = qLeft + rLeft;
        int potentialMax = currentScore;
        for (int i = index; i < sortedCells.size() && remaining > 0; i++, remaining--) {
            potentialMax += sortedCells.get(i).value;
        }
        if (potentialMax <= maxScore) return;

        // Procesar la celda actual
        Cell cell = sortedCells.get(index);
        int x = cell.x, y = cell.y;

        // Intentar colocar una torre o una reina en la celda actual (si está vacía)
        if (tablero[x][y] == '.') {
            if (qLeft > 0 && isSafe(x, y, 'Q')) {
                // Colocar una reina
                tablero[x][y] = 'Q';
                // Llamada recursiva para colocar la siguiente torre o reina
                backtrack(index + 1, qLeft - 1, rLeft, currentScore + valores[x][y]);
                // Deshacer la colocación de la reina
                tablero[x][y] = '.';
            }

            if (rLeft > 0 && isSafe(x, y, 'R')) {
                // Colocar una torre
                tablero[x][y] = 'R';
                // Llamada recursiva para colocar la siguiente torre o reina
                backtrack(index + 1, qLeft, rLeft - 1, currentScore + valores[x][y]);
                // Deshacer la colocación de la torre
                tablero[x][y] = '.';
            }
        }

        // Opción de no colocar nada en la celda actual
        backtrack(index + 1, qLeft, rLeft, currentScore);
    }

    // Comprobar si es seguro colocar una torre o una reina en la celda (x, y)
    static boolean isSafe(int x, int y, char type) {
        // Comprobar si la celda está dentro del tablero y esta vacía
        for (int i = 0; i < N; i++) {
            if (tablero[x][i] != '.') return false;
            if (tablero[i][y] != '.') return false;
        }

        // Comprobar diagonales si es una reina
        if (type == 'Q') {
            int[] dx = {-1, -1, 1, 1};
            int[] dy = {-1, 1, -1, 1};
            for (int d = 0; d < 4; d++) {
                int i = x + dx[d], j = y + dy[d];
                while (i >= 0 && i < N && j >= 0 && j < N) {
                    if (tablero[i][j] != '.') return false;
                    i += dx[d];
                    j += dy[d];
                }
            }
        }

        // Comprobar si ya está atacada por alguna torre o reina existente
        return !attackedByExisting(x, y);
    }

    // Comprobar si la celda (x, y) está siendo atacada por alguna torre o reina ya colocada
    static boolean attackedByExisting(int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char p = tablero[i][j];
                if (p == '.') continue;

                if (i == x || j == y) return true;
                if (p == 'Q' && Math.abs(i - x) == Math.abs(j - y)) return true;
            }
        }
        return false;
    }

    // Clase para representar una celda con su posición y valor
    static class Cell {
        int x, y, value;

        Cell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
