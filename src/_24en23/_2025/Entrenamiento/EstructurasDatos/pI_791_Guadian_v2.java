package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.io.*;
import java.util.*;

//La idea es ir creando las pilas de cajas desde abajo.
//De esta forma no nos importa la cantidad de cajas que vengan después ya que
//la pila solo crecerá si la nueva pieza (en la base) soporta la altura existente.

// Usar una cola con prioridad.
// Se ordenarán las cajas de menor a mayor
// Se añadirá siempre en la cola más pequeña
// Si la cola nos ofrece una pila menor de la que se soporta, se extrae el elemento y se reinserta con altura +1
// Si la cola nos ofrece una pil mayor de la que se soporta, las demás tampoco se soportarán.
//    Se añade el elemento con altura 1
//    Se incrementa el número de pilas en 1

public class pI_791_Guadian_v2 {


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

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE; // EOF
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
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder out = new StringBuilder();

        while (true) {
            int n = fs.nextInt();
            if (n == Integer.MIN_VALUE) break; // gestión del EOF con FastScanner

            //Leer las cajas
            int[] cajas = new int[n];
            for (int i = 0; i < n; i++) {
                cajas[i] = fs.nextInt();
            }

            //Ordenar las cajas
            Arrays.sort(cajas);

            //Definir una lista de prioridad
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int numPilas = 0;

            //Recorre cada una de las cajas
            for (int caja : cajas) {
                //Si la cola no está vacía y la de menor altura la soporta la nueva caja
                if (!pq.isEmpty() && pq.peek() <= caja) {
                    //Elimina el valor de la cola y lo reinserta incrementando en 1 (la pila se ha incrementado en 1)
                    pq.offer(pq.poll() + 1);
                } else {
                    //En caso contrario, ninguna otra pila se soportará
                    //   inserta un 1 (1 caja apilada) e incrementa el contador de pilas
                    pq.offer(1);
                    numPilas++;
                }
            }

            //El resultado es el número de pilas
            out.append(numPilas).append('\n');
        }

        System.out.print(out);
    }
}
