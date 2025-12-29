package _12UVas._2017;


/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Contar los niveles de un racimo perfecto
 *      es como sumar de 1..N y comprobar si la suma es menor que el número de uvas del racimo
 *      sumar de 1..N = (1 + N) * N / 2
 *      Como N no es muy grande (10^6), haremos una búsqueda lineal,
 *      sinó buscaremos en el espacio de soluciones (1..numUvas) con busqueda binaria
 *      AC!
 */

import java.util.Scanner;

public class p433_RacimosDeUvas {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int totalUvas = scan.nextInt();
        while (totalUvas > 0) {
            int numNiveles = 1;
            int numUvas = 0;
            for (numNiveles = 1; numUvas < totalUvas; numNiveles++) {
                numUvas = (1 + numNiveles) * numNiveles / 2;
            }

            //A la salida i tiene el número de niveles +1
            System.out.println(numNiveles - 1);

            //Siguiente caso
            totalUvas = scan.nextInt();
        }
    }
}
