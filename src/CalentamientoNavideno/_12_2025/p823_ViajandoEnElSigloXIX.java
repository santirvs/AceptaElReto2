package CalentamientoNavideno._12_2025;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p823_ViajandoEnElSigloXIX {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {

            int normalDentro = scan.nextInt();
            int normalFuera = scan.nextInt();
            int comodoDento = scan.nextInt();
            int comodoFuera = scan.nextInt();

            // CONVERSIONES
            // 1 LIBRA --> 20 CHELINES  // 1 CHELIN --> 12 PENIQUES
            // 1 LIBRA --> 20 * 12 = 240 PENIQUES

            // PRECIOS
            // normalDentro --> 1 CHELIN --> 12 PENIQUES
            // normalFuera --> 9 PENIQUES
            // comoddDentro --> 1 CHELIN + 6 PENIQUES --> 18 PENIQUES
            // comodoFuera --> 1 CHELIN --> 12 PENIQUES

            int totalCostePeniques =
                    normalDentro * 12
                    + normalFuera * 9
                    + comodoDento * 18
                    + comodoFuera * 12;


            // Calcular libras, chelines y peniques
            int totalLibras = totalCostePeniques / 240;
            totalCostePeniques = totalCostePeniques %240;
            int totalChelines = totalCostePeniques / 12;
            int totalPeniques = totalCostePeniques % 12;

            //Mostrar el resultado
            System.out.println(totalLibras + " " + totalChelines + " " + totalPeniques);


        }


    }


}
