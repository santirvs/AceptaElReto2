package _24en23._2025;

/**
 *
 * @author santi
 * @date 15/06/2025
 */

import java.util.Scanner;

/*
 Después de 7 intentos con TLE haciéndolo de diferentes formas,
 donde el planteamiento ya era correcto:

 a) Este problema se basa en una idea famosa relacionada con el número de divisores de cada habitación:
 b) Si una habitación tiene un número par de divisores, termina apagada.
 c) Si tiene un número impar de divisores, termina encendida.

 Faltaba conocer el "atajo" clave para solucionarlo sin TLE:
 d) Solo los cuadrados perfectos tienen un número impar de divisores.
*/

public class p800_TravesurasEnElHotelDeHilbert {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLong()) {
            long room = sc.nextLong();

            if (isPerfectSquare(room)) {
                System.out.println("ENCENDIDA");
            } else {
                System.out.println("APAGADA");
            }
        }

        sc.close();
    }

    // Método para comprobar si un número long es un cuadrado perfecto
    public static boolean isPerfectSquare(long n) {
        long sqrt = (long) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
