package CalentamientoNavideno._12_2025;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 18/12/2025
 */
public class p825_ElSueldoDelMaquinista {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();

        while (numCasos-- > 0) {
            int toneladas = scan.nextInt();
            int millas = scan.nextInt();

            int peniquesToneladaYMilla = scan.nextInt();

            int combustibleMilla = scan.nextInt();
            int costeMantenimiento = scan.nextInt();
            int salario = scan.nextInt();


            int totalIngresos = 0;
            int totalGastos = 0;

            totalIngresos = toneladas * millas * peniquesToneladaYMilla;

            totalGastos = combustibleMilla * millas * 2 + costeMantenimiento + salario;

            System.out.println(totalIngresos - totalGastos);


        }


    }


}
