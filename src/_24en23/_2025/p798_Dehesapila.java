package _24en23._2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author santi
 * @date 13/06/2025
 */

public class p798_Dehesapila {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {

            int numVagones = scan.nextInt();

            //No hay mas casos
            if (numVagones == 0) break;

            //La estación es una pila
            Stack<Integer> estacion = new Stack<>();
            List<Integer> tren  = new ArrayList<>();

            //Construir el tren de llegada
            for (int i=1; i<=numVagones; i++) {
                tren.add(i);
            }

            boolean posible = true;
            for (int i=1; i<=numVagones && posible; i++) {
                int numVagon = scan.nextInt();
                //Mira si el vagón buscado está en lo alto de la pila
                if (!estacion.isEmpty() && estacion.peek() == numVagon) {
                    estacion.pop();
                }
                else {
                    //Si no lo está, vamos apilando vagones hasta encontrar el deseado
                    while (!tren.isEmpty() && tren.get(0) != numVagon) {
                        estacion.push(tren.remove(0));
                    }

                    //Si el primer vagon del tren coincide con el vagon
                    if (!tren.isEmpty() && tren.get(0) == numVagon) {
                        //Si coincide, lo elimina (simular push y pop)
                        tren.remove(0);
                    } else {
                        //Si no lo es, no es posible
                        posible = false;
                        scan.nextLine();  //Leer todos los vagones pendientes del caso
                    }
                }
            }

            //Mostrar resultado
            if (posible) System.out.println("SI");
            else System.out.println("NO");
        }
    }
}

