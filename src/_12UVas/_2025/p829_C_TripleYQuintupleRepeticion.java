package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class p829_C_TripleYQuintupleRepeticion {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Mientras no llegue un cero
        int numMovimientos = scan.nextInt();
        scan.nextLine();
        while (numMovimientos != 0) {
            //Tratar caso

            //Definir un HashMap <String, Int>
            HashMap<String, Integer> mapaBlancas = new HashMap<>();
            HashMap<String, Integer> mapaNegras = new HashMap<>();
            boolean tablas = false;
            boolean turnoBlancas = true;

            while (numMovimientos-- > 0) {
                HashMap<String, Integer> mapa;
                String mov = scan.nextLine();
                if (turnoBlancas) mapa = mapaBlancas;
                else mapa = mapaNegras;
                if (mapa.containsKey(mov)) {
                    int numVeces = mapa.get(mov);
                    if (numVeces == 4) tablas = true;
                    mapa.put(mov, numVeces+1);
                } else {
                    mapa.put(mov, 1);
                }
                //cambio del turno del jugador
                turnoBlancas = !turnoBlancas;
            }


            //Mostrar resultado
            if (tablas)
                System.out.println("SI");
            else
                System.out.println("NO");

            //Siguiente caso
            numMovimientos = scan.nextInt();
            scan.nextLine();
        }

    }

}
