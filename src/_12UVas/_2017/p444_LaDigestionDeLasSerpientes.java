package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Parece simple...  seguro que tiene trampa!
 *      Cuando llegue un 1 se hace reset de 0s consecutivos
 *      Cuando llegue el primer 1 guardar la posición inicial
 *      Cuando llegue un 0 incrementar el número de 0s
 *      Si el número de 0s supera el umbral. Calcular longitud del periodo hasta el último 1
 *      Volver a empezar.
 *      Controlar el caso final.
 *      AC!  No hay trampa!
 *
 */
import java.util.Arrays;
import java.util.Scanner;

public class p444_LaDigestionDeLasSerpientes {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numMediciones = scan.nextInt();
        int umbral = scan.nextInt();

        while (numMediciones !=0 || umbral != 0) {
            //Nuevo caso. Reiniciar contadores
            int maximaLongitud = 0;
            int posPrimerUno = -1;
            int posUltimoUno = -1;
            int numCerosSeguidos = 0;
            int posicion =0;

            while (posicion < numMediciones) {
                int valor = scan.nextInt();
                if (valor==0) {
                    //LLega un 0, incrementar los ceros seguidos
                    numCerosSeguidos++;
                    if (numCerosSeguidos > umbral) {
                        //Si supera el umbral, se recalcula la máxima longitud
                        if (posUltimoUno >= 0) {
                            maximaLongitud = Math.max(maximaLongitud, posUltimoUno - posPrimerUno + 1);
                            posPrimerUno = -1;
                            posUltimoUno = -1;
                        }
                    }
                } else {
                    //LLega un 1
                    //Actualizar las posiciones del primer y último uno
                    if (posPrimerUno == -1) posPrimerUno = posicion;
                    posUltimoUno = posicion;
                    //Reset del numero de ceros seguidos
                    numCerosSeguidos = 0;
                }
                posicion++;
            }
            //Comprobar último tramo
            if (posPrimerUno != -1) {
                maximaLongitud = Math.max(maximaLongitud, posUltimoUno - posPrimerUno + 1);
            }

            //Imprimir el resultado
            System.out.println(maximaLongitud);

            //Siguiente caso
            numMediciones = scan.nextInt();
            umbral = scan.nextInt();
        }
    }

}


