package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Trabajar en segundos y finalmente convertir a HH:MM:SS
 *      Restar la duración del anuncio de las 24:00:00
 */

import java.util.Scanner;

public class p437_ElAnuncioMasCaroDelAnyo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        scan.nextLine();

        while (numCasos-- > 0) {
            String[] duracion = scan.nextLine().split(":");

            int horas = Integer.parseInt(duracion[0]);
            int minutos = Integer.parseInt(duracion[1]);
            int segundos = Integer.parseInt(duracion[2]);

            //Restar y ajustar los minutos y horas si son negativos
            int horasInicio = 24 - horas;
            int minutosInicio = 0 - minutos;
            int segundosInicio = 0 - segundos;
            if (segundosInicio < 0) { segundosInicio+=60; minutosInicio--; }
            if (minutosInicio < 0) { minutosInicio+=60; horasInicio--; }

            //Imprimir en formato HH:MM:SS
            System.out.printf("%02d:%02d:%02d\n", horasInicio, minutosInicio, segundosInicio);

        }
    }
}
