package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Implementar el algoritmo de la suma en números muuuuuuy largos
 *      Leer la entrada y separar por millares
 *      Empezar por el final, sumar uno, controlar el acarreo y apilar
 *      Finalmente, recorrer la pila e imprimir
 *
 */

import java.util.Scanner;
import java.util.Stack;

public class p441_ContarHastaElFinal {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            //Leer el número
            String linea = scan.nextLine();
            String[] numero = linea.split("\\.");

            //Definir pila y acarreo
            int acarreo = 1; //inicialmente se suma 1
            Stack<Integer> pila = new Stack<>();

            //Recorrer el número desde el final hasta el principio
            for (int i=numero.length-1; i>=0; i--) {
                int valor = Integer.parseInt(numero[i]) + acarreo;
                if (valor == 1000) {
                    acarreo = 1;
                    valor = 0;
                } else {
                    acarreo =0;
                }
                pila.push(valor);
            }
            //Controla el último acarreo
            if (acarreo == 1) pila.push(1);

            //Extraer el primer número (se imprime sin ceros al inicio)
            int valor = pila.pop();
            System.out.print(valor);

            //Extraer el resto de numeros (se imprimen con . y ceros al inicio)
            while (!pila.isEmpty()) {
                valor = pila.pop();
                System.out.printf(".%03d", valor);
            }
            System.out.println();
        }
    }

}


