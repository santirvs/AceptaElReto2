package _24en23._2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 19/06/2025
 */

// v1.- Usar un Trie (árbol de prefijos) para almacenar los hashes
//      Y contar cuántos hashes pasan por cada nodo  --> MLE
// v2.- Ordenar los hashes y calcular el prefijo común con el anterior y el siguiente
//      para determinar cuántos caracteres son necesarios para que cada hash sea único
//      Este planteamiento funciona porque no existe ningún hash que sea prefijo de otro.
//      --> AC

public class p804_MinimizandoHashes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            sc.nextLine();
            // No hay más casos
            if (n == 0) break;

            if (n == 1) {
                sc.nextLine(); // leer la única hash, pero no procesarla
                System.out.println(0);
                continue;
            }

            // Leer todos los hashes
            String[] hashes = new String[n];
            for (int i = 0; i < n; i++) {
                hashes[i] = sc.nextLine();
            }

            // Ordenar los hashes lexicográficamente
            Arrays.sort(hashes);

            int totalMinChars = 0;

            for (int i = 0; i < n; i++) {
                int prefixPrev = 0;
                int prefixNext = 0;

                if (i > 0) {
                    prefixPrev = commonPrefixLength(hashes[i], hashes[i - 1]);
                }

                if (i < n - 1) {
                    prefixNext = commonPrefixLength(hashes[i], hashes[i + 1]);
                }

                int minUnique = Math.max(prefixPrev, prefixNext) + 1;
                totalMinChars += minUnique;
            }

            System.out.println(totalMinChars);
        }
    }

    // Devuelve cuántos caracteres consecutivos son iguales desde el inicio
    static int commonPrefixLength(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }
}




