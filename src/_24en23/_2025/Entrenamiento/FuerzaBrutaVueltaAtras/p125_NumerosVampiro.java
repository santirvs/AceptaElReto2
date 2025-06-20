package _24en23._2025.Entrenamiento.FuerzaBrutaVueltaAtras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/**
 *
 * @author santi
 * @date 20/06/2025
 */


public class p125_NumerosVampiro {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = Integer.parseInt(sc.nextLine());

        while (casos-- > 0) {
            String numStr = sc.nextLine().trim();
            int num = Integer.parseInt(numStr);

            if (esVampiro(num)) {
                System.out.println("SI");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean esVampiro(int num) {
        String numStr = String.valueOf(num);

        // Condició 1: Nombre parell de dígits
        if (numStr.length() % 2 != 0) {
            return false;
        }

        int halfLength = numStr.length() / 2;
        List<String> colmillos = new ArrayList<>();

        int inicio = (int) Math.pow(10, halfLength - 1);
        int fin = (int) Math.pow(10, halfLength);

        for (int i = inicio; i < fin; i++) {
            if (num % i != 0) continue;

            int j = num / i;

            // Condició 2: Els dos colmillos han de tenir la mateixa longitud (halfLength)
            if (String.valueOf(j).length() != halfLength) continue;

            // Condició 3: Els colmillos no poden acabar simultàniament en 0
            if (i % 10 == 0 && j % 10 == 0) continue;

            // Condició 4: Comparar si els dígits coincideixen
            String fangs = String.valueOf(i) + String.valueOf(j);
            if (mateixosDigits(numStr, fangs)) {
                return true;
            }
        }

        return false;
    }

    private static boolean mateixosDigits(String original, String combinat) {
        char[] o1 = original.toCharArray();
        char[] o2 = combinat.toCharArray();
        Arrays.sort(o1);
        Arrays.sort(o2);
        return Arrays.equals(o1, o2);
    }
}
 