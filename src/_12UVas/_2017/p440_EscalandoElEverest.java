package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Típico problema de las caravanas
 *      Leer las velocidades, y procesarlas a medida que se leen.
 *      Cada vez que se encuentre uno con velocidad inferior a la mínima hasta ese momento, se genera un nuevo grupo.
 *      Anotar el número de grupos, tamaño del grupo menor y tamaño del grupo mayor.
 *
 */

import java.util.Scanner;

public class p440_EscalandoElEverest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int tamanyoGrupo = scan.nextInt();
        while (tamanyoGrupo > 0) {

            int numGrupos = 1;
            int miembrosGrupo = 1;
            int velocidadGrupo = scan.nextInt();
            int maxGrupo = Integer.MIN_VALUE;
            int minGrupo = Integer.MAX_VALUE;

            for (int i = 1; i < tamanyoGrupo; i++) {
                int velocidad = scan.nextInt();
                if (velocidad < velocidadGrupo) {
                    maxGrupo = Math.max(maxGrupo, miembrosGrupo);
                    minGrupo = Math.min(minGrupo, miembrosGrupo);
                    velocidadGrupo = velocidad;
                    miembrosGrupo = 1;
                    numGrupos++;
                } else {
                    miembrosGrupo++;
                }
            }

            //Revisar último grupo
            maxGrupo = Math.max(maxGrupo, miembrosGrupo);
            minGrupo = Math.min(minGrupo, miembrosGrupo);

            //Mostrar resultado
            System.out.println(numGrupos + " " + minGrupo + " " + maxGrupo);


            //Siguiente grupo
            tamanyoGrupo = scan.nextInt();
        }
    }

}


