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
 *  v2.- Cambio de planteamiento.
 *       Plantearlo como un Diskjstra calculando el coste de llegar a cada planta andando o en ascensor
 *       TLE!!
 *
 * v3.- Recupero la v1 y mirar de corregir el problema.
 *      No llamo al ascensor en cada planta, ya que es una pérdida de tiempo
 *      Para cada planta calcular el tiempo de
 *          -- llegar caminando
 *          -- hacer venir al ascensor a esa planta
 *          -- llegar a destino en ascensor
 *      Quedarnos con el mínimo de todos ellos.
 *      TLE! -> Iterar no parece una buena solución
 *
 * v3_corregida
 *      El TLE lo daba el System.err !!!!
 */
import java.util.Scanner;

public class p830_D_EnAscensorOAndando_v3_Corregida {

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
        // Opción 1: el límite máximo será hacerlo entero andando
        int  mejorTiempo = Math.abs(origen - destino) * tAndando;

        //Si el ascensor va más lento o igual que ir andando, ya tenemos la solución
        if (tAndando <= tAscensor)
            return mejorTiempo;

        // Dirección de caminata  -->  1: sube, -1: baja
        int dir = (destino >= origen) ? 1 : -1;

        int tiempoAndando = 0;
        int pisoActual = origen;

        // Distancia que recorrerá el ascensor siguiendo las llamadas
        int distanciaAscensor = Math.abs(ascensor - origen);

        //Simular cuanto tiempo se tarda en llamar desde cada piso
        //Llamarle desde un piso y seguir andando es una pérdida de tiempo

        boolean salir = false;
        while (!salir) {
            //System.err.println("Piso Actual:" + pisoActual + " - Distancia Ascensor: " + (Math.abs(pisoActual-ascensor));

            // Subirse al ascensor en el pisoActual y bajarse en destino
            int tiempoAscensor = (Math.abs(pisoActual-ascensor) + Math.abs(pisoActual - destino)) * tAscensor;
            int total = tiempoAndando + tiempoAscensor;

            mejorTiempo = Math.min(mejorTiempo, total);

            if (pisoActual == destino) salir = true;

            // Avanzar un piso andando
            pisoActual = pisoActual + dir;
            tiempoAndando += tAndando;
        }

        return mejorTiempo;
    }
}