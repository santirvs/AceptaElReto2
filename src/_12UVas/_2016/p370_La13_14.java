package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer los dos números de la llave, que deben cumplir
 *      a) Son consecutivos
 *      b) El menor de los dos números es par
 */

import java.util.Scanner;

public class p370_La13_14 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int casos = scan.nextInt();
        scan.nextLine();

        while (casos-- > 0) {
            String[] llave = scan.nextLine().split("-");

            int num1 = Integer.parseInt(llave[0]);
            int num2 = Integer.parseInt(llave[1]);

            int min = Math.min(num1,num2);

            if (min%2==0  && Math.abs(num1-num2)==1) {
                System.out.println("SI");
            } else {
                System.out.println("NO");
            }
        }
    }
}
