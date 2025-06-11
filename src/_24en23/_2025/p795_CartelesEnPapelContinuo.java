package _24en23._2025;

import java.util.Scanner;

public class p795_CartelesEnPapelContinuo {


    public static void imprimirBordeFila(int lineas) {
        System.out.print("-");
        for (int i=0; i<lineas; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int columnas = scan.nextInt();
        int lineas = scan.nextInt();
        scan.nextLine();

        while (columnas!=0 || lineas!=0) {
            char[][] papel = new char[columnas][lineas];

            //Ignorar la primera línea
            scan.nextLine();

            //Leer las filas del cartel y almacenarlas en el papel con las coordenadas giradas 90º en sentido antihorario
            for (int linea=0; linea<lineas; linea++) {
                String lineaCartel = scan.nextLine();
                for (int columna=1; columna<=columnas; columna++) {
                    papel[columnas-columna][linea] = lineaCartel.charAt(columna);
                }
            }


            //Ignorar la última línea
            scan.nextLine();


            //Imprimir el papel
            imprimirBordeFila(lineas);
            for (int columna=0; columna<columnas; columna++) {
                System.out.print("|");
                for (int linea=0; linea<lineas; linea++) {
                    System.out.print(papel[columna][linea]);
                }
                System.out.println("|");
            }
            imprimirBordeFila(lineas);

            //Siguiente caso
            columnas = scan.nextInt();
            lineas = scan.nextInt();
            scan.nextLine();
        }


    }
}
