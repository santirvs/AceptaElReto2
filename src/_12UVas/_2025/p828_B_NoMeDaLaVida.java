package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *
 */

import java.util.Scanner;

public class p828_B_NoMeDaLaVida {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Mientras no llegue un cero
        int valores = scan.nextInt();
        while (valores != 0) {
            //Tratar caso. Leer todas las duraciones en segundos
            int numSegundos = 0;

            while (valores-- > 0) {
                numSegundos += scan.nextInt();
            }

            //Transformar a horas, minutos y segundos
            int numHoras = numSegundos / (60*60);
            numSegundos = numSegundos % (60*60);
            int numMinutos = numSegundos/ 60;
            numSegundos = numSegundos % 60;

            //Mostrar resultado
            System.out.printf("%02d:%02d:%02d\n", numHoras, numMinutos, numSegundos);

            //Siguiente caso
            valores = scan.nextInt();
        }

    }

}
