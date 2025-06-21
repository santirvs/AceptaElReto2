package _24en23._2025;

import java.util.Scanner;
import java.util.*;

/**
 *
 * @author santi
 * @date 21/06/2025
 */

// v1.- Buscar el MCM de todas las combinaciones de 4 monedas distintas
//      (20 monedas, combinaciones de 4: 4845 combinaciones) no parece excesivo
//      Almacenar los múltiplos de cada MCM, podemos encontrar el más cercano por debajo y por encima de la altura deseada
//      Finalmente, imprimir el más cercano por debajo y por encima de la altura deseada


public class p806_SoportesEnElBanco {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Leer hasta que no haya más casos
        while (sc.hasNextInt()) {
            // Leer la altura deseada y el número de monedas
            int alturaDeseada = sc.nextInt();
            int n = sc.nextInt();
            int[] monedas = new int[n];
            // Leer las monedas
            for (int i = 0; i < n; i++) {
                monedas[i] = sc.nextInt();
            }

            // Usamos un TreeSet para almacenar las alturas posibles
            TreeSet<Integer> alturasPosibles = new TreeSet<>();

            // Elegimos combinaciones de 4 tipos distintos
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        for (int l = k + 1; l < n; l++) {
                            // Calcular el MCM de las 4 monedas
                            int lcm = lcm(lcm(monedas[i], monedas[j]), lcm(monedas[k], monedas[l]));

                            // Añadir los múltiplos más cercanos a la altura deseada
                            int numRepeticiones = alturaDeseada / lcm;
                                alturasPosibles.add(numRepeticiones * lcm);
                            // También considerar el múltiplo más cercano por encima
                            if (numRepeticiones * lcm < alturaDeseada) {
                                alturasPosibles.add((numRepeticiones + 1) * lcm);
                            }

                        }
                    }
                }
            }

            // Agregamos caso especial: altura 0 (sin usar monedas)
            alturasPosibles.add(0);

            // Buscar el múltiplo más cercano por debajo y por encima de la altura deseada
            Integer porAbajo = alturasPosibles.floor(alturaDeseada);
            Integer porArriba = alturasPosibles.ceiling(alturaDeseada);

            // Imprimir los resultados
            System.out.println(porAbajo + " " + porArriba);
        }

        sc.close();
    }

    // Función para calcular el MCM de dos números
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    // Máximo común divisor (algoritmo de Euclides)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}






