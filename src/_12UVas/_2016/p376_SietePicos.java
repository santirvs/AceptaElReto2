package _12UVas._2016;


/**
 * @author santi
 * @since 30/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer los números en un array de tamaño n+2
 *      Cargar todos los números y copiar el 0 en n y el 1 en n+1
 *      Buscar los picos entre 1 y n+1
 */

import java.util.Scanner;

public class p376_SietePicos {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numAlturas = scan.nextInt();

        while (numAlturas > 0)  {

            //Definir el array para guardar las alturas
            int[] alturas = new int[numAlturas+2];

            //Leer los datos
            for (int i=0; i<numAlturas; i++) {
                alturas[i] = scan.nextInt();
            }

            //Añadir las dos primeras posiciones al final (para cerrar el círculo)
            alturas[numAlturas] = alturas[0];
            alturas[numAlturas+1] = alturas[1];

            //Buscar los picos
            int numPicos = 0;
            for (int i=1; i<numAlturas+1;i++) {
                if (alturas[i] > alturas[i-1] && alturas[i] > alturas[i+1]) {
                    numPicos++;
                }
            }

            //Imprimir resultado
            System.out.println(numPicos);

            //Siguiente caso
            numAlturas = scan.nextInt();

        }
    }
}
