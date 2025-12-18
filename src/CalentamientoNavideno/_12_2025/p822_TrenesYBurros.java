package CalentamientoNavideno._12_2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p822_TrenesYBurros {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numTrayectos = scan.nextInt();

        while (numTrayectos > 0) {

            int[] trenes = new int[numTrayectos - 1];
            int[] burros = new int[numTrayectos - 1];

            for (int i = 0; i < numTrayectos - 1; i++) {
                trenes[i] = scan.nextInt();
            }
            for (int i = 0; i < numTrayectos - 1; i++) {
                burros[i] = scan.nextInt();
            }

            int trayectos = 0;
            for (int i = 0; i < numTrayectos - 1; i++) {
                int tiempoTrenes = trenes[i];
                int tiempoBurros = burros[i];
                if (tiempoBurros < tiempoTrenes) trayectos++;
                for (int j = i + 1; j < numTrayectos - 1; j++) {
                    tiempoTrenes += trenes[j];
                    tiempoBurros += burros[j];
                    if (tiempoBurros < tiempoTrenes) trayectos++;
                }
            }

            System.out.println(trayectos);

            numTrayectos = scan.nextInt();
        }


    }


}
