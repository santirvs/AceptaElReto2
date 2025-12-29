package _12UVas._2017;
/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Teorema de las casillas
 *      Si hay menos casillas que palomas, por fuerza habrá dos palomas en una misma casilla
 *      Si hay las mismas o más casillas que palomas, cada paloma puede estar en un único casillero
 *      AC -- ranking 461 - 0.122 seg
 *
 * v2.- Pruebo con BufferedReader e InputStreamReader
 *      AC -- ranking 323 - 0.045 seg
 *
 * v3.- Pruebo con FastScanner
 *      AC -- ranking 284 - 0.026 seg
 *
 * v4.- Añado StringBuilder para la salida
 *      AC -- ranking 97 - 0.008 seg
 *
 * v5.- Ajusto el tamaño del buffer de entrada paso de 16 a 20
 *      AC -- ranking 98 - 0.009 seg
 * v6.- Ajusto el tamaño del buffer de entrada paso de 16 a 12
 *      AC -- ranking 84 - 0.007 seg
 * v7.- Ajusto el tamaño del buffer de entrada paso de 16 a 10
 *      AC -- ranking 85 - 0.007 seg
 * v8.- Ajusto el tamaño del buffer de entrada paso de 16 a 8
 *      AC -- ranking 100 - 0.008 seg
 *
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class p434_RomanceEnElPalomar {

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 20];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        // Lee un entero (positivo o negativo)
        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
                if (c == -1) return Integer.MIN_VALUE;
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        // Lee un carácter no blanco
        char nextChar() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');
            return (char) c;
        }
    }


    public static void main(String[] args) throws IOException {
        //Scanner scan = new Scanner(System.in);
        //BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        FastScanner scan = new FastScanner();
        StringBuilder salida = new StringBuilder();

        //int numCasos = Integer.parseInt(scan.readLine());
        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {

            //String[] linea = scan.readLine().split(" ");
            //int numPalomas = Integer.parseInt(linea[0]);
            //int numCasillas = Integer.parseInt(linea[1]);
            int numPalomas = scan.nextInt();
            int numCasillas = scan.nextInt();

            if (numCasillas >= numPalomas) {
                //System.out.println("ROMANCE");
                salida.append("ROMANCE\n");
            } else {
                //System.out.println("PRINCIPIO");
                salida.append("PRINCIPIO\n");
            }

        }

        System.out.print(salida);
    }
}