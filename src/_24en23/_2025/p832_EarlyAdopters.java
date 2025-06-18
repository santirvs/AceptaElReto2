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
// v2.- En lugar de comprobar todos los árboles,
//      los construye y comprueba que funcionen

public class p832_EarlyAdopters {

    static List<Integer> respuesta;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCasos = sc.nextInt();

        while (numCasos-- > 0) {
            int numeros = sc.nextInt();
            int altura = sc.nextInt();

            respuesta = new ArrayList<>();

            //Prueba a construir el árbol
            if (!construirArbol(1, numeros, altura)) {
                //No es posible
                System.out.println("IMPOSIBLE");
            } else {
                //Sí es posible, mostrar el resultado
                for (int i = 0; i < respuesta.size(); i++) {
                    if (i > 0) System.out.print(" ");
                    System.out.print(respuesta.get(i));
                }
                System.out.println();
            }
        }
    }

    // Prueba a construir un árbol (no balanceado)
    //  - que sea lexicográficamente el más pequeño
    //  - con valores entre lo y hi,
    //  - de altura máxima h
    static boolean construirArbol(int lo, int hi, int h) {
        if (lo > hi) return true; // Árbol vacío tiene altura 0
        if (h == 0) return false; // No se puede insertar nada más si altura 0

        int tamanyo = hi - lo + 1;

        // Elegimos el punto medio lo más pequeño posible para garantizar menor orden lex
        int minHeight = getMinHeight(tamanyo);
        if (minHeight > h) return false;

        // Buscamos la raíz que produzca el árbol lexicográficamente más pequeño
        for (int root = lo; root <= hi; root++) {
            int left = root - lo;
            int right = hi - root;

            // Altura de subárboles máximo h-1
            // Si es posible crear los subárboles, tenemos la raíz que buscamos
            // (la prioridad es que sea lexicográficamente menor, no que esté balanceado)
            if (getMinHeight(left) <= (h-1) && getMinHeight(right) <= (h-1)) {
                //añade la raiz
                respuesta.add(root);
                //construye los árboles derecho e izquierdo
                construirArbol(lo, root - 1, (h-1));
                construirArbol(root + 1, hi, (h-1));
                //Esto siempre devolverá true!! (ya se ha comprobado antes que se pueda hacer)
                return true;
            }
        }

        return false;
    }

    // Devuelve la altura mínima que se necesita para insertar n nodos (es decir, la más compacta)
    static int getMinHeight(int n) {
        if (n == 0) return 0;
        int h = 0;
        while ((1 << h) - 1 < n) h++;
        return h;
    }
}

