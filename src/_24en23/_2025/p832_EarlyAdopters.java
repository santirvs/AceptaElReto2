package _24en23._2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author santi
 * @date 18/06/2025
 */

// v1.- Calcular recursivamente la lista de los números a insertar


public class p832_EarlyAdopters {


    public static List<Integer> resolver(int numNodos, int alturaMaxima) {

        if (posible)

        return lista;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {

            int numNodos = scan.nextInt();
            int alturaMaxima = scan.nextInt();

            List<Integer> lista = resolver(numNodos, alturaMaxima);

            //Imprimir la lista de nodos
            for (int i = 0; i < lista.size(); i++) {
                if (i > 0) System.out.print(" ");
                System.out.print(lista.get(i));
            }

        }
    }
}

