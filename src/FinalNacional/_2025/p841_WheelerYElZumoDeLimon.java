package FinalNacional._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class p841_WheelerYElZumoDeLimon {

    static class Persona implements Comparable<Persona>{
        int evaluacion;
        int percepcion;
        int sobreestimacion;

        Persona(int evaluacion) {
            this.evaluacion = evaluacion;
        }

        public void setPercepcion(int percepcion) {
            this.percepcion = percepcion;
            sobreestimacion = percepcion-evaluacion;
        }

        @Override public int compareTo(Persona other) {
            return this.evaluacion - other.evaluacion;
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
        FR_Int sc = new FR_Int();
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int numPersonas = sc.nextInt();
        while (numPersonas > 0 ) {

            Persona[] personas = new Persona[numPersonas];
            //Leer la evaluacion
            for (int i=0; i<numPersonas; i++) {
                personas[i] = new Persona(sc.nextInt());
            }
            //Leer la percepcion y actualizar
            for (int i=0; i<numPersonas; i++) {
                personas[i].setPercepcion(sc.nextInt());
            }

            //Ordenar por percepcion
            Arrays.sort(personas);

            boolean blnSeCumple = true;
            //Verificar que la persona siguiente tiene una sobreestimación inferior
            for (int i=0; i<numPersonas-1; i++) {
                if (personas[i].sobreestimacion <= personas[i+1].sobreestimacion) {
                    blnSeCumple = false;
                }
            }

            if (blnSeCumple) out.println("SI");
            else out.println("NO");

            //Siguiente caso
            numPersonas = sc.nextInt();

        }

        out.close();
    }

}