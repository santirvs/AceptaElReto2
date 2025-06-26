package _24en23._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author santi
 * @date 24/06/2025
 */

// v0-Empiezo de nuevo después varios intentos infructuosos combinando LIS

// v1-Procesar los puntos de los coches segun llegan al tren
    // Mantener una lista de los coches que se han cargado en el tren
    // Si el coche es más pesado que el último de la lista, se añade al final de la lista
    // Si el coche es más ligero que el primero de la lista, se añade al principio de la lista
    // Si el coche es más pesado que el primero de la lista y más ligero que el último,
    // se busca su posición en la lista (que estará ordenada)
    // y se reemplaza el coche en esa posición.
    // Si está entre el primer coche y el segundo, se posiciona al principio
    // y sino se reemplaza el coche en la posición encontrada
    // ---->  MLE (Memory Limit Exceeded) por usar una lista de enteros para almacenar los coches en el tren

    // Ideas...  ¿se necesita toda la lista de coches del tren?
    //  Si me guardo el primero, segundo, penúltimo y último coche del tren,
    //  puedo saber si el coche que llega es más pesado o más ligero que ellos y si se encuentra entre el primero y segundo
    // Sigue dando MLE. ¿Es necesario guardar los coches del tren?

    // Ideas.... se pueden ir procesando los coches a medida que se leen?


public class p810_ElMozoDeEstacion_MLE2 {


    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // Método para resolver el problema
    public static int resolver(int[] pesos) {
        // Lista para almacenar los coches en el tren

        int[] numerosClave = new int[4];
        int cantidadCoches = 0;
        for (int i = 0; i < 4; i++) {
            numerosClave[i] = Integer.MAX_VALUE; // Inicializar con un valor grande
        }

        if (pesos.length <= 2) {
            return pesos.length; // Si hay 0 o 1 coche, el tren tiene ese número de coches
        }

        for (int peso : pesos) {
            // Si el tren está vacío, añadir el coche
            if (numerosClave[0] == Integer.MAX_VALUE) {
                numerosClave[0] = peso;
                cantidadCoches++;
            } else if (peso > ultimoValor(numerosClave)) {
                // Si el coche es más pesado que el último, añadir al final
                anyadirAlFinal(numerosClave, peso);
                cantidadCoches++;
            } else if (peso < numerosClave[0]) {
                // Si el coche es más ligero que el primero, añadir al principio
                anyadirAlPrincipio(numerosClave, peso);
                cantidadCoches++;
            } else {
                // Buscar la posición correcta para insertar el coche
                reemplazarCoche(numerosClave, peso);

            }
        }

        return cantidadCoches;
    }

    private static void reemplazarCoche(int[] numerosClave, int peso) {
        if (numerosClave[0] < peso && peso < numerosClave[1]) {
            // Si solo hay un coche, se reemplaza el primero
            numerosClave[0] = peso;
        } else if (numerosClave[1] < peso && peso < numerosClave[2]) {
            // Si el peso está en el intervalo entre el segundo y el penúltimo coche
            // se reemplaza el penultimo
            if (numerosClave[3] == Integer.MAX_VALUE) {
                // Si el penúltimo coche no está informado, se añade el peso
                numerosClave[3] = numerosClave[2];
            }
            numerosClave[2] = peso;
        } else if (numerosClave[2] < peso && peso < numerosClave[3])  {
            // Si el peso está entre el penúltimo y el último coche, se reemplaza el último
            numerosClave[3] = peso;
        }
    }

    private static void anyadirAlPrincipio(int[] numerosClave, int peso) {
        int segundo = numerosClave[1];
        numerosClave[1] = numerosClave[0];
        numerosClave[0] = peso; // Añadir el nuevo peso al principio
        if (numerosClave[3] == Integer.MAX_VALUE) {
            // Si el último coche no está informado, se desplaza el penúltimo coche
            numerosClave[3] = numerosClave[2];
            numerosClave[2] = segundo; // Desplazar el segundo coche al penúltimo
        }
    }

    private static void anyadirAlFinal(int[] numerosClave, int peso) {
        int i=3;
        while (numerosClave[i] == Integer.MAX_VALUE) {
            i--;
        }
        if (i == 3) {
            //Se añade el peso al final
            numerosClave[i - 1] = numerosClave[i];
            numerosClave[i] = peso;
        }
        else {
            numerosClave[i+1] = peso; // Añadir el nuevo peso al final
        }
    }

    private static int ultimoValor(int[] numerosClave) {
        int resultado = Integer.MAX_VALUE;

        for (int i = 3; i >= 0; i--) {
            if (numerosClave[i] != Integer.MAX_VALUE) {
                resultado = numerosClave[i]; // Devolver el último valor no máximo
                break;
            }
        }
        return resultado;
    }


    public static void main(String[] args) throws IOException {

            //Lectura de los datos con BufferedReader en lugar de Scanner
            FastReader scan = new FastReader();
            while (true) {
                //Leer hasta encontrar un 0
                int numCoches = scan.nextInt();
                if (numCoches == 0) break;

                //Leer la cantidad de coches que van a llegar
                //Lectura del peso de los n coches
                int[] pesos = new int[numCoches];
                for (int i = 0; i < numCoches; i++) {
                    pesos[i] = scan.nextInt();
                }

                //Calcular la solución
                int res = resolver(pesos);
                System.out.println(res);
            }
        }
    }
