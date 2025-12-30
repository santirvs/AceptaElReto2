package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Tratar el número con BigInteger y dividirlo entre 16
 *      El residuo de esa división es el número que buscamos y habrá que comparar con 0,1,4 o 9
 */

import java.math.BigInteger;
import java.util.Scanner;

public class p377_CuadradosImperfectos {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext() )  {
            //Leer el número
            String numero = scan.nextLine();

            //Pasarlo a BigInteger
            BigInteger numB = new BigInteger(numero);

            //Modulo 16
            BigInteger modulo = numB.mod(new BigInteger("16"));

            //Comparar con 0, 1, 4 o 9
            if ( modulo.equals(new BigInteger("0")) ||
                    modulo.equals(new BigInteger("1")) ||
                    modulo.equals(new BigInteger("4")) ||
                    modulo.equals(new BigInteger("9")) ) {
                System.out.println("NO SE");
            } else {
                System.out.println("IMPERFECTO");
            }

        }
    }
}
