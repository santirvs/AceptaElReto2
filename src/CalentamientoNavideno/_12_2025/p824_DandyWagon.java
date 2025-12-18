package CalentamientoNavideno._12_2025;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p824_DandyWagon {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numTramos = scan.nextInt();

        while (numTramos > 0) {

            int inercia = 0;
            int tramosDescanso = 0;
            int maxTramosDescanso = 0;

            while (numTramos-- > 0) {
                int inerciaTramo = scan.nextInt();
                //Si el tramo es llano y no hay inercia acumulada
                if (inercia == 0 && inerciaTramo ==0) {
                    tramosDescanso = 0;
                }
                else {
                    //Sumar o restar inercia
                    inercia += inerciaTramo;
                    if (inercia < 0) {
                        //No hay inercia para superar el tramo. Deben usarse caballos
                        tramosDescanso = 0;
                        inercia = 0;
                    } else {
                        //Hay suficiente inercia para que sigan descansando los caballos
                        tramosDescanso++;
                        maxTramosDescanso = Math.max(maxTramosDescanso, tramosDescanso);
                    }
                }
            }


            System.out.println(maxTramosDescanso);

            numTramos = scan.nextInt();

        }


    }


}
