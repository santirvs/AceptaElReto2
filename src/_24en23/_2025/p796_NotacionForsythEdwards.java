package _24en23._2025;

import java.util.Scanner;

public class p796_NotacionForsythEdwards {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        scan.nextLine();

        while (numCasos-- > 0) {
            String[] linea = scan.nextLine().split("/");
            int[][] tablero = new int[8][8];

            // Cargar el tablero desde la notación Forsyth-Edwards
            for (int i = 0; i < 8; i++) {
                String parte = linea[i];
                int j = 0;
                for (char c : parte.toCharArray()) {
                    if (Character.isDigit(c)) {
                        j += c - '0'; // Avanzar varias columnas
                    } else {
                        switch (c) {
                            case 'r':
                            case 'R': tablero[i][j++] = 10; // Torre
                                      break;
                            case 'n':
                            case 'N': tablero[i][j++] = 20; // Caballo
                                        break;
                            case 'b':
                            case 'B': tablero[i][j++] = 30; // Alfil
                                        break;
                            case 'q':
                            case 'Q' : tablero[i][j++] = 40; // Reina
                                        break;
                            case 'k':
                            case 'K': tablero[i][j++] = 50; // Rey
                                         break;
                            case 'p' :  tablero[i][j++] = 60; // Peón negro
                                        break;
                            case 'P' : tablero[i][j++] = 70; // Peón blanco
                                        break;
                            default : tablero[i][j++] = 0; // Vacío o error
                                        break;
                        };
                    }
                }
            }

            // Determinar casillas alcanzables
            for (int fila=0; fila<8; fila++) {
                for (int columna=0; columna<8; columna++) {
                    if (tablero[fila][columna] == 0 || tablero[fila][columna] == 1) continue; // Casilla vacía o alcanzada

                    int pieza = tablero[fila][columna];
                    switch (pieza) {
                        case 10: // Torre
                            marcarTorre(tablero, fila, columna);
                            break;
                        case 20: // Caballo
                            marcarCaballo(tablero, fila, columna);
                            break;
                        case 30: // Alfil
                            marcarAlfil(tablero, fila, columna);
                            break;
                        case 40: // Reina
                            marcarReina(tablero, fila, columna);
                            break;
                        case 50: // Rey
                            marcarRey(tablero, fila, columna);
                            break;
                        case 60: // Peón negro
                            marcarPeon(tablero, fila, columna, false);
                            break;
                        case 70: // Peón blanco
                            marcarPeon(tablero, fila, columna, true);
                            break;
                    }
                }
            }

            //Contar casillas no alcanzables
            int numCasillas = 0;
            for (int fila=0; fila<8; fila++) {
                for (int columna = 0; columna < 8; columna++) {
                    if (tablero[fila][columna] == 0) { // Casilla vacía
                        numCasillas++;
                    }
                }
            }
            System.out.println(numCasillas);
        }

    }

    private static void marcarPeon(int[][] tablero, int fila, int columna, boolean esBlanco) {
        // El movimiento de avance del peón no se considera un ataque, sino una casilla alcanzable y no debe contarse.
        if (esBlanco) {
            if (fila > 0) {
                // Marcar las casillas atacables por el peón blanco
                if (columna < 7) { // Ataque diagonal derecha
                    if (tablero[fila - 1][columna + 1] == 0) // Casilla alcanzable
                        tablero[fila - 1][columna + 1] = 1; // Marcar como alcanzable
                }
                if (columna > 0) { // Ataque diagonal izquierda
                    if (tablero[fila - 1][columna - 1] == 0) // Casilla alcanzable
                        tablero[fila - 1][columna - 1] = 1; // Marcar como alcanzable
                }
            }
        } else { // Peón negro
            if (fila < 7) {
                if (columna < 7) {
                    if (tablero[fila + 1][columna + 1] == 0) // Casilla alcanzable
                        tablero[fila + 1][columna + 1] = 1; // Marcar como alcanzable
                }
                if (columna > 0) {
                    if (tablero[fila + 1][columna - 1] == 0) // Casilla alcanzable
                        tablero[fila + 1][columna - 1] = 1; // Marcar como alcanzable
                }
            }
        }
    }

    private static void marcarRey(int[][] tablero, int fila, int columna) {
        // Marcar las casillas alcanzables por el rey (8 casillas alrededor)
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Ignorar la casilla del rey
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
                    if (tablero[nuevaFila][nuevaColumna] == 0) // Solo marcar si está vacío
                        tablero[nuevaFila][nuevaColumna] = 1; // Marcar como alcanzable
                }
            }
        }
    }

    private static void marcarReina(int[][] tablero, int fila, int columna) {
        // Marcar las casillas alcanzables por la reina (combinación de torre y alfil)
        marcarTorre(tablero, fila, columna);
        marcarAlfil(tablero, fila, columna);
    }

    private static void marcarAlfil(int[][] tablero, int fila, int columna) {
        // Marcar las casillas alcanzables por el alfil
        for (int i = 1; i < 8; i++) {
            if (fila + i < 8 && columna + i < 8) {
                if (tablero[fila + i][columna + i] != 0 && tablero[fila + i][columna + i] != 1) break;
                tablero[fila + i][columna + i] = 1; // Marcar como alcanzable
            }
        }
        for (int i = 1; i < 8; i++) {
            if (fila - i >= 0 && columna - i >= 0) {
                if (tablero[fila - i][columna - i] != 0 && tablero[fila - i][columna - i] != 1) break;
                tablero[fila - i][columna - i] = 1;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (fila + i < 8 && columna - i >= 0) {
                if (tablero[fila + i][columna - i] != 0 && tablero[fila + i][columna - i] != 1) break;
                tablero[fila + i][columna - i] = 1;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (fila - i >= 0 && columna + i < 8) {
                if (tablero[fila - i][columna + i] != 0 && tablero[fila - i][columna + i] != 1) break;
                tablero[fila - i][columna + i] = 1;
            }
        }
    }

    private static void marcarCaballo(int[][] tablero, int fila, int columna) {
        // Marcar las casillas alcanzables por el caballo
        int[][] movimientos = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] movimiento : movimientos) {
            int nuevaFila = fila + movimiento[0];
            int nuevaColumna = columna + movimiento[1];
            if (nuevaFila >= 0 && nuevaFila < 8 && nuevaColumna >= 0 && nuevaColumna < 8) {
                if (tablero[nuevaFila][nuevaColumna] == 0) // Solo marcar si está vacío
                    tablero[nuevaFila][nuevaColumna] = 1; // Marcar como alcanzable
            }
        }
    }

    private static void marcarTorre(int[][] tablero, int fila, int columna) {
        // Marcar las casillas alcanzables por la torre
        for (int i = fila + 1; i < 8; i++) {
            if (tablero[i][columna] != 0 && tablero[i][columna] != 1) break; // Detener si hay una pieza
            tablero[i][columna] = 1; // Marcar como alcanzable
        }
        for (int i = fila - 1; i >= 0; i--) {
            if (tablero[i][columna] != 0  && tablero[i][columna] != 1) break;
            tablero[i][columna] = 1;
        }
        for (int j = columna + 1; j < 8; j++) {
            if (tablero[fila][j] != 0  && tablero[fila][j] != 1) break;
            tablero[fila][j] = 1;
        }
        for (int j = columna - 1; j >= 0; j--) {
            if (tablero[fila][j] != 0  && tablero[fila][j] != 1) break;
            tablero[fila][j] = 1;
        }
    }
}
