package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.util.*;

/**
 *
 * @author santi
 * @date 13/06/2025
 */


public class p142_QuienEmpieza {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int numNinos = scanner.nextInt();
            int salto = scanner.nextInt();

            //Fin de los casos
            if (numNinos == 0 && salto == 0) break;

            //Crear una lista de numNinos
            List<Integer> lista = new ArrayList<>();
            for (int i=1; i<=numNinos; i++) {
                lista.add(i);
            }
            int posicion = -1;
            int ultimoEliminado = 0;

            //Ir eliminando cada salto
            while (lista.size() > 0) {
                posicion = (posicion + salto +1) % lista.size();
                ultimoEliminado = lista.get(posicion);
                lista.remove(posicion);
                posicion--;
            }

            //Mostrar el último eliminado
            System.out.println(ultimoEliminado);
        }

    }
}
 