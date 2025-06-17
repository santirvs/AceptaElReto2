package _24en23._2025.Entrenamiento.AlgoritmosRecursivos;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 17/06/2025
 */


public class p136_EncadenandoTrolls {

        public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);

            while (true) {
                int fuerzaHobbit = scan.nextInt();
                if (fuerzaHobbit == 0) break; // fin de entrada

                int tamanyoCadena = scan.nextInt();

                int numCortes = resolver(fuerzaHobbit, tamanyoCadena);

                System.out.println(numCortes);

            }

        }

    private static int resolver(int fuerzaHobbit, int tamanyoCadena) {


            //Si el enano (que tiene el doble de fuerza que un hobbit) puede con la cadena no hay que trocearla
            if (tamanyoCadena <= 2*fuerzaHobbit) {
                return 0;
            }

            //Se trocea la cadena en dos partes: 1/3 y 2/3
            int tamanyoParte1 = tamanyoCadena / 3;
            int tamanyoParte2 = tamanyoCadena - tamanyoParte1;

            return 1 + resolver(fuerzaHobbit, tamanyoParte1) + resolver(fuerzaHobbit, tamanyoParte2);

    }

}
 