package _24en23._2025.Entrenamiento.ProblemasMatematicos;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date14/06/2025
 */

/**
 * 1/k = 1/x + 1/y
 *  (multiplicar por kxy)
 *  kxy/k = kxy/x + kxy/y
 *  xy = kx + ky
 *  restar kx + ky
 *  xy - kx - ky = 0
 *  sumar k^2
 *  xy -kx - ky + k^2 = k^2
 *  reordenar
 *  (x-k)(y-k) = k^2
 *
 *  Sea a = x-k, b= y-x, entonces ab = k^2
 *  Es decir, estamos buscando todos los pares de enteros positivos (a,b) tales que ab=k^2
 *  Por cada divisor a de k^2, existe un b = k^2 / a
 *  Entonces, cada par (a,b) genera un par (x,y) = (a+k,b+k)
 *   Como se considera que (x,y) y (y,x) son iguales, solo contamos una vez cada pareja. Por eso, solo se cuentan las que cumplen
 * x >= y (o sea, a ≥ b), lo que equivale a contar los divisores de k^2 que son menores o iguales a la raíz cuadrada de k^2.
 * Por lo tanto, el número de pares distintos (x,y) es igual al número de divisores de k^2 que son menores o iguales a k.
 */

/* v1 -> TLE --> Agilizar la búsqueda de divisores


 */

public class p226_ReescribiendoFracciones {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLong()) {
                long k = sc.nextLong();
                System.out.println(contarParejas(k));
            }
            sc.close();
        }

        // Cuenta los pares distintos (x, y) tal que 1/k = 1/x + 1/y
        static int contarParejas(long k) {
            long objetivo = k * k;
            int conteo = 0;

            // Recorremos todos los divisores de k^2
            for (long i = 1; i * i <= objetivo; i++) {
                if (objetivo % i == 0) {
                    long j = objetivo / i;
                    long x = i + k;
                    long y = j + k;

                    conteo++;
                }
            }

            return conteo;
        }
    }
