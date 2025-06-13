package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author santi
 * @date 13/06/2025
 */


public class p197_MensajeInterceptado {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            //Lee la configuración del tren
            String entrada = scan.nextLine();

            Stack<Character> pilaPaso1 = new Stack<>();

            //Fase 1: Leer un caracter y apilar el siguiente.
            //        Finalmente vaciar la pila
            StringBuilder paso1 = new StringBuilder();
            for (int i = 0; i < entrada.length(); i++) {
                if (i%2==0) {
                    paso1.append(entrada.charAt(i));
                }
                else {
                    pilaPaso1.push(entrada.charAt(i));
                }
            }
            while (!pilaPaso1.isEmpty()) {
                paso1.append(pilaPaso1.pop());
            }

            //Fase 2: Las vocales mantienen la posición
            //    Aquello que no sea una vocal, se va apilando
            //    En el momento de encontrar una vocal, se desapila toda la pila
            String fase1 = paso1.toString();
            Stack<Character> pilaPaso2 = new Stack<>();

            StringBuilder paso2 = new StringBuilder();
            for (int i = 0; i < fase1.length(); i++) {
                if (esVocal(fase1.charAt(i))) {
                    while (!pilaPaso2.isEmpty()) {
                        paso2.append(pilaPaso2.pop());
                    }
                    paso2.append(fase1.charAt(i));
                }
                else {
                    pilaPaso2.push(fase1.charAt(i));
                }
            }
            //Si queda algo en la pila, se vacía
            while (!pilaPaso2.isEmpty()) {
                paso2.append(pilaPaso2.pop());
            }

            //Imprimir resultado
            System.out.println(entrada + " => " + paso2.toString());
        }
    }

    private static boolean esVocal(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c=='e' || c=='i' || c=='o' || c=='u') { return true; }
        else return false;
    }
}
 