package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer la entrada, parsearla y aplicar la fórmula.
 *      No es necesario vigilar decimales, ya que se garantiza que el resultado es entero
 */

import java.util.Scanner;

public class p439_VelocidadDesplazamientoTiempo {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        scan.nextLine();

        while (numCasos-- > 0) {
            String[] datos = scan.nextLine().toUpperCase().split(" ");

            //Si los valores son 0 significan que no están informados
            int velocidad = 0;
            int desplazamiento = 0;
            int tiempo = 0;

            //Lectura de los datos
            for (int i=0; i<2;i++) {
                String[] dato = datos[i].split("=");
                int valor = Integer.parseInt(dato[1]);
                if (dato[0].equals("D"))
                    desplazamiento = valor;
                else if (dato[0].equals("T"))
                    tiempo = valor;
                else velocidad = valor;
            }

            //Calculo del resultado
            if (desplazamiento==0) {
                desplazamiento = velocidad * tiempo;
                System.out.println("D="+desplazamiento);
            } else if (velocidad==0) {
                velocidad = desplazamiento / tiempo;
                System.out.println("V=" + velocidad);
            } else {
                tiempo = desplazamiento / velocidad;
                System.out.println("T="+tiempo);
            }

        }
    }
}
