package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v0.- Ya solucionado el 14/05/2022
 *
 * v1.- Leer los datos (gasto / premio), ordenarlos de forma creciente por gasto y, en caso de empate, por premio
 *      Recorrer la ordenación y mirar de encontrar algún caso donde mayor gasto no tiene mayor premio
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p378_LaJusticiaDeLaLoteria {

    public static class Premio implements Comparable {
        public int premio;
        public int gasto;

        Premio(int gasto, int premio) {
            this.gasto = gasto;
            this.premio = premio;
        }

        @Override
        public int compareTo(Object o) {
            Premio p = (Premio) o;
            if (this.gasto == p.gasto) {
                return Integer.compare(this.premio, p.premio);
            } else {
                return Integer.compare(this.gasto, p.gasto);
            }
        }
    }



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numLocalidades = scan.nextInt();

        while (numLocalidades > 0 )  {

            //Lista de localidades
            ArrayList<Premio> lista = new ArrayList<>();

            while (numLocalidades-- > 0) {
                //Leer los datos de las localidades
                int gasto = scan.nextInt();
                int premio = scan.nextInt();

                lista.add(new Premio(gasto, premio));
            }

            //Ordenar la lista
            Collections.sort(lista);

            //Recorrer la lista buscando si alguna localidad con más gasto no ha obtenido más premio
            boolean justa = true;

            for (int i=1; i<lista.size() && justa;i++) {
                Premio actual = lista.get(i);
                Premio anterior = lista.get(i-1);
                if (actual.gasto > anterior.gasto && actual.premio <= anterior.premio) {
                    justa = false;
                }
            }

            //Mostrar el resultado
            if (justa)
                System.out.println("SI");
            else
                System.out.println("NO");

            numLocalidades = scan.nextInt();
        }
    }
}
