package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Nos piden cuantos movimientos deben hacerse, pero no en qué orden.
 *      Por lo tanto, nos basta con comprobar las cartas según llegan
 *      contando las cartas donde carta[n] > carta[n+1].
 *      Si es así, la carta[n] deberá cambiarse en algún momento.
 *      WA !!!
 *
 * v2.- Falla para los casos
 *          2 4 5 6 1 3  --> Debe dar 5, pero da 2
 *          3 4 5 1 2    --> Debe dar 3, pero da 2
 *      Cambio de planteamiento:  procesar desde el final simulando el intercambio de la carta
 *      para poder seguir haciendo la comparación. Comprobar adicionalmente si el último elemento es mayor que
 *      alguno de los que ya se han movido.
 *      WA!!!
 *
 *  v3.- La última versión falla para el caso:
 *          3 1 2 4 5  --> Debe dar 3, pero da 2
 *       Cambio de planteamiento. Debe buscarse la secuencia final estrictamente creciente
 *       En resumen:
 *       Las cartas que no se moverán son aquellas que:
 *          - forman un sufijo del array original
 *          - estan estrictamente en orden creciente
 *          - las cartas coinciden con el final del array ordenado
 *       Las demás cartas deberán moverse
 *       Empezar con esperado = +INF
 *       Recorrer el array en orden inverso
 *       Si carta[i] <= esperado, se queda y actualizar esperado como carta[i]
 *       Sino, deberá moverse
 *       WA!!
 *
 * v4.   Falla para el caso 3 1 2 4 5, que debería dar 3, pero da 1
 *       Cambio de planteamiento:
 *       - ordenar el array (sorted)
 *       - encontrar el mayor prefijo de sorted que aparezca como subsecuencia en el array original
 *       - las cartas del prefijo no se mueven y el resto sí
 *
 */

import java.util.Arrays;
import java.util.Scanner;

public class p443_AbanicoDeNaipes {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCartas = scan.nextInt();

        while (numCartas > 0) {

            //Leer las cartas en un array
            int[] cartas = new int[numCartas];
            for (int i=0; i<numCartas; i++) {
                cartas[i] = scan.nextInt();
            }

//            //v.2
//            //Buscar la cantidad de pares donde carta[n]>carta[n+1]
//            //Y finalmente, comprobar si hay que cambiar también la última
//
//            //Tratar el caso
//            //Leer la 1a carta
//            int cartaSiguiente= cartas[numCartas-1];
//            int minCartaMovida = Integer.MAX_VALUE;
//            int movimientos = 0;
//
//            //Leer el resto de cartas
//            for (int i=numCartas-2; i>=0; i--) {
//                int cartaActual = cartas[i];
//                if (cartaActual > cartaSiguiente) {
//                    movimientos++;
//                    minCartaMovida = Math.min(minCartaMovida, cartaActual);
//                    //No se actualiza cartaSiguiente y así se simula el cambio
//                } else {
//                    cartaSiguiente = cartaActual;
//                }
//            }
//
//            //Última revisión: el último número es mayor que el más pequeño de los que se han movido?
//            if (cartas[numCartas-1] > minCartaMovida) {
//                movimientos++;
//            }

//            //v3
//            /*       Empezar con esperado = +INF
//             *       Recorrer el array en orden inverso
//             *       Si carta[i] <= esperado, se queda y actualizar esperado como carta[i]
//             *       Sino, deberá moverse
//             */
//
//            int esperado = Integer.MAX_VALUE;
//            int movimientos = 0;
//            for (int i=cartas.length-1; i>=0; i--) {
//                if (cartas[i] <= esperado) {
//                    esperado = cartas[i];
//                } else {
//                    movimientos++;
//                }
//            }

            //v4
            int[] sorted = cartas.clone();
            Arrays.sort(sorted);

            int i = 0; // índice en sorted
            int j = 0; // índice en cartas

            while (i < numCartas && j < numCartas) {
                if (cartas[j] == sorted[i]) {
                    i++;   // encontramos el siguiente del prefijo ordenado
                }
                j++;
            }

            // i = número de cartas que NO se mueven
            int movimientos = numCartas - i;

            //Mostrar resultado
            System.out.println(movimientos);

            numCartas = scan.nextInt();
        }
    }

}


