package CalentamientoNavideno;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 17/12/2025
 */
public class p365_EnLaColaDePapaNoel {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {

            int numNinos = scan.nextInt();
            int posAitor = scan.nextInt()-1;

            int[] regalosNinos = new int[numNinos];
            for (int i=0; i<numNinos; i++) {
                regalosNinos[i] = scan.nextInt();
            }

            //recorrer la lista de regalos y calcular el tiempo que tardará
            int cantidadRegalosAitor = regalosNinos[posAitor];
            int cantidadNinosConPapaNoel = 0;
            for (int i=0; i<posAitor; i++) {
                cantidadNinosConPapaNoel += Math.min(cantidadRegalosAitor, regalosNinos[i]);
            }
            cantidadNinosConPapaNoel += cantidadRegalosAitor;
            for (int i=posAitor+1; i<numNinos; i++) {
                cantidadNinosConPapaNoel += Math.min(cantidadRegalosAitor-1, regalosNinos[i]);
            }

            //2 minutos por cada niño. Mostrar el resultado
            System.out.println(cantidadNinosConPapaNoel*2);

        }
    }

}
