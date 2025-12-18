package CalentamientoNavideno._12_2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p821_OrdenandoRamales {

    static class Ramal implements Comparable {
        String nombre;
        int distancia;
        char lado;

        Ramal(String nombre, int distancia, char lado) {
            this.nombre = nombre;
            this.distancia = distancia;
            this.lado = lado;
        }


        @Override
        public int compareTo(Object o) {
            Ramal r1 = (Ramal) o;
            if (this.distancia == r1.distancia) {
                return this.lado < r1.lado ? 1 : -1;
            } else {
                return this.distancia < r1.distancia ? -1 : 1;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numRamales = scan.nextInt();


        while (numRamales != 0) {
            List<Ramal> ramales = new ArrayList<Ramal>();
            while (numRamales-- > 0) {

                String nom = scan.next();
                char lado = scan.next().charAt(0);
                int distancia = scan.nextInt();

                ramales.add(new Ramal(nom, distancia, lado));
            }

            // Ordenar los ramales
            Collections.sort(ramales);

            //Imprimir los ramales ordenados
            for (Ramal r : ramales) {
                System.out.println(r.nombre);
            }
            System.out.println("---");


            numRamales = scan.nextInt();
        }
    }


}
