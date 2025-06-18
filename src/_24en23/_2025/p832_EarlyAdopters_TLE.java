package _24en23._2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/06/2025
 */

// v1.- Calcular recursivamente la lista de los números a insertar  -> TLE


public class p832_EarlyAdopters_TLE {

    //Lista donde ir guardando el resultado
    static List<Integer> result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();
        while (casos-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            //Inicializar la lista de resultados
            result = new ArrayList<>();

            //Construye el árbol
            if (!buildTree(1, n, k)) {
                //No se puede
                System.out.println("IMPOSIBLE");
            } else {
                //Sí se puede. Imprimir el resultado
                for (int i = 0; i < result.size(); i++) {
                    if (i > 0) System.out.print(" ");
                    System.out.print(result.get(i));
                }
                System.out.println();
            }
        }
        sc.close();
    }

    // Construye el arbol por valores [lo, hi] con altura máxima k
    static boolean buildTree(int lo, int hi, int k) {
        if (lo > hi) return true; // Subarbol vacío, altura 0
        if (k == 0) return false; // No se puede tener nodos con altura 0

        //Buscar el punto medio a partir del cual hacer el árbol
        for (int mid = lo; mid <= hi; mid++) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();

            // Guarda la lista original para poder hacer backtracking
            List<Integer> prev = new ArrayList<>(result);
            result.add(mid);

            // Comprueba que puedan construirse los árboles a la izquierda y la derecha
            // del punto medio
            boolean okLeft = buildTree(lo, mid - 1, k - 1);
            boolean okRight = buildTree(mid + 1, hi, k - 1);

            //Si se pueden hacer los dos, se devuelve cierto
            if (okLeft && okRight) {
                return true;
            }

            // Si no ha funcionado, hacer backtrack
            result = prev;
        }

        //Si llegamos al final sin éxito, es que no se puede
        return false;
    }
}
