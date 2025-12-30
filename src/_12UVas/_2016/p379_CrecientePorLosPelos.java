package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- O es muy simple o esto tiene truco...
 *      Leer el vector, volver a escribirlo excepto el último número que debe incrementarse en 1
 *      WA!
 *
 * v2.- Pues tenía algún truco que no he sabido ver...
 *      El vector 1 2 3 4 es creciente por los pelos, pero según la v1, daría 1 2 3 5 que no es creciente por los pelos
 *      cuando debería dar 2 2 2 2
 *      Por lo tanto, hay que buscar dos números iguales consecutivos desde el final, incrementar el segundo número y arrastrarlo
 *      hacia la derecha. Si no hay ninguna pareja igual, se incrementará el primer número
 *
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class p379_CrecientePorLosPelos {

    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);

            int n = scan.nextInt();
            while (n != 0) {

                //Define array de tamaño n
                int[] numeros = new int[n];

                //Lee el vector entero
                for (int i = 0; i < n; i++) {
                    numeros[i] = scan.nextInt();
                }

                // Buscar desde el final dos números iguales consecutivos
                int j = 0;
                for (int i = n - 1; i > 0; i--) {
                    if (numeros[i] == numeros[i - 1]) {
                        j = i;
                        break;
                    }
                }

                // Incrementar esa posición
                numeros[j]++;

                // Copiar el valor incrementado hacia la derecha
                for (int i = j + 1; i < n; i++) {
                    numeros[i] = numeros[j];
                }

                // Salida
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if (i > 0) sb.append(' ');
                    sb.append(numeros[i]);
                }
                System.out.println(sb.toString());

                //Siguiente caso
                n = scan.nextInt();
            }
        }
    }

