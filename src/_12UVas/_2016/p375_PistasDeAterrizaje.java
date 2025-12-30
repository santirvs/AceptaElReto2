package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer la pista. Tendrá siempre un longitud máxima de 3 (dos dígitos y una letra opcional)
 *      Transformar los dígitos a número y sumarle o restarle 18 (<19 --> sumar 18, sino restar 18)
 *      Recuperar la letra: L pasa a ser R y R pasa a ser L
 *      Imprimir la nueva pista
 */

import java.util.Scanner;

public class p375_PistasDeAterrizaje {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            //Leer y dividir los datos
            String pista = scan.nextLine();
            int numPista = Integer.parseInt(pista.substring(0,2));
            String letra = "";
            if (pista.length() > 2) {
                letra = "" + pista.charAt(2);
            }

            //Transformar los datos
            if (numPista < 19) {
                numPista+=18;
            } else {
                numPista-=18;
            }
            if (letra.equals("L")) letra = "R";
            else if (letra.equals("R")) letra = "L";

            //Imprimir resultado
            System.out.printf("%02d%s\n",numPista,letra);

        }
    }
}
