package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- No encuentro una fórmula directa
 *      Así que hago un bucle que vaya sumando 3*1 + 3*2 + 3*3 + ... + 3*n
 *      Ojo con el límite de 20.000 --> usar long?  No es necesario, da 600 millones
 */

import java.util.Scanner;

public class p371_AburrimientoEnLasSobremesas {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int lado = scan.nextInt();

        while (lado > 0) {

            int numCerillas = 0;
            for (int i=1; i<=lado; i++) {
                numCerillas += 3*i;
            }
            System.out.println(numCerillas);
            lado = scan.nextInt();
        }
    }
}
