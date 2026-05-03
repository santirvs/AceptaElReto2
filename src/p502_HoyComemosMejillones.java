import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.IOException;

import static java.lang.System.out;

// Ir construyendo diferentes pilas de mejillones
// Cuando tengamos varias pilas donde situarlo escogeremos aquella que se ajuste mejor
// (que minimize el espacio libre entre la concha existente y la que ponemos)
// No nos interesa guardar toda la pila, solo el top, con lo cual podemos usar un vector
// Ese vector será creciente, con lo que podemos realizar una búsqueda binaria

//Problemas:
// MLE -> Es necesario usar un FastReader para solo enteros. No se admiten conversiones desde String
// TLE -> No se puede usar el binarySearch de la biblioteca y a partir de allí hacer una búsqueda lineal.
//        Es necesario hacer un upperBound con una búsqueda binaria manual

public class p502_HoyComemosMejillones {

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

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        float nextFloat() { return Float.parseFloat(next()); }

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


    public static void main(String[] args) throws IOException {
        //Fast reader
        //FR fr = new FR();
        FR_Int fr = new FR_Int();
        //Scanner fr = new Scanner(System.in);
        //Descomentar per fer-lo encara més ràpid, però compte que imprimeix tot de cop!!! (Memory Limit?)
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int[] pilas = new int[500010];  //Tamaño máximo de entradas: 500.000

        //String entrada = fr.next();
        int entrada = fr.nextInt();
        //while (entrada != null) {
        while (entrada > 0) {
            //while (fr.hasNext()) {

            //Reaprovechar la misma pila y liberar memoria
            int numMontones = 0;

            //int tamanyo = Integer.parseInt(entrada);
            int tamanyo = entrada;

            for (int i=0; i<tamanyo; i++) {

                int mejillon = fr.nextInt();

                int index = upper_bound(pilas, numMontones, mejillon);

                pilas[index] = mejillon;
                if (index == numMontones) {
                    numMontones++;
                }
            }

            out.println(numMontones);

            //Siguiente caso
            //entrada = fr.next();
            entrada = fr.nextInt();
        }
    }


    public static int upper_bound(int[] pilas, int tamanyo, int valor) {

        int izquierda = 0;
        int derecha = tamanyo -1;
        int posicion = tamanyo;

        // Búsqueda binaria
        while (izquierda <= derecha) {
            // Usamos desplazamiento de bits (>>> 1) en lugar de / 2
            // Es ligeramente más rápido a nivel de procesador
            int medio = (izquierda + derecha) >>> 1;

            if (pilas[medio] > valor) {
                posicion = medio;
                derecha = medio - 1;
            } else {
                izquierda = medio + 1;
            }
        }

        return posicion;
    }

}
