/**
 *
 * @author santi
 * @since 27/12/2025
 *
 *
 * v1.- Recuperado un envío del 19/03/2024 - RTE
 *
 * v2.- Analizo. Parece que el único error era el tratamiento del EOF
 *
 *
 */
import java.util.Arrays;
import java.util.Scanner;

public class p681_LaEscaleraDeAlba {



    public static void main(String[] args) {

        Scanner scan = new Scanner (System.in);

        while (scan.hasNext()) {

            //Leer el número de piezas
            int numColores = scan.nextInt();
            //Definir un array del tamaño suficiente y leer la cantidad de piezas
            int[] piezas = new int[numColores];

            for (int i =0; i<numColores; i++)
                piezas[i] = scan.nextInt();

            //Ordenar el array (ascendente)
            Arrays.sort(piezas);

            //Recorrer el array en sentido inverso
            long totalPiezas = piezas[piezas.length-1];
            int numPiezasNivelAnterior = piezas[piezas.length-1];

            for (int i=piezas.length-2; i>=0; i--)
            {
                //Si el siguiente color tiene menos piezas
                if (piezas[i] < numPiezasNivelAnterior)
                {
                    //El nuevo nivel se hará con todas las piezas
                    totalPiezas+=piezas[i];
                    numPiezasNivelAnterior = piezas[i];
                }
                // Si el siguiente color tiene el mismo número de piezas (nunca puede ser más, ya que está ordenado)
                else {
                    //El nuevo nivel se hará con una pieza menos, salvo que ya no se puedan hacer más niveles
                    if (numPiezasNivelAnterior>0) {
                        numPiezasNivelAnterior--;
                        totalPiezas += numPiezasNivelAnterior;
                    }
                }
            }

            //Imprimir el resultado
            System.out.println(totalPiezas);
        }



    }

}