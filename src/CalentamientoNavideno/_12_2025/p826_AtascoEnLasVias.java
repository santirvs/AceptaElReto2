package CalentamientoNavideno._12_2025;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


// Recorremos los trenes desde delante hacia atrás
// Cada vez que encontramos una velocidad menor o igual que la mínima vista se forma un nuevo grupo
// Si es mayor, ese tren alcanzará al grupo y se unirá a él

public class p826_AtascoEnLasVias {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            int numTrenes = scan.nextInt();
            int[]velocidades = new int[numTrenes];

            int velocidadMinima = Integer.MAX_VALUE;
            int grupos = 0;

            //Leer las velocidades
            for (int i = 0; i < numTrenes; i++) {
                velocidades[i] = scan.nextInt();
            }

            // Recorremos desde el último tren hacia atrás
            for (int i = numTrenes - 1; i >= 0; i--) {
                //Y vamos anotando las velocidades mínimas
                if (velocidades[i] <= velocidadMinima) {
                    grupos++;
                    velocidadMinima = velocidades[i];
                }
            }

            System.out.println(grupos);
        }
    }
}
