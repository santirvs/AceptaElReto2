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
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class p707_LaBatallaPorLaTierraMedia_TLE {

    public static int ceilDiv(int x, int y) {
        int r = x / y;
        // if the signs are the same and modulo not zero, round up
        if ((x ^ y) >= 0 && (r * y != x)) {
            r++;
        }
        return r;
    }

    public static final class Enemigo implements Comparable {
        int numGrupo;
        int numTropas;
        int numLuchadores;

        Enemigo(int numGrupo, int numTropas, int numLuchadores) {
            this.numTropas = numTropas;
            this.numLuchadores = numLuchadores;
        }

        public int ratio() {
            return ceilDiv(numTropas, numLuchadores);
        }

        @Override
        public int compareTo(Object o) {
            Enemigo e = (Enemigo) o;
            int resultado;
            if (this.ratio() > e.ratio()) {
                resultado = -1;
            } else if (this.ratio() < e.ratio()) {
                resultado = 1;
            } else if (this.numGrupo > e.numGrupo) {
                resultado = -1;
            } else {
                resultado = 1;
            }

            return resultado;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Mientras hayan datos a la entrada
        while (scan.hasNext()) {
            int numLuchadores = scan.nextInt();
            int tiposCriaturas = scan.nextInt();

            //Leer la cantidad de criaturas y acumular
            List<Integer> cantCriaturas = new ArrayList<Integer>();
            int totalCriaturas = 0;
            for (int i = 0; i < tiposCriaturas; i++) {
                int numCriaturas = scan.nextInt();
                cantCriaturas.add(numCriaturas);
                totalCriaturas += numCriaturas;
            }

            //Reparto inicial de luchadores
            //La ratio será la estimada inicialmente o superior
            int ratioInicialLuchadores = ceilDiv(totalCriaturas, numLuchadores);

            //Guardamos los datos en una estructura de árbol que nos lo mantiene ordenado
            TreeSet<Enemigo> tsEnemigos = new TreeSet<Enemigo>();

            int numGrupo = 0;
            //Recorrer la lista con la cantidad de cada tipo y asignar la estimación inicial
            while (!cantCriaturas.isEmpty()) {
                numGrupo++;
                if (cantCriaturas.get(0) <= ratioInicialLuchadores) {
                    //La cantidad de criaturas es inferior o igual a la ratio inicial.
                    //A este grupo se le asigna un único luchador
                    //No es necesario gestionar más este grupo
                    numLuchadores--;
                } else {
                    //Calculamos cuantos luchadores necesitamos para alcanzar la ratio Inicial
                    //Este grupo sí lo guardamos para acabar de ajustar si es necesario
                    //Es posible optimizar si la ratio resultante es la misma que la inicial???
                    int cantLuchadores = ceilDiv(cantCriaturas.get(0), ratioInicialLuchadores);
                    Enemigo e = new Enemigo(numGrupo, cantCriaturas.get(0), cantLuchadores);
                    tsEnemigos.add(e);
                    numLuchadores -= cantLuchadores;
                }
                //Elimina el elemento de la lista. Ya no se usará más
                cantCriaturas.remove(0);
            }

            //Al final se habrán asignado todos los luchadores y, debido al redondeo al alza, algunos más
            //Hay que buscar aquellos grupos con una ratio menor y eliminar luchadores.
            //Si quedan con un único luchador, ya podemos eliminar el grupo (no se vuelve a insertar)
            while (numLuchadores < 0) {
                //Sacamos el enemigo con menor ratio
                Enemigo e = tsEnemigos.pollLast();
                e.numLuchadores--;
                if (e.numLuchadores > 1) {
                    tsEnemigos.add(e);
                }
                numLuchadores++;
            }

            //Al final, la ratio del primer elemento nos dará la solución al problema
            //Si no hay elementos, la solución es la ratioInicialLuchadores
            if (tsEnemigos.isEmpty())
                System.out.println(ratioInicialLuchadores);
            else
                System.out.println(tsEnemigos.first().ratio());

            //Eliminar los elementos
            tsEnemigos.clear();
        }

    }
}
