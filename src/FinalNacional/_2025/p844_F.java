package FinalNacional._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class p844_F {

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

    public static void main(String[] args) {
        FR sc = new FR();
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String sFilas = sc.next();
        while (sFilas!=null) {
            int filas = Integer.parseInt(sFilas);
            int columnas = sc.nextInt();

            String patron = sc.nextLine().trim();


            for (int i=0; i<filas; i++) {
                String fila = "";
                int index = patron.length()-i%patron.length();
                fila = patron.substring(index);
                while (fila.length() < columnas) {
                    fila += patron;
                }
                out.println(fila.substring(0,columnas));
            }

            //Siguiente caso
            sFilas = sc.next();
        }

        out.close();
    }

}