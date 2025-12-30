package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- No veo una fórmula directa.
 *      Pero sí una fórmula descompuesta:
 *      Caras superior e inferior: 2caras * n cubos * n cubos
 *      El resto son n-2 anillos
 *      Los anillos tienen 2 lados * n cubos + 2 lados opuestos *(n-2) cubos
 *
 *      Ojo para n = 1.000.000 --> usar long
 */

import java.util.Scanner;

public class p373_CubosVisibles {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {

            long n = scan.nextLong();

            long carasSuperiorInferior = 2 * n * n;
            long anillos = (n-2) * (2 * n + 2 * (n-2));
            long totalCubosVisibles = carasSuperiorInferior + anillos;

            System.out.println(totalCubosVisibles);
        }
    }
}
