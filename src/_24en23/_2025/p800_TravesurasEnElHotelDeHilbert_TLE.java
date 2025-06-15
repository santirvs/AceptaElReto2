package _24en23._2025;

import java.util.*;
import java.lang.Math;

/**
 *
 * @author santi
 * @date 15/06/2025
 */

// v1. Nos piden saber si un número es primo o no.

public class p800_TravesurasEnElHotelDeHilbert_TLE {

        // Devuelve la cantidad de divisores de un número
        public static int numDivisores(long n)
        {
            int contador = 1; // 1 siempre es un factor primo
            // Print the number of 2s that divide n
            while (n % 2 == 0) {
                contador ++;
                n /= 2;
            }

            // Al llegar aquí, n es impar. Por lo tanto, podemos saltar de 2 en 2
            for (int i = 3; i <= Math.sqrt(n); i += 2) {
                // Si i divide n, contar cuántas veces lo hace
                while (n % i == 0) {
                    contador++;
                    n /= i;
                }
            }

            // Por si me encuentro con un número primo mayor que 2
            if (n > 2)
                contador++;

            return contador;
        }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            // Leer el número a comprobar
            long numero = scan.nextLong();

            // Comprobar si es primo
            if (numDivisores(numero) % 2 == 0) {
                System.out.println("APAGADA");
            } else {
                System.out.println("ENCENDIDA");
            }
        }

        scan.close();
    }
}



