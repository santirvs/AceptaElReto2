package _12UVas._2017;
/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

import java.io.InputStream;
import java.io.IOException;

/**
 * v1.- Contar las apariciones de cada dígito en una secuencia
 *      y determinar si todos los dígitos aparecen el mismo número de veces
 *      AC - ranking 573 - 0.171 seg
 *
 * v2.- Añado FastScanner y StringBuilder
 *      AC - ranking 275 - 0.023 seg
 */



public class p435_ElPijote {

    static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16]; // 64 KB
        private int ptr = 0, len = 0;

        public FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        // ======================
        // Lectura de enteros
        // ======================
        public int nextInt() throws IOException {
            int c, sign = 1, val = 0;

            do {
                c = readByte();
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

        public long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;

            do {
                c = readByte();
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

        // ======================
        // Lectura de palabra
        // ======================
        public String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            do {
                c = readByte();
                if (c == -1) return null;
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }

        // ======================
        // Lectura de línea completa
        // ======================
        public String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            // Si venimos de un token, saltar fin de línea pendiente
            while (true) {
                c = readByte();
                if (c == -1) return sb.length() == 0 ? null : sb.toString();
                if (c != '\n' && c != '\r') break;
            }

            while (c != '\n' && c != '\r' && c != -1) {
                sb.append((char) c);
                c = readByte();
            }

            return sb.toString();
        }
        public boolean hasNext() throws IOException {
            int c;
            while (true) {
                c = readByte();
                if (c == -1) return false;
                if (c > ' ') {
                    ptr--;
                    return true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scan = new FastScanner(System.in);
        StringBuilder salida = new StringBuilder();

        while (scan.hasNext()) {
            //Lee el número como cadena de texto
            String numero = scan.nextLine();

            //Inicializa el contador de frecuencias
            int[] digitos = new int[10];

            //Incrementa el contador de frecuencias
            for (int i=0; i<numero.length(); i++) {
                digitos[numero.charAt(i)-'0']++;
            }

            //Determina si todos los contadores son iguales o no
            boolean iguales = true;
            for (int i=1; i<10 && iguales; i++) {
                iguales = digitos[i] == digitos[0];
            }

            //Mostrar el resultado
            if (iguales) {
                //System.out.println("subnormal");
                salida.append("subnormal\n");
            } else {
                //System.out.println("no subnormal");
                salida.append("no subnormal\n");
            }


        }

        System.out.print(salida);
    }
}