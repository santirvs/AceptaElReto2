import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import static java.lang.System.out;


// Recursivamente, buscar la longitud máxima que se puede conseguir
// Si tiene longitud <2 -> devolver la longitud (1 (siempre es palindromo) o 0 (vacío))
// Si empieza y acaba por la misma letra -> 2 + long_max(palabra sin 1r y último caracter)
// Si empieza y acaba por letras diferentes -> 2 opciones
//      a) long_max(palabra sin 1r caracter)
//      b) long_max(palabra sin ultimo caracter)
//      Quedarse con el mayor de a y b
//      Si son iguales damos prioridad a eliminar por la izquierda y nos quedaremos con b)
// Cada opción nos la guardaremos en una matriz para saber la longitud máxima que podemos tener
// desde dos posiciones cualquiera

// Pero con esto tendremos la longitud máxima y no la palabra...
// Hacer un segundo recorrido (ahora basta con usar la matriz) y nos quedaremos con el carácter que nos interese
// en función de las posiciones inicio y fin
// Son iguales?  Guardar la primera letra en una lista (añadir al final), inicio++, final--
// Son diferentes?  Consultar la longitud máxima (ini+1, final)  (ini, final-1)  (ini+1, final-1)
//     Quedarse con la mayor y eliminar las letras pertinentes

public class p322_TiroAlPatindromo {

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

    public static void main(String[] args) {
        //Fast reader
        FR fr = new FR();
        //Scanner fr = new Scanner(System.in);
        //Descomentar per fer-lo encara més ràpid, però compte que imprimeix tot de cop!!! (Memory Limit?)
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String entrada = fr.next();
        while (entrada != null) {
        //while (fr.hasNext()) {

            //Definir la matriz de soluciones inicializada a -1
            int [][] soluciones = new int[entrada.length()][entrada.length()];
            for (int i=0; i < soluciones.length; i++) {
                for (int j=0; j < soluciones[i].length; j++) {
                    soluciones[i][j] = -1;
                }
            }

            //Resolver el caso
            int longitud = solve(entrada, 0, entrada.length()-1, soluciones);

            //Imprimir la palabra
            ArrayList<Character> solucion = new ArrayList<Character>();

            construir_palabra(entrada, 0, entrada.length()-1, soluciones, solucion);

            //Imprimir la solucion
            for (int i=0; i<solucion.size(); i++) {
                out.print(solucion.get(i));
            }
            int j=solucion.size()-1;
            if (longitud%2!=0) j--;
            for (; j>=0;j--){
                out.print(solucion.get(j));
            }
            out.println();

            //Siguiente caso
            entrada = fr.next();
        }

        //Salida del buffer
        out.close();

    }

    public static int solve(String s, int inicio, int fin, int[][] soluciones) {
        int result = -1;

        //Ya lo hemos resuelto? Evitar solapamiento!
        if (soluciones[inicio][fin]!=-1) return soluciones[inicio][fin];

        //Longitud inferior a 2
        if (fin == inicio)  {
            result = 1;
        }
        else if (fin < inicio) {
            result = 0;
        }
        else {
            //Empieza y acaba por el mismo caracter
            if (s.charAt(inicio) == s.charAt(fin)) {
                result = 2 + solve(s, inicio+1, fin-1, soluciones);
            }
            else {
                //Empieza y acaba por caracteres diferentes
                int izq = solve(s, inicio+1, fin, soluciones);
                int der = solve(s, inicio, fin-1, soluciones);

                if (der>=izq) result = der;
                else result = izq;
            }

        }

        soluciones[inicio][fin] = result;

        return result;
    }


    public static void construir_palabra(String s, int inicio, int fin, int[][] soluciones, ArrayList<Character> solucion) {
        //Longitud inferior a 2
        if (fin == inicio)  {
            //Añadir el último caracter que queda
            solucion.add(s.charAt(inicio));
        }
        else if (fin < inicio) {
            //No añadir nada
        }
        else {
            //Empieza y acaba por el mismo caracter
            if (s.charAt(inicio) == s.charAt(fin)) {
                solucion.add(s.charAt(inicio));
                construir_palabra(s, inicio+1, fin-1, soluciones, solucion);
            }
            else {
                //Empieza y acaba por caracteres diferentes, ¿cual se elimina?
                int izq = soluciones[inicio+1][fin];
                int der = soluciones[inicio][fin-1];

                if (der>izq) construir_palabra(s, inicio, fin-1, soluciones, solucion);
                else construir_palabra(s, inicio+1, fin, soluciones, solucion);
            }
        }
    }

}
