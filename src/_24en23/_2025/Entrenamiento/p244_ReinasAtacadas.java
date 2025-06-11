package _24en23._2025.Entrenamiento;

import java.util.Scanner;

/**
 *
 * @author santi
 * @date 10/06/2025
 */
public class p244_ReinasAtacadas {
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tamanyo = scan.nextInt();
        int numReinas = scan.nextInt();

        while (tamanyo!=0 && numReinas!=0) {

            int[] filas = new int[tamanyo+1];
            int[] columnas = new int[tamanyo+1];
            int[] diagonalCreciente = new int[2*tamanyo];
            int[] diagonalDecreciente = new int[2*tamanyo];
            boolean atacadas = false;

            for (int i=0; i<numReinas; i++) {
                int columna=scan.nextInt();
                int fila=scan.nextInt();

                //Marcar las casillas afectadas
                filas[fila]++;
                columnas[columna]++;
                diagonalCreciente[tamanyo-fila+columna]++;
                diagonalDecreciente[fila+columna-1]++;

                //Si alguna de las casillas afectadas > 1 --> las reinas se atacan
                if (filas[fila]>1) atacadas = true;
                if (columnas[columna] > 1) atacadas = true;
                if (diagonalCreciente[tamanyo-fila+columna] > 1) atacadas = true;
                if (diagonalDecreciente[fila+columna-1] > 1) atacadas = true;
            }

            if (atacadas) {
                System.out.println("SI");
            }
            else {
                System.out.println("NO");
            }

            //Siguiente caso
            tamanyo = scan.nextInt();
            numReinas = scan.nextInt();

        }
        
        
    }
}

 