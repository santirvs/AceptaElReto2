package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author santi
 * @date 13/06/2025
 */


public class p145_ElTrenDelAmor {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            //Lee la configuración del tren
            String entrada = scan.nextLine();
            //La pila simula los hombres que avanzan por el tren
            Stack<Character> pila = new Stack<Character>();
            //Contador de parejas que se forman
            int contador = 0;

            for (int i = 0; i < entrada.length(); i++) {
                if (entrada.charAt(i) == 'h' || entrada.charAt(i) == 'H') {
                    //Llegamos a un hombre. Se apila.
                    pila.push(entrada.charAt(i));
                }
                else if (entrada.charAt(i) == 'm' || entrada.charAt(i) == 'M') {
                    //LLegamos a una mujer. Buscar si forma pareja con el hombre de lo alto de la pila
                    if (!pila.isEmpty() && pila.peek() == 'h' && entrada.charAt(i) == 'm') {
                        //Se forma una pareja baja. Eliminar el hombre bajo de la cima de la pila
                        pila.pop();
                        contador++;
                    }
                    else if (!pila.isEmpty() && pila.peek() == 'H' && entrada.charAt(i) == 'M') {
                        //Se forma una pareja alta. Eliminar al hombre alto de la cima de la pila
                        pila.pop();
                        contador++;
                    }
                    else {
                        //No se forma pareja. Se hace tapón. Es equivalente a vaciar la pila.
                        pila.clear();
                    }
                }
                else if (entrada.charAt(i) == '@') {
                    //Mercancía insalvable. Se hace tapón. Es equivalente a vaciar la pila.
                    pila.clear();
                }
                else {
                    //Mercancía salvable. Los hombres de la pila avanzan en el tren.
                    //No es necesario hacer nada
                }
            }

            //Se ha llegado al final del tren se imprime el resultado
            System.out.println(contador);
        }
    }
}
 