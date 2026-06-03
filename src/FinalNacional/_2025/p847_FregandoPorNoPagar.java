package FinalNacional._2025;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

public class p847_FregandoPorNoPagar {

    static class Plato {
        long instante;
        long tiempo;
        int id; // Añadimos un identificador para saber cuál es el último plato
        Plato(long instante, long tiempo, int id) {
            this.instante = instante;
            this.tiempo = tiempo;
            this.id = id;
        }
    }

    static class FR_Int {
        private InputStream in = System.in;
        private byte[] buffer = new byte[1 << 16];
        private int head = 0;
        private int tail = 0;

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        public long nextLong() throws IOException {
            int c = read();
            while (c != -1 && c <= 32) {
                c = read();
            }
            if (c == -1) return -1;

            long res = 0;
            while (c > 32) {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        FR_Int sc = new FR_Int();
        StringBuilder sb = new StringBuilder();

        long numPlatos;
        while ((numPlatos = sc.nextLong()) > 0) {
            ArrayList<Plato> listaPlatos = new ArrayList<>((int) numPlatos);
            for (int i = 0; i < numPlatos; i++) {
                listaPlatos.add(new Plato(sc.nextLong(), sc.nextLong(), i));
            }

            // La pila ahora guardará el objeto Plato para rastrear su ID
            Stack<Plato> pila = new Stack<>();
            long tiempoActual = 0;
            int i = 0;
            boolean ultimoPlatoProcesado = false;

            while (i < numPlatos || !pila.isEmpty()) {

                // Si ya empezó a fregar el último plato en la iteración anterior, terminamos
                if (ultimoPlatoProcesado) {
                    break;
                }

                // 1. Si la pila está vacía, avanzamos el reloj
                if (pila.isEmpty() && tiempoActual < listaPlatos.get(i).instante) {
                    tiempoActual = listaPlatos.get(i).instante;
                }

                // 2. Apilamos todos los platos que ya hayan llegado
                while (i < numPlatos && listaPlatos.get(i).instante <= tiempoActual) {
                    pila.push(listaPlatos.get(i));
                    i++;
                }

                // 3. Rodrigo coge el plato de arriba
                if (!pila.isEmpty()) {
                    sb.append(pila.size()).append(" ");
                    Plato actual = pila.pop();
                    tiempoActual += actual.tiempo;

                    // Si el plato que acaba de coger es el último de la lista (id == numPlatos - 1)
                    if (actual.id == numPlatos - 1) {
                        ultimoPlatoProcesado = true;
                    }
                }
            }

            // Reemplazar el espacio final por salto de línea
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
                sb.setCharAt(sb.length() - 1, '\n');
            }
        }

        System.out.print(sb.toString());
    }
}