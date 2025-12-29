/**
 *
 * @author santi
 *
 * v1 - Recuperado un envío anterior de 29/12/2023 con TLE
 *
 * v2 - Análisis. El planteamiento no es correcto,
 *      Se debe hacer una búsqueda binaria en el espacio de soluciones
 *      La solución estará entre 1 y el máximo de enemigos de los tipos
 *      Buscamos el punto medio:
 *        - Calcular cuantos luchadores necesito para cada tipo de enemigo: ceil(cantidadTipo / solucion)
 *        - Sumar todos los luchadores
 *        - Tengo suficientes luchadores ?
 *        -    Sí --> Buscar una solución menor, cada uno podrá luchar contra menos enemigos
 *        -    No --> Buscar una solución mayor, cada uno tendrá que luchar contra más enemigos
 *
 *       AC!!
 *
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p707_LaBatallaPorLaTierraMedia {

    public static int ceilDiv(int x, int y) {
        int r = x / y;
        // if the signs are the same and modulo not zero, round up
        if ((x ^ y) >= 0 && (r * y != x)) {
            r++;
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Mientras hayan datos a la entrada
        while (scan.hasNext()) {
            int numLuchadores = scan.nextInt();
            int tiposCriaturas = scan.nextInt();

            //Leer la cantidad de criaturas y quedarnos con el máximo
            List<Integer> cantCriaturas = new ArrayList<Integer>();
            int maxEnemigos = 0;
            for (int i = 0; i < tiposCriaturas; i++) {
                int numCriaturas = scan.nextInt();
                cantCriaturas.add(numCriaturas);
                maxEnemigos = Math.max(maxEnemigos, numCriaturas);
            }

            //Buscar la solución entre 1 y maxEnemigos
            int resultado = solucion(1, maxEnemigos, numLuchadores, cantCriaturas);

            //Imprimir el resultado
            System.out.println(resultado);

        }

    }

    public static int solucion(int izquierda, int derecha, int numLuchadores, List<Integer> cantCriaturas) {

        //Se han encontrado ambos límites? Ya tengo la solución
        if (izquierda >= derecha) {
            return derecha;
        }

        //Buscar el punto medio
        int medio = (izquierda+derecha) / 2;

        boolean posible = solucionPosible(medio, numLuchadores, cantCriaturas);
        int resultado = 0;

        if (posible) {
            //Es posible con medio, buscar una solución inferior
            resultado = solucion(izquierda, medio, numLuchadores, cantCriaturas);
        }
        else {
            //No es posible con medio, buscar una solución superior
            resultado = solucion(medio+1, derecha, numLuchadores, cantCriaturas);
        }

        return resultado;
    }

    public static boolean solucionPosible(int luchadores, int cantLuchadores, List<Integer> cantEnemigos) {
        boolean resultado = true;

        int i=0;
        int totalLuchadores=0;
        while (i < cantEnemigos.size() && resultado) {
            int numLuchadores = ceilDiv(cantEnemigos.get(i), luchadores);
            totalLuchadores += numLuchadores;
            if (totalLuchadores > cantLuchadores)
                resultado = false;
            i++;
        }

        return resultado;
    }
}