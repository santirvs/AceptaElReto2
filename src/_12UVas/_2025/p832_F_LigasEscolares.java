package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      BFS pero mirando de maximizar el acumulado de comida
 *      Hay que tener en cuenta la lista de casillas visitadas en cada uno de los recorridos
 *
 */

import java.math.BigInteger;
import java.util.Scanner;

public class p832_F_LigasEscolares {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Lectura del número de casos
        int numCasos = scan.nextInt();
        while (numCasos-- > 0) {
            //Tratar caso
            int numAlumnos = scan.nextInt();
            int tamanyoEquipo = scan.nextInt();

            //Calcular tamaño
            BigInteger combinaciones = combinaciones(numAlumnos, tamanyoEquipo);
            //Mostrar resultado
            System.out.println(combinaciones.toString());
        }


    }

    public static BigInteger combinaciones(int n, int k) {
        // Usar simetría
        k = Math.min(k, n - k);

        BigInteger resultado = BigInteger.ONE;

        for (int i = 1; i <= k; i++) {
            resultado = resultado
                    .multiply(BigInteger.valueOf(n - k + i))
                    .divide(BigInteger.valueOf(i));
        }

        return resultado;
    }

}
