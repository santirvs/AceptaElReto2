package FinalNacional._2025;

import java.io.IOException;
import java.io.InputStream;

public class p846_SubiendoYBajandoPorLaMuralla {

    static class FR_Int {
        private InputStream in = System.in;
        private byte[] buffer = new byte[1 << 16];
        private int head = 0;
        private int tail = 0;

        private int read() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1; // Fin de archivo
            }
            return buffer[head++];
        }

        public int nextInt() throws IOException {
            int c = read();
            // Ignorar espacios en blanco o saltos de línea (ASCII <= 32)
            while (c != -1 && c <= 32) {
                c = read();
            }

            if (c == -1) return -1; // EOF

            // --- CORRECCIÓN AQUÍ: Soporte para números negativos ---
            boolean negativo = false;
            if (c == '-') {
                negativo = true;
                c = read();
            }

            int res = 0;
            // Construir el número mientras el carácter sea visible (> 32)
            while (c > 32) {
                res = res * 10 + (c - '0');
                c = read();
            }
            return negativo ? -res : res;
        }
    }

    public static void main(String[] args) throws IOException {
        FR_Int sc = new FR_Int();
        StringBuilder sb = new StringBuilder();

        int n;
        while ((n = sc.nextInt()) > 0) {
            long alturaActual = 0;

            // Inicializamos la altura mínima con el valor del primer tramo
            int primerDesnivel = sc.nextInt();
            alturaActual += primerDesnivel;

            long alturaMinima = Math.min(0, alturaActual);
            long maximaDiferencia = Math.max(0, alturaActual);

            for (int i = 1; i < n; i++) {
                int desnivel = sc.nextInt();
                alturaActual += desnivel;

                // 1. Calculamos la ganancia con respecto al punto más bajo previo
                long diferenciaPotencial = alturaActual - alturaMinima;
                if (diferenciaPotencial > maximaDiferencia) {
                    maximaDiferencia = diferenciaPotencial;
                }

                // 2. Actualizamos el punto más bajo para las siguientes metas posibles
                if (alturaActual < alturaMinima) {
                    alturaMinima = alturaActual;
                }
            }

            sb.append(maximaDiferencia).append("\n");
        }

        System.out.print(sb.toString());
    }
}