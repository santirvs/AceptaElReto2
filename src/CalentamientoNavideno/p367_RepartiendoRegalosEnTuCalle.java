package CalentamientoNavideno;

import java.util.*;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p367_RepartiendoRegalosEnTuCalle {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {

            int portalActual = scan.nextInt();
            int numPortales = scan.nextInt();

            int[] portales = new int[numPortales];
            while (numPortales-- > 0) {
                portales[numPortales] = scan.nextInt();
            }

            Arrays.sort(portales);
            //Buscar on quedaria el portal on aterra
            int pos = 0;
            while (pos<portales.length && portales[pos] < portalActual) {
                pos++;
            }

            //Indice de los portales anterior y posterior
            int posPortalAnterior = pos -1;
            int posPortalSiguiente = pos;
            boolean primer = true;
            //Ha aparcat en un portal on ha de deixar regals?
            if (pos < portales.length && portales[pos] == portalActual) {
                System.out.print(portalActual);
                primer = false;
                posPortalSiguiente++;
            }

            //Mientras no lleguemos al algún extremo de la calle
            while (posPortalAnterior >= 0 && posPortalSiguiente < portales.length) {
                int distanciaAnterior = Math.abs( portalActual - portales[posPortalAnterior]);
                int distanciaSiguiente = Math.abs( portalActual - portales[posPortalSiguiente]);

                if (distanciaAnterior < distanciaSiguiente) {
                    portalActual = portales[posPortalAnterior];
                    posPortalAnterior--;
                } else {
                    portalActual = portales[posPortalSiguiente];
                    posPortalSiguiente++;
                }

                if (primer) primer=false;
                else System.out.print(" ");

                System.out.print(portalActual);
            }

            //Seguir hasta el otro extremo
            while (posPortalAnterior >= 0) {
                if (primer) primer=false;
                else System.out.print(" ");

                System.out.print(portales[posPortalAnterior]);
                posPortalAnterior--;
            }
            //Seguir hasta el otro extremo
            while (posPortalSiguiente < portales.length) {
                if (primer) primer=false;
                else System.out.print(" ");

                System.out.print(portales[posPortalSiguiente]);
                posPortalSiguiente++;
            }

            System.out.println();

        }
    }

}
