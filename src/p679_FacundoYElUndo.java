import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @since 27/12/2025
 *
 *
 * v1.- Recuperado un envío del 19/03/2024 - RTE
 *
 * v2.- Analizo. Detectado un error en el REDO, al escribir una nueva palabra
 *      puedo seguir recuperando anteriores. No es correcto! Añado un control de maxPos
 *      para indicar la posición máxima que se puede recuperar.
 *      --> AC!
 *
 *
 */
public class p679_FacundoYElUndo {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] palabras;

        int numCasos = scan.nextInt();

        while (numCasos > 0) {

            palabras = new String[200];
            int index = 0;
            int maxIndex = 0;

            //Leer palabra a palabra
            String palabra = scan.next().trim();

            // Al llegar un punto, se acaba el caso.
            // El punto no debe procesarse
            while (!palabra.equals(".")) {

                //Deshacer la última acción, si es que no estamos al principio
                if (palabra.equals("<")) {
                    if (index > 0) {
                        index--;
                    }
                }
                //Rehacer la acción o repetir la última
                else if (palabra.equals(">")) {
                    // Recuperar la palabra
                    if (maxIndex > index) { index++; }
                    else {
                        //Añadir la última palabra, si existe
                        if (index > 0) {
                            palabras[index] = palabras[index-1];
                            index++;
                            maxIndex = Math.max(maxIndex, index);
                        }
                    }
                } else {
                    //Nueva palabra
                    palabras[index] = palabra;
                    index++;
                    maxIndex=index;
                }

                //Siguiente palabra
                palabra = scan.next().trim();

            }

            for (int i = 0;i < index; i++) {
                if (i != 0) System.out.print(" ");
                System.out.print(palabras[i]);
            }
            System.out.println("");

            numCasos--;
        }

    }

}