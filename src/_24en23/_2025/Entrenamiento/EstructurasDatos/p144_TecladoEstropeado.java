package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 13/06/2025
 */


public class p144_TecladoEstropeado {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            String entrada = scan.nextLine();
            List<Character> salida = new ArrayList<>();
            int posicion=0;

            for (int i = 0; i < entrada.length(); i++) {
                char c = entrada.charAt(i);
                if (c == '-') {
                    //Inicio
                    posicion = 0;
                }
                else if (c == '+') {
                    //Final
                    posicion = salida.size();
                }
                else if (c == '*') {
                    //Derecha
                    if (posicion < salida.size())
                        posicion++;
                }
                else if (c == '3') {
                    //Suprimir
                    if (posicion < salida.size()) {
                        salida.remove(posicion);
                    }
                }
                else {
                    //Añadir caracter en la posición
                    salida.add(posicion,c);
                    posicion++;
                }

            }

            //Imprimir la salida
            for (Character c : salida) {
                System.out.print(c);
            }
            System.out.println();
        }

    }
}
 