import java.io.*;
import java.util.*;

public class p782_ElAbueloCentenario {

        /* ============================================================
         *  LECTOR RÁPIDO DE ENTRADA
         *  ------------------------------------------------------------
         *  Evita Scanner y StringTokenizer.
         *  Fundamental para no dar TLE en Acepta el Reto.
         * ============================================================ */
        static class FastScanner {
            private final byte[] buffer = new byte[1 << 16];
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

        /* ============================================================
         *  ESTRUCTURAS AUXILIARES
         * ============================================================ */

        // Representa una ventana (obstáculo rectangular)
        static class Ventana {
            int x1, x2, h;
            Ventana(int a, int b, int c) {
                x1 = a; x2 = b; h = c;
            }
        }

        /* ------------------------------------------------------------
         *  Intervalo:
         *  Cada objeto Intervalo representa una "barra" del histograma.
         *
         *  xi -> inicio del intervalo en el eje X
         *  xd -> final del intervalo en el eje X
         *  h  -> altura máxima permitida en ese tramo
         *
         *  El ancho real del intervalo es (xd - xi)
         * ------------------------------------------------------------ */
        static class Intervalo {
            int xi, xd, h;
            Intervalo(int a, int b, int c) {
                xi = a; xd = b; h = c;
            }
        }

        // Usado en las pilas monótonas
        static class InfoCoord {
            int x, h;
            InfoCoord(int a, int b) {
                x = a; h = b;
            }
        }

        public static void main(String[] args) throws Exception {
            FastScanner fs = new FastScanner();
            StringBuilder salida = new StringBuilder();

            /* ============================================================
             *  BUCLE PRINCIPAL DE CASOS
             * ============================================================ */
            while (true) {
                int ancho = fs.nextInt();
                int alto = fs.nextInt();
                int numObs = fs.nextInt();

                // Condición de fin
                if (ancho == 0 && alto == 0 && numObs == 0) break;

                /* ------------------------------------------------------------
                 *  separaciones:
                 *  TreeMap ordenado por X.
                 *  En cada X guardamos la altura máxima permitida.
                 * ------------------------------------------------------------ */
                TreeMap<Integer, Integer> separaciones = new TreeMap<>();
                separaciones.put(0, alto);  // Inicio del eje X

                // Gargolas: limitan altura solo en un punto X
                HashMap<Integer, Integer> gargolas = new HashMap<>();

                // Ventanas: afectan a intervalos completos
                ArrayList<Ventana> ventanas = new ArrayList<>();

                /* ============================================================
                 *  LECTURA DE OBSTÁCULOS
                 * ============================================================ */
                for (int i = 0; i < numObs; i++) {
                    char tipo = fs.nextChar();
                    int x = fs.nextInt();
                    int y = fs.nextInt();

                    // Toda coordenada X relevante crea una separación
                    separaciones.put(x, alto);

                    if (tipo == 'G') {
                        // Gárgola: limita altura solo en X
                        int h = alto - y;
                        if (gargolas.containsKey(x))
                            gargolas.put(x, Math.min(gargolas.get(x), h));
                        else
                            gargolas.put(x, h);
                    }
                    else { // Ventana
                        int anchoV = fs.nextInt();
                        int altoV = fs.nextInt();
                        int x2 = x + anchoV;

                        separaciones.put(x2, alto);
                        ventanas.add(new Ventana(x, x2, alto - y));
                    }
                }

                /* ============================================================
                 *  APLICAR LAS VENTANAS
                 *  Reduce la altura permitida en los tramos afectados
                 * ============================================================ */
                for (Ventana v : ventanas) {
                    for (Map.Entry<Integer, Integer> e :
                            separaciones.tailMap(v.x1, true).entrySet()) {

                        if (e.getKey() >= v.x2) break;
                        e.setValue(Math.min(e.getValue(), v.h));
                    }
                }

                /* ============================================================
                 *  CONSTRUCCIÓN DE INTERVALOS
                 *  Transformación clave:
                 *  Problema 2D → histograma 1D
                 * ============================================================ */
                ArrayList<Intervalo> intervalos = new ArrayList<>();

                for (Map.Entry<Integer, Integer> e : separaciones.entrySet()) {
                    int xi = e.getKey();
                    int h = e.getValue();

                    // Si hay gárgola, creamos un intervalo puntual
                    if (gargolas.containsKey(xi)) {
                        intervalos.add(new Intervalo(xi, xi, gargolas.get(xi)));
                    }

                    Integer siguiente = separaciones.higherKey(xi);
                    int xd = (siguiente == null) ? ancho : siguiente;

                    intervalos.add(new Intervalo(xi, xd, h));
                }

                /* ============================================================
                 *  MÁXIMO RECTÁNGULO EN HISTOGRAMA
                 *  Algoritmo O(n) con pilas monótonas
                 * ============================================================ */
                int n = intervalos.size();
                int[] left = new int[n];
                int[] right = new int[n];
                Arrays.fill(right, ancho);

                ArrayDeque<InfoCoord> pilaL = new ArrayDeque<>();
                ArrayDeque<InfoCoord> pilaR = new ArrayDeque<>();

                // Barrido hacia la izquierda
                for (int i = 0; i < n; i++) {
                    Intervalo in = intervalos.get(i);
                    while (!pilaL.isEmpty() && pilaL.peek().h >= in.h)
                        pilaL.pop();

                    if (!pilaL.isEmpty())
                        left[i] = pilaL.peek().x;

                    pilaL.push(new InfoCoord(in.xd, in.h));
                }

                // Barrido hacia la derecha
                for (int i = n - 1; i >= 0; i--) {
                    Intervalo in = intervalos.get(i);
                    while (!pilaR.isEmpty() && pilaR.peek().h >= in.h)
                        pilaR.pop();

                    if (!pilaR.isEmpty())
                        right[i] = pilaR.peek().x;

                    pilaR.push(new InfoCoord(in.xi, in.h));
                }

                /* ============================================================
                 *  CÁLCULO DEL ÁREA MÁXIMA
                 * ============================================================ */
                long areaMax = 0;
                for (int i = 0; i < n; i++) {
                    Intervalo in = intervalos.get(i);
                    long area = (long)(right[i] - left[i]) * in.h;
                    if (area > areaMax) areaMax = area;
                }

                salida.append(areaMax).append('\n');
            }

            System.out.print(salida);
        }
    }
