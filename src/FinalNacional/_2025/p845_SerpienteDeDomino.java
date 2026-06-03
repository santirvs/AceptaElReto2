package FinalNacional._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class p845_SerpienteDeDomino {

    static class FR_Int {
        private InputStream in = System.in;
        private byte[] buffer = new byte[1 << 10];
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

    public static int normalizaFicha(int ficha) {
        int fichaInvertida = ficha / 10 + (ficha %10) * 10;
        return (Math.min(ficha, fichaInvertida));
    }

    public static void main(String[] args) throws IOException {
        FR_Int sc = new FR_Int();
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int numFichas = sc.nextInt();
        while (numFichas > 0) {

            //Contador de cada ficha
            HashMap<Integer,Integer> dominos = new HashMap<>();

            int ultimaFicha = sc.nextInt();
            dominos.put(normalizaFicha(ultimaFicha),1);

            boolean hayError = false;
            numFichas--;

            while(numFichas-- > 0) {
                int ficha = sc.nextInt();

                if (ficha/10 != ultimaFicha%10) {
                    hayError = true;
                }
                if (!hayError) {
                    int fichaNormalizada = normalizaFicha(ficha);
                    if (dominos.containsKey(fichaNormalizada)) {
                        dominos.put(fichaNormalizada, dominos.get(fichaNormalizada) + 1);
                    } else {
                        dominos.put(fichaNormalizada, 1);
                    }
                    ultimaFicha = ficha;
                }
            }

            if (hayError) {
                out.println("ERROR");
            }
            else {
                int maximo = 0;
                for (int valor : dominos.values()) {
                    if (valor > maximo) maximo=valor;
                }
                out.println(maximo);
            }

            //Siguiente caso
            numFichas = sc.nextInt();

        }

        out.close();
    }

}