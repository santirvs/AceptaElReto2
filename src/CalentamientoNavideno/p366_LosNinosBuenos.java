package CalentamientoNavideno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p366_LosNinosBuenos {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numNinos = scan.nextInt();
        while (numNinos > 0) {

            List<Integer>[] comportamiento = new List[101];
            for (int i = 0; i <= 100; i++) {
                comportamiento[i] = new ArrayList<Integer>();
            }

            while (numNinos-- > 0) {

                int bueno = scan.nextInt();
                int peso = scan.nextInt();

                comportamiento[bueno].add(peso);
            }

            //Recorrer los niños de más buenos a menos buenos
            for (int bueno=100; bueno>0; bueno--) {
                //Ordena el peso de cada comportamiento
                Collections.sort(comportamiento[bueno]);

                for (Integer peso : comportamiento[bueno]) {
                    System.out.println(bueno + " " + peso);
                }
            }

            //Separación de casos
            System.out.println();

            //Siguiente caso
            numNinos = scan.nextInt();
        }
    }

}
