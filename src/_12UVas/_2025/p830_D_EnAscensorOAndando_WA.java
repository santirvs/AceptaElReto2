package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Caminar hacia la planta de destino llamando al ascensor en cada planta si no es que nos acercamos a él
 *      Calcular lo que tardará en llegar el ascensor a la planta
 *      Esperarlo y subir a él
 *      O bien seguir caminando.
 *      WA!
 *

 *
 */
import java.util.Scanner;

public class p830_D_EnAscensorOAndando_WA {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int origen = scan.nextInt();
        int destino = scan.nextInt();
        int ascensor = scan.nextInt();
        int tAndando = scan.nextInt();
        int tAscensor = scan.nextInt();

        while (!(origen == 0 && destino == 0 && ascensor == 0 && tAndando == 0 && tAscensor == 0)) {
            long  respuesta = calcularAscensor(origen, destino, ascensor, tAndando, tAscensor);
            System.out.println(respuesta);

            origen = scan.nextInt();
            destino = scan.nextInt();
            ascensor = scan.nextInt();
            tAndando = scan.nextInt();
            tAscensor = scan.nextInt();
        }
    }

    private static long calcularAscensor(int origen, int destino, int ascensor, int tAndando, int tAscensor) {
        // Opción 1: hacerlo entero andando
        long mejorTiempo = (long) Math.abs(origen - destino) * tAndando;

        // Dirección de caminata
        int dir = (destino >= origen) ? 1 : -1;

        long tiempoAndando = 0;
        int pisoActual = origen;

        // Distancia que recorrerá el ascensor siguiendo las llamadas
        long distanciaAscensor = Math.abs(ascensor - origen);

        while (true) {
            // Subirse al ascensor en el pisoActual y bajarse en destino
            long tiempoAscensor = (distanciaAscensor + Math.abs(pisoActual - destino)) * tAscensor;
            long total = tiempoAndando + tiempoAscensor;

            mejorTiempo = Math.min(mejorTiempo, total);

            if (pisoActual == destino) break;

            // Avanzar un piso andando
            int next = pisoActual + dir;
            tiempoAndando += tAndando;
            distanciaAscensor += Math.abs(pisoActual - next);
            pisoActual = next;
        }

        return mejorTiempo;
    }
}
