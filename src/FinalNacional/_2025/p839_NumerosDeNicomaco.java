package FinalNacional._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.System.out;

public class p839_NumerosDeNicomaco {

    static class FR {
        BufferedReader br;
        StringTokenizer st;

        public FR() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null; // Manejo de EOF
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        float nextFloat() {
            return Float.parseFloat(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st != null && st.hasMoreElements()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
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

            int res = 0;
            // Construir el número mientras el carácter sea visible (> 32)
            while (c > 32) {
                res = res * 10 + (c - '0');
                c = read();
            }
            return res;
        }
    }

    public static void main(String[] args) {
        FR sc = new FR();
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //Pre-calcular los números de Nicomaco hasta 10^9
        int[] nico = new int[252];
        int sumaCubos = 0;

        for (int i=1; sumaCubos < 1_000_000_000; i++) {
            sumaCubos += i*i*i;
            nico[i-1] = sumaCubos;
        }
        nico[251] = Integer.MAX_VALUE;

        //El array ya está ordenado, solo hay que buscarlo
        int numCasos = sc.nextInt();
        while (numCasos-- > 0) {
            int numero = sc.nextInt();
            int pos= Arrays.binarySearch(nico, numero);

            if (pos>=0) out.println(nico[pos]);
            else out.println(nico[-pos-1]);
        }

        out.close();
    }

}
