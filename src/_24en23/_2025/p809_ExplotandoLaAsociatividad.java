package _24en23._2025;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 23/06/2025
 */

// v1.- Usar programación dinámica con dos tablas:
//min[i][j]: número mínimo de multiplicaciones para multiplicar matrices de i a j.
//max[i][j]: número máximo de multiplicaciones para el mismo rango.
//Se intentan todos los posibles puntos de corte k entre i y j, y calcula el costo para cada combinación.
//La solución final está en min[0][n-1] y max[0][n-1]  -> AC

//Multiplicación de matrices:
// ¿Cómo se multiplica A1 × A2?
//A1 tiene dimensión 2×3
//A2 tiene dimensión 3×2
//El producto A1×A2 es posible (porque 3 = 3)
//El resultado será una matriz 2×2
    // Cada elemento de la matriz resultante se calcula como el producto de las filas de A1 por las columnas de A2
    //    R[0][0] = A1[0][0] * A2[0][0] + A1[0][1] * A2[1][0] + A1[0][2] * A2[2][0]
    //    R[0][1] = A1[0][0] * A2[0][1] + A1[0][1] * A2[1][1] + A1[0][2] * A2[2][1]
    //    R[1][0] = A1[1][0] * A2[0][0] + A1[1][1] * A2[1][0] + A1[1][2] * A2[2][0]
    //    R[1][1] = A1[1][0] * A2[0][1] + A1[1][1] * A2[1][1] + A1[1][2] * A2[2][1]
    // Para multiplicar una matriz de tamaño p×q por una de q×r, el número de multiplicaciones escalares necesarias es:
    //   p × q × r
    //   Por tanto, para el primer caso es 2 × 3 × 2 = 12

public class p809_ExplotandoLaAsociatividad {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Leer hasta que no haya más casos
        while (sc.hasNextInt()) {
            int n = sc.nextInt(); // Número de matrices
            int[] dims = new int[n + 1]; // Dimensiones a0, a1, ..., an

            //Leer las dimensiones de las matrices
            for (int i = 0; i <= n; i++) {
                dims[i] = sc.nextInt();
            }

            // Inicializar matrices para almacenar los valores mínimos y máximos
            int[][] min = new int[n][n];
            int[][] max = new int[n][n];

            // Llenar las matrices de mínimos y máximos
            for (int l = 2; l <= n; l++) {
                for (int i = 0; i <= n - l; i++) {
                    // j es el índice final de la subcadena de matrices
                    int j = i + l - 1;
                    min[i][j] = Integer.MAX_VALUE;
                    max[i][j] = Integer.MIN_VALUE;

                    // Probar todas las particiones posibles
                    // entre i y j
                    for (int k = i; k < j; k++) {
                        int cost = dims[i] * dims[k + 1] * dims[j + 1];
                        int minValue = min[i][k] + min[k + 1][j] + cost;
                        int maxValue = max[i][k] + max[k + 1][j] + cost;

                        if (minValue < min[i][j]) {
                            min[i][j] = minValue;
                        }
                        if (maxValue > max[i][j]) {
                            max[i][j] = maxValue;
                        }
                    }
                }
            }

            // El resultado es el mínimo y máximo para la cadena completa de matrices
            // que va desde 0 hasta n-1
            System.out.println(min[0][n - 1] + " " + max[0][n - 1]);
        }

        sc.close();
    }
}



