package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Contar las ollas que se necesitan para que quepan todos los huevos y multiplicar por 10
 */

import java.util.Scanner;

public class p368_CociendoHuevos {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int huevos = scan.nextInt();
        int capacidad = scan.nextInt();
        while (huevos!=0 || capacidad!=0) {
            int ollasNecesarias = huevos / capacidad;
            if (ollasNecesarias*capacidad < huevos) ollasNecesarias++;

            //10 minutos por cada olla
            System.out.println(ollasNecesarias*10);

            //Siguiente caso
            huevos = scan.nextInt();
            capacidad = scan.nextInt();
        }
    }
}
