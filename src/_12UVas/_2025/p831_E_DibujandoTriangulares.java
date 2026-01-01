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

public class p831_E_DibujandoTriangulares {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Lectura del número de casos
        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {
            //Tratar caso
            int linea = 1;
            int numInicial = 1;
            int numLinea = scan.nextInt();
            while (linea < numLinea) {
                numInicial += linea;
                linea++;
            }

            //Aquí sabemos el numInicial de la línea que se busca
            //y sabemos que la línea tiene linea numeros, por lo tanto linea-1 espacios
            //faltará contar el tamaño de cada número
            int numCaracteres = linea-1;  //espacios de separación
            for (int i=numInicial; i<=numInicial+linea-1;i++) {
                numCaracteres += (""+i).toString().length();
            }



            //Mostrar resultado
            System.out.println(numCaracteres);
        }



    }

}
