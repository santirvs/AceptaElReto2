package FinalNacional._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class p843_E {

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

        int ancho = sc.nextInt();
        int alto = sc.nextInt();
        int numCanones = sc.nextInt();
        while (ancho!=0 || alto !=0 || numCanones!=0) {

            //Definir la fachada
            boolean[][] fachada = new boolean[alto][ancho];
            int contador = alto * ancho;

            //Leer los cañones
            while (numCanones-- > 0) {
                int xIni = sc.nextInt();
                int yIni = sc.nextInt();
                int xFin = sc.nextInt();
                int yFin = sc.nextInt();

                //Marca la fachada como proyectada
                for (int fila = yIni; fila < yIni + yFin && fila < fachada.length; fila++) {
                    for (int col = xIni; col < xIni + xFin && col < fachada[0].length; col++) {
                        //Control de si se sale de la fachada
                        if (fila < fachada.length && col < fachada[0].length) {
                            if (fachada[fila][col] == false) {
                                contador--;
                                fachada[fila][col] = true;
                            }
                        }

                    }
                }
            }

            out.println(contador);

            //Siguiente caso
            ancho = sc.nextInt();
            alto = sc.nextInt();
            numCanones = sc.nextInt();

        }

        out.close();
    }

}