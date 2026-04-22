import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import static java.lang.System.out;


// Implementar dos colas de prioridad: una para los menores
// y otra para los mayores
// En el top tendremos siempre los candidatos a ser los medianos
// Es necesario mantener ambas colas equilibradas de forma que
// la cola de menores siempre tendrá como máximo un elemento más que
// la cola de mayores.
// El candidato estará siempre en el top de la cola de menores

public class p302_ElMedianoPorFavor {

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
        //Definir las colas con prioridad. La de min en orden inverso (nos interesa saber el mayor)
        PriorityQueue<Integer> pqMin = new PriorityQueue<Integer>(11, Collections.reverseOrder());
        PriorityQueue<Integer> pqMax = new PriorityQueue<>();

        //Fast reader
        FR fr = new FR();
        //Scanner fr = new Scanner(System.in);
        //Descomentar per fer-lo encara més ràpid, però compte que imprimeix tot de cop!!! (Memory Limit?)
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String entrada = fr.next();
        while (entrada != null) {
        //while (fr.hasNext()) {

            //Tratar el caso
            //int numEventos = fr.nextInt();
            int numEventos = Integer.parseInt(entrada);
            pqMin.clear();
            pqMax.clear();
            boolean primero = true;

            while (numEventos-- > 0) {
                int num = fr.nextInt();

                //Si no es un cero, apilar
                if (num != 0) {
                    //Apilar en pqMin o pqMax dependiendo del top de pqMax
                    if (pqMin.isEmpty() || pqMax.isEmpty() || num <= pqMax.peek()) {
                        pqMin.add(num);
                    } else {
                        pqMax.add(num);
                    }

                    balancearColas(pqMin, pqMax);

                }
                //Si es un cero, procesar
                else {
                    //Espacio separador entre eventos (excepto en el primero)
                    if (primero) primero=false;
                    else out.print(" ");

                    //Obtener el top de la pqMin
                    if (pqMin.isEmpty())
                        out.print("ECSA");
                    else
                        out.print(pqMin.poll());

                    //Balancear colas
                    balancearColas(pqMin, pqMax);
                }
            }

            //Final del caso
            out.println();

            //Siguiente caso
            entrada = fr.next();
        }

        //Salida del buffer
        out.close();

    }

    private static void balancearColas(PriorityQueue<Integer> pqMin, PriorityQueue<Integer> pqMax) {
        // La pqMin debe tener como máximo un elemento más que la pqMax
        if (pqMin.size() < pqMax.size()) {
            pqMin.add(pqMax.poll());
        }

        if (pqMin.size() > pqMax.size()+1) {
            pqMax.add(pqMin.poll());
        }
    }
}
