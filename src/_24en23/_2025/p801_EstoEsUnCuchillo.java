package _24en23._2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 16/06/2025
 */

public class p801_EstoEsUnCuchillo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numMalhechores = scan.nextInt();

        while (numMalhechores > 0) {

            if (numMalhechores == 1) {
                //Por si acaso hay un único malhechor, no puede haber escena
                scan.nextInt();
                System.out.println(0);
            } else {

                int tamanyoAnterior = scan.nextInt();
                int maximo = 0;
                int numEscenas = 0;

                for (int i = 2; i <= numMalhechores; i++) {
                    numEscenas++;
                    maximo = Math.max(maximo, numEscenas);

                    int tamanyo = scan.nextInt();
                    if (tamanyo > tamanyoAnterior) {
                        numEscenas = 1;
                        tamanyoAnterior = tamanyo;
                    }
                }

                System.out.println(maximo);

            }


            //Siguiente caso
            numMalhechores = scan.nextInt();
        }
    }
}

