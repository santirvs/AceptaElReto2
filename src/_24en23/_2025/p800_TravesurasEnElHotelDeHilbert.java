package _24en23._2025;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author santi
 * @date 15/06/2025
 */

// v1. Nos piden saber si un número es primo o no.

public class p800_TravesurasEnElHotelDeHilbert {

    static Random rand = new Random();

    public static long contarDivisores(BigInteger n) {
        Map<BigInteger, Integer> factores = new HashMap<BigInteger, Integer>();
        factorizar(n, factores);

        long total = 1;
        for (Iterator<Map.Entry<BigInteger, Integer>> it = factores.entrySet().iterator(); it.hasNext();) {
            Map.Entry<BigInteger, Integer> entry = it.next();
            total *= (entry.getValue() + 1);
        }
        return total;
    }

    public static void factorizar(BigInteger n, Map<BigInteger, Integer> factores) {
        if (n.equals(BigInteger.ONE)) return;
        if (esPrimo(n, 10)) {
            if (factores.containsKey(n)) {
                factores.put(n, factores.get(n) + 1);
            } else {
                factores.put(n, 1);
            }
            return;
        }
        BigInteger factor = rho(n);
        factorizar(factor, factores);
        factorizar(n.divide(factor), factores);
    }

    public static boolean esPrimo(BigInteger n, int iteraciones) {
        if (n.compareTo(BigInteger.valueOf(2)) < 0) return false;
        if (n.equals(BigInteger.valueOf(2))) return true;
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;

        BigInteger d = n.subtract(BigInteger.ONE);
        int s = 0;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.valueOf(2));
            s++;
        }

        for (int i = 0; i < iteraciones; i++) {
            BigInteger a = randomBetween(BigInteger.valueOf(2), n.subtract(BigInteger.valueOf(2)));
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;

            boolean compuesto = true;
            for (int r = 0; r < s - 1; r++) {
                x = x.modPow(BigInteger.valueOf(2), n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    compuesto = false;
                    break;
                }
            }
            if (compuesto) return false;
        }

        return true;
    }

    public static BigInteger rho(BigInteger n) {
        if (n.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return BigInteger.valueOf(2);
        BigInteger x = randomBetween(BigInteger.valueOf(2), n.subtract(BigInteger.ONE));
        BigInteger y = x;
        BigInteger c = randomBetween(BigInteger.ONE, n.subtract(BigInteger.ONE));
        BigInteger d = BigInteger.ONE;

        while (d.equals(BigInteger.ONE)) {
            x = x.multiply(x).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            y = y.multiply(y).mod(n).add(c).mod(n);
            d = x.subtract(y).abs().gcd(n);

            if (d.equals(n)) return rho(n);
        }
        return d;
    }

    public static BigInteger randomBetween(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min).add(BigInteger.ONE);
        int length = range.bitLength();
        BigInteger result;
        do {
            result = new BigInteger(length, rand);
        } while (result.compareTo(range) >= 0);
        return result.add(min);
    }

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


//        public static void main2(String[] args) {
//            // Ejemplo: número grande (tarda menos de 1 segundo)
//            BigInteger numero = new BigInteger("999999999999999989");
//            long start = System.nanoTime();
//            long divisores = contarDivisores(numero);
//            long end = System.nanoTime();
//
//            System.out.println("Cantidad de divisores: " + divisores);
//            System.out.printf("Tiempo: %.3f ms%n", (end - start) / 1e6);
//        }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            // Leer el número a comprobar
            long numero = scan.nextLong();

            if (numero > 1_000_000L) {
                // Comprobar si es primo por el metodo de Miller-Rabin
                if (contarDivisores(BigInteger.valueOf(numero)) % 2 == 0) {
                    System.out.println("APAGADA");
                } else {
                    System.out.println("ENCENDIDA");
                }
            }
            else {
                if (numDivisores(numero) % 2 == 0) {
                    System.out.println("APAGADA");
                } else {
                    System.out.println("ENCENDIDA");
                }
            }
        }

        scan.close();
    }
}


