package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer el número y hacer un bucle de N repeticiones imprimiendo un 1
 */

import java.util.Scanner;

public class p369_ContandoEnLaArena {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numero = scan.nextInt();

        while (numero != 0) {
            for (int i = 0; i < numero; i++) {
                System.out.print("1");
            }
            System.out.println();
            numero = scan.nextInt();
        }
    }
}
