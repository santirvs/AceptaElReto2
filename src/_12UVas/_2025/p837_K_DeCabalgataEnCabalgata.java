package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

 /**
 * v1.- Planteamiento general del problema
 *      Combinar dos estrategias básicas:
 *      -Búsqueda binaria sobre la respuesta
 *         Buscar el máximo valor X tal que sea posible que todas las secciones
 *         tengan al menos X leds de iluminación.
 *      - Comprobación voraz con ventana deslizante
 *        Para un valor X dado:
 *          Recorrer las secciones de izquierda a derecha.
 *          Si una sección no llega a X, añadimos los leds mínimos necesarios
 *          en el foco más a la derecha que aún la afecte.
 *          Controlar los efectos de esas adiciones con un array de diferencias para que sea eficiente.
 *
 */


 import java.io.*;

public class p837_K_DeCabalgataEnCabalgata {

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        boolean hasNext() throws IOException {
            int c;
            while ((c = read()) != -1) {
                if (!Character.isWhitespace(c)) {
                    ptr--;
                    return true;
                }
            }
            return false;
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) != -1 && Character.isWhitespace(c)) ;
            if (c == -1) throw new EOFException();
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            long val = 0;
            while (c >= '0' && c <= '9') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }
    }

    static int numFocos, focosLados;
    static long ledsRepuesto;
    static long[] focos;
    static long[] inicial;


    // Comprueba si es posible una iluminación de X en todos los tramos
    static boolean puede(long X) {
        //Array de diferencias para los ajustes de la ventana
        long[] diff = new long[numFocos + 2];
        long usados = 0;
        long efecto = 0;

        //Recorrer todos los focos de la situación inicial (que contiene el efecto de la ventana)
        for (int i = 0; i < numFocos; i++) {
            efecto += diff[i];
            long actual = inicial[i] + efecto;

            //Si no se alcanza la iluminación deseada en una ventana
            if (actual < X) {
                //Se le añaden los leds necesarios a esa ventana
                long repuestos = X - actual;
                usados += repuestos;
                if (usados > ledsRepuesto) return false; //Si excedemos el stock --> imposible

                //Actualiza el efecto según los leds que se han necesitado
                efecto += repuestos;
                //Actualiza el array de diferencias
                int fin = i + 2 * focosLados + 1;
                if (fin < diff.length) diff[fin] -= repuestos;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        while (fs.hasNext()) {
            //Lectura de los datos del caso
            numFocos = (int) fs.nextLong();
            focosLados = (int) fs.nextLong();
            ledsRepuesto = fs.nextLong();

            //Leer todos los focos y guardar en array
            focos = new long[numFocos];
            for (int i = 0; i < numFocos; i++) {
                focos[i] = fs.nextLong();
            }

            /* ---- Suma de prefijos para iluminación inicial ---- */
            long[] pref = new long[numFocos + 1];
            for (int i = 0; i < numFocos; i++) {
                pref[i + 1] = pref[i] + focos[i];
            }

            //Nuevo array de focos con la situación inicial de la ventana deslizante
            //apoyándose en la suma de prefijos calculada anteriormente
            inicial = new long[numFocos];
            for (int i = 0; i < numFocos; i++) {
                int left = Math.max(0, i - focosLados);
                int right = Math.min(numFocos - 1, i + focosLados);
                inicial[i] = pref[right + 1] - pref[left];
            }

            //Busqueda binaria entre 0 y 1e18
            long lo = 0, hi = (long) 1e18;
            while (lo < hi) {
                long mid = (lo + hi + 1) >>> 1; // mid = ceil((lo + hi) / 2)
                if (puede(mid)) lo = mid;
                else hi = mid - 1;
            }

            //Imprime el resultado
            System.out.println(lo);
        }

    }
}
