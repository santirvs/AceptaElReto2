import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static java.lang.System.out;

// Hay que descomponer el caso en varios pasos
// 1.- Construir un grafo con las teclas del mando
// 2.- Generar un mapa donde resolvamos cuanto cuesta ir de una tecla a otra cualquiera
// 3.- Resolver el caso

// Para construir un grafo, recorremos la matriz y marcamos las casillas ya visitadas.
// Como las teclas no se repiten, podemos apoyarnos en un array de booleanos

// Para generar el mapa, usaremos el algoritmo de Floyd que, aunque tiene coste V^3, al ser V muy bajo nos sirve

// Una vez generado el mapa, simplemente añadimos la 1a letra (posición inicial) a lo que buscamos
// y calculamos los desplazamientos entre dos letras consecutivas

public class p563_TecladoDeTelevisor {

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

    public static HashMap<Character, Integer>  crearMapaTeclas() {
        HashMap<Character, Integer> mapa = new HashMap<Character, Integer>();
        //Inserta el punto
        mapa.put('.',0);
        int index=0;
        //Añade las letras
        for (char c='A'; c<='Z';c++) {
            mapa.put(c, ++index);
        }
        //Añade los números
        for (char c='0'; c<='9'; c++) {
            mapa.put(c, ++index);
        }
        return mapa;
    }

    public static void Floyd (int[][]dist) {
        for (int k=0; k<dist.length; ++k) {
            for (int i=0; i<dist.length; ++i) {
                for (int j=0; j<dist.length; ++j) {
                    dist[i][j] = Math.min( dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
    }
    
    public static int[][] analizarMando(char[][] matriz, HashMap<Character, Integer> indiceTeclas) {
        int[][] distancias = new int[indiceTeclas.size()][indiceTeclas.size()];
        //Inicializarlo a INFINITO (cualquier valor superior a la cantidad de teclas)
        int INF = 100000;
        for (int f=0; f<distancias.length; f++) {
            for (int c=0; c<distancias.length; c++) {
                distancias[f][c]=INF;
            }
        }
        boolean[] visitadas = new boolean[indiceTeclas.size()];
        int f = matriz.length;
        int c = matriz[0].length;

        //Calcular matriz de adyacencias
        for (int i=0; i<f; i++) {
            for (int j= 0; j<c; j++) {
                char teclaActual = matriz[i][j];
                int indiceTeclaActual = indiceTeclas.get(teclaActual);

                //Si ya está visitada la tecla, pasamos a la siguiente
                if ( visitadas[indiceTeclaActual] ) continue;

                //Marcar la tecla como visitada
                visitadas[indiceTeclaActual] = true;

                //Tecla de arriba
                char teclaNueva = matriz[(i-1+f)%f][j];
                int indiceTeclaNueva = indiceTeclas.get(teclaNueva);
                distancias[indiceTeclaActual][indiceTeclaNueva]=1;

                //Tecla de la izquierda
                teclaNueva = matriz[i][(j-1+c)%c];
                indiceTeclaNueva = indiceTeclas.get(teclaNueva);
                distancias[indiceTeclaActual][indiceTeclaNueva]=1;

                //Tecla de la derecha
                int posx = j+1;
                teclaNueva = matriz[i][posx%c];
                //Bucle mientras la tecla sea la misma
                while (posx <= c && teclaActual == teclaNueva) {
                    posx++;
                    teclaNueva = matriz[i][posx%c];
                }
                indiceTeclaNueva = indiceTeclas.get(teclaNueva);
                distancias[indiceTeclaActual][indiceTeclaNueva]=1;

                //Tecla de abajo
                int posy = i+1;
                teclaNueva = matriz[posy%f][j];
                //Bucle mientras la tecla sea la misma
                while (posy <= f && teclaActual == teclaNueva) {
                    posy++;
                    teclaNueva = matriz[posy%f][j];
                }
                indiceTeclaNueva = indiceTeclas.get(teclaNueva);
                distancias[indiceTeclaActual][indiceTeclaNueva]=1;
            }
        }

        //Establecer la diagonal a cero (la distancia de una tecla a ella misma)
        for (int i=0; i < distancias.length ; i++) distancias[i][i] = 0;
        
        return distancias;
    }

    public static void main(String[] args) throws IOException {
        //Fast reader
        FR fr = new FR();
        //Descomentar per fer-lo encara més ràpid, però compte que imprimeix tot de cop!!! (Memory Limit?)
        //PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //Crea el mapa de teclas -> indice que es común para todos los casos
        HashMap<Character, Integer> indiceTeclas = crearMapaTeclas();

        int numFilas = fr.nextInt();
        int numColumnas = fr.nextInt();
        int numPalabras = fr.nextInt();

        while (numPalabras!=0 || numColumnas!=0 || numFilas !=0) {

            //Declaración y lectura de la matriz
            char[][] matriz = new char[numFilas][numColumnas];
            for (int f=0; f<numFilas;f++) {
                matriz[f] = fr.next().toCharArray();
            }

            //análisis del mando, devuelve una matriz de distancias entre botones
            int[][] distancia = analizarMando(matriz, indiceTeclas);

            //aplicar Floyd a la matriz de distancias -> distancia mínima entre cada par de botones
            Floyd(distancia);

            //resolver el caso
            for (int i=0; i < numPalabras; i++) {
                String palabra = fr.next();
                int solucion = distancia[indiceTeclas.get(matriz[0][0])][indiceTeclas.get(palabra.charAt(0))];
                for (int j=0 ; j < palabra.length()-1; j++) {
                    solucion += distancia[indiceTeclas.get(palabra.charAt(j))][indiceTeclas.get(palabra.charAt(j+1))];
                }
                out.println(solucion);
            }

            //Fin del caso
            out.println("---");

            //Siguiente caso
            numFilas = fr.nextInt();
            numColumnas = fr.nextInt();
            numPalabras = fr.nextInt();
        }
    }

}
