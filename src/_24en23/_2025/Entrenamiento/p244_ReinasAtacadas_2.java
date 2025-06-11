package _24en23._2025.Entrenamiento;

import java.util.*;

public class p244_ReinasAtacadas_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt(); // tamaño del tablero (ancho y alto)
            int q = sc.nextInt(); // número de reinas

            if (n == 0 && q == 0) break;

            Set<Integer> columnas = new HashSet<>();
            Set<Integer> filas = new HashSet<>();
            Set<Integer> diagPrincipal = new HashSet<>(); // x - y
            Set<Integer> diagSecundaria = new HashSet<>(); // x + y

            boolean hayConflicto = false;

            for (int i = 0; i < q; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                if (!columnas.add(x) || !filas.add(y) ||
                        !diagPrincipal.add(x - y) || !diagSecundaria.add(x + y)) {
                    hayConflicto = true;
                }
            }

            System.out.println(hayConflicto ? "SI" : "NO");
        }

        sc.close();
    }
}