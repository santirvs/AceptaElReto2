package _24en23._2025;

import java.io.InputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author santi
 * @date 15/06/2025
 */



class LongReader {
    private InputStream in;
    private byte[] buffer = new byte[1 << 16]; // 64KB
    private int pointer = 0, bytesRead = 0;

    public LongReader(InputStream in) {
        this.in = in;
    }

    private byte read() throws IOException {
        if (pointer >= bytesRead) {
            pointer = 0;
            bytesRead = in.read(buffer);
            if (bytesRead == -1) return -1;
        }
        return buffer[pointer++];
    }

    public long nextLong() throws IOException {
        byte c;
        do {
            c = read();
            if (c == -1) throw new IOException("EOF");
        } while (c <= ' ');

        boolean neg = false;
        if (c == '-') {
            neg = true;
            c = read();
        }

        long x = 0;
        while (c >= '0' && c <= '9') {
            x = x * 10 + (c - '0');
            c = read();
        }
        return neg ? -x : x;
    }

    public boolean hasNext() throws IOException {
        byte c;
        while (true) {
            c = read();
            if (c == -1) return false;
            if (c > ' ') {
                pointer--;
                return true;
            }
        }
    }
}
public class p800_TravesurasEnElHotelDeHilbert {

    static Random rand = new Random();


    static void agregarFactor(Map<Long, Integer> factores, long p) {
        if (factores.containsKey(p)) factores.put(p, factores.get(p) + 1);
        else factores.put(p, 1);
    }

    // Paso 1: usar criba de Eratóstenes para probar divisores pequeños
    static void factorizarPequenos(long n, Map<Long, Integer> factores) {
        int LIM = 1000000;
        boolean[] esCompuesto = new boolean[LIM + 1];
        List<Integer> primos = new ArrayList<Integer>();
        for (int i = 2; i <= LIM; i++) {
            if (!esCompuesto[i]) {
                primos.add(i);
                for (int j = i + i; j <= LIM; j += i) esCompuesto[j] = true;
            }
        }

        for (int p : primos) {
            while (n % p == 0) {
                agregarFactor(factores, p);
                n /= p;
            }
            if (n == 1) break;
        }
    }

    static void factorizar(long n, Map<Long, Integer> factores) {
        if (n == 1) return;
        if (esPrimo(n)) {
            agregarFactor(factores, n);
            return;
        }
        long f = rho(n);
        factorizar(f, factores);
        factorizar(n / f, factores);
    }

    static long rho(long n) {
        if (n % 2 == 0) return 2;
        long x = 2 + rand.nextInt(1000000);
        long y = x;
        long c = 1 + rand.nextInt(1000000);
        long d = 1;
        while (d == 1) {
            x = (modMul(x, x, n) + c) % n;
            y = (modMul(y, y, n) + c) % n;
            y = (modMul(y, y, n) + c) % n;
            d = gcd(Math.abs(x - y), n);
            if (d == n) return rho(n);
        }
        return d;
    }

    static boolean esPrimo(long n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        long d = n - 1;
        int s = 0;
        while (d % 2 == 0) {
            d /= 2;
            s++;
        }

        long[] bases = {2, 3, 5, 7, 11, 13, 17, 19};
        for (long a : bases) {
            if (a >= n) continue;
            long x = modPow(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean pasa = false;
            for (int r = 0; r < s - 1; r++) {
                x = modMul(x, x, n);
                if (x == n - 1) {
                    pasa = true;
                    break;
                }
            }
            if (!pasa) return false;
        }
        return true;
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = modMul(result, base, mod);
            base = modMul(base, base, mod);
            exp >>= 1;
        }
        return result;
    }

    static long modMul(long a, long b, long mod) {
        long result = 0;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) result = (result + a) % mod;
            a = (a << 1) % mod;
            b >>= 1;
        }
        return result;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }


//    public static void main(String[] args) {
//        long n = 999999999999999989L;  // Ejemplo: número primo grande
//
//        long start = System.nanoTime();
//        Map<Long, Integer> factores = new HashMap<Long, Integer>();
//
//        // Paso 1: Factoriza usando primos pequeños
//        factorizarPequenos(n, factores);
//
//        long producto = 1;
//        for (Map.Entry<Long, Integer> entry : factores.entrySet()) {
//            for (int i = 0; i < entry.getValue(); i++) {
//                producto *= entry.getKey();
//            }
//        }
//
//        long resto = n / producto;
//        if (resto > 1) {
//            if (esPrimo(resto)) {
//                agregarFactor(factores, resto);
//            } else {
//                factorizar(resto, factores); // usar Pollard's Rho solo si es necesario
//            }
//        }
//
//        // Cálculo de cantidad de divisores
//        long total = 1;
//        for (int e : factores.values()) total *= (e + 1);
//
//        long end = System.nanoTime();
//        System.out.println("Cantidad de divisores: " + total);
//        System.out.printf("Tiempo: %.3f ms\n", (end - start) / 1e6);
//    }
//

    public static void main(String[] args) throws IOException {
        //Scanner scan = new Scanner(System.in);
        LongReader scan = new LongReader(System.in);

        while (scan.hasNext()) {
            // Leer el número a comprobar
            long numero = scan.nextLong();
            Map<Long, Integer> factores = new HashMap<Long, Integer>();

            // Paso 1: Factoriza usando primos pequeños
            factorizarPequenos(numero, factores);

            long producto = 1;
            for (Map.Entry<Long, Integer> entry : factores.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    producto *= entry.getKey();
                }
            }

            long resto = numero / producto;
            if (resto > 1) {
                if (esPrimo(resto)) {
                    agregarFactor(factores, resto);
                } else {
                    factorizar(resto, factores); // usar Pollard's Rho solo si es necesario
                }
            }

            // Cálculo de cantidad de divisores
            long total = 1;
            for (int e : factores.values()) total *= (e + 1);

            // Comprobar si es primo
            if (total % 2 == 0) {
                System.out.println("APAGADA");
            } else {
                System.out.println("ENCENDIDA");
            }
        }

    }

}



