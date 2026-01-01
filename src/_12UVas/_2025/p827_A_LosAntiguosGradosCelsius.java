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
import java.util.*;

public class p827_A_LosAntiguosGradosCelsius {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Lectura del número de casos
        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {
            //Tratar caso
            int grados = scan.nextInt();
            int celsiusOriginales = 100-grados;


            //Mostrar resultado
            System.out.println(celsiusOriginales);
        }


    }

}
