package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer los números y procesarlos segun se leen. No es necesario almacenarlos
 */

import java.util.Scanner;

public class p374_Criogenizacion {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {

            long n = scan.nextLong();
            long max = Long.MIN_VALUE;
            long min = Long.MAX_VALUE;
            long maxRepeticiones = 0;
            long minRepeticiones = 0;

            while (n > 0) {
                if (n > max) {
                    maxRepeticiones = 1;
                    max = n;
                } else if (n == max) {
                    maxRepeticiones++;
                }
                if (n < min) {
                    minRepeticiones = 1;
                    min = n;
                } else if (n == min) {
                    minRepeticiones++;
                }

                n= scan.nextLong();
            }

            System.out.println(min + " " + minRepeticiones + " " + max + " " + maxRepeticiones);

        }
    }
}
