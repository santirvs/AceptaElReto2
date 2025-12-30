package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Dar la vuelta a la frase original y hacer una segunda pasada comprobando mayúsculas y minúsculas
 */

import java.util.Scanner;

public class p372_LaFarsanteDeMaryPoppins {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        scan.nextLine();

        while (numCasos-- > 0) {

            String palabra = scan.nextLine();
            StringBuilder palabraInvertida = new StringBuilder(palabra.toLowerCase());
            palabraInvertida.reverse();

            //Buscar posiciones mayúsculas en la palabra original y asignarlas a la palabra invertida
            for (int pos=0; pos < palabra.length(); pos++) {
                if (Character.isUpperCase(palabra.charAt(pos))) {
                    palabraInvertida.setCharAt(pos, Character.toUpperCase(palabraInvertida.charAt(pos)));
                }
            }

            //Mostrar el resultado
            System.out.println(palabraInvertida);
        }
    }
}
