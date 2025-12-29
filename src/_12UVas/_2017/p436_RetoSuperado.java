package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Trabajar siempre en micras. Esto obliga a usar long, ya que un edificio
 *      de 10.000 metro se convierte en 10.000.000.0000 micras
 *      Doblar cada vez el ancho del papel hasta igualar o superar el alto del edificio
 */

import java.util.Scanner;

public class p436_RetoSuperado {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            long altoPapel = scan.nextLong();
            long altoEdificio = scan.nextLong() * 1_000_000;

            int numDobleces = 0;
            while (altoPapel < altoEdificio) {
                numDobleces++;
                altoPapel *=2;
            }

            System.out.println(numDobleces);
        }
    }
}
