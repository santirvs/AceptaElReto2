package _24en23._2025;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 01/07/2025
 */

public class p817_ElChatarrerooooo {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                int n = sc.nextInt(); // número de piezas
                if (n == 0) break;    // Caso sin piezas: final del caso de pruebas

                double totalArea = 0.0;

                for (int i = 0; i < n; i++) {
                    int v = sc.nextInt(); // número de vértices
                    //Array donde se guardan las coordenadas de los vértices
                    int[] x = new int[v];
                    int[] y = new int[v];

                    // Leer las coordenadas de los vértices
                    for (int j = 0; j < v; j++) {
                        x[j] = sc.nextInt();
                        y[j] = sc.nextInt();
                    }

                    //Suma el área del polígono actual al total
                    totalArea += Math.abs(polygonArea(x, y, v));
                }

                // Evita notación científica
                if (totalArea == (long) totalArea) {
                    //Si es un entero, se imprime como long (evitará notación científica)
                    System.out.println((long) totalArea);
                } else {
                    //Si no es entero, se imprime con 1 decimal que solo podrá ser .5
                    System.out.println((long) totalArea+".5");
                }
            }

            sc.close();
        }

        // Calcula el área usando la fórmula del zapato (shoelace)
        public static double polygonArea(int[] x, int[] y, int n) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                int j = (i + 1) % n;
                sum += (long)x[i] * y[j] - (long)x[j] * y[i];
            }
            return 0.5 * sum;
        }
    }
