package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer la frase. Recorrerla contando letras y admiraciones.
 *      Comparar los contadores
 */

import java.util.Scanner;

public class p438_Esgritura {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        while (scan.hasNext()) {
            String escritura = scan.nextLine().toUpperCase();

            int numExclamaciones = 0;
            int numLetras = 0;

            //Contar las apariciones de letras y exclamaciones
            for (int pos=0; pos<escritura.length(); pos++) {
                char caracter = escritura.charAt(pos);
                if (caracter == '!') {
                    numExclamaciones++;
                }
                else if (caracter >= 'A' && caracter <= 'Z') {
                    numLetras ++;
                }
            }

            //Comparar los contadores
            if (numExclamaciones > numLetras) {
                System.out.println("ESGRITO");
            } else {
                System.out.println("escrito");
            }
        }
    }
}
