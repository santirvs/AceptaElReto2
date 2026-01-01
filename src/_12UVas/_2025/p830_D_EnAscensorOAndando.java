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
 *      Análisis de la situación: me he dejado el caso en que el ascensor va más lento que andando
 *      Optimizo los saltos
 *      TLE!!
 *
 * v4.- Probando con casos extremos veo lo siguiente:
 *      Si el ascensor va igual o más lento, tardamos lo que tardamos en ir andando
 *      Si el ascensor va más rápido, tardamos lo que tarde el ascensor en llegar al punto inicial
 *      más lo que tarde el ascensor en perseguirnos al destino (por el camino nos atrapa sin penalización)
 *      WA!
 *
 * v5.- Adapto la version de Enrique Nogal.
 *      Conclusiones:  System.err.println --> genera TLE!
 *
 *
 */
import java.util.Scanner;

public class p830_D_EnAscensorOAndando {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int origen = scan.nextInt();
        int destino = scan.nextInt();
        int ascensor = scan.nextInt();
        int tAndando = scan.nextInt();
        int tAscensor = scan.nextInt();

        while (!(origen == 0 && destino == 0 && ascensor == 0 && tAndando == 0 && tAscensor == 0)) {
            //long  respuesta = calcularAscensor(origen, destino, ascensor, tAndando, tAscensor);
//            long totalAndando = tAndando * Math.abs(origen-destino);
//            long totalAscensor = tAscensor * (Math.abs(ascensor-origen) + Math.abs(origen-destino));
//            long respuesta = Math.min(totalAndando, totalAscensor);

            int respuesta = procesaCaso(origen, destino, ascensor, tAndando, tAscensor);
            //int respuesta = calcularAscensor(origen, destino, ascensor, tAndando, tAscensor);

            System.out.println(respuesta);

            origen = scan.nextInt();
            destino = scan.nextInt();
            ascensor = scan.nextInt();
            tAndando = scan.nextInt();
            tAscensor = scan.nextInt();
        }
    }

//    private static long calcularAscensor(int origen, int destino, int ascensor, int tAndando, int tAscensor) {
//        // Opción 1: el límite máximo será hacerlo entero andando
//        int  mejorTiempo = Math.abs(origen - destino) * tAndando;
//
//        //Si el ascensor va más lento o igual que ir andando, ya tenemos la solución
//        if (tAndando <= tAscensor)
//            return mejorTiempo;
//
//        // Dirección de caminata  -->  1: sube, -1: baja
//        int dir = (destino >= origen) ? 1 : -1;
//
//        int tiempoAndando = 0;
//        int pisoActual = origen;
//
//        // Distancia que recorrerá el ascensor siguiendo las llamadas
//        int distanciaAscensor = Math.abs(ascensor - origen);
//
//        //Simular que se llama al ascensor en todas las paradas
//        //Sabemos el que ascensor va más rápido que el ir andando así que
//        //es mejor llamar en todas las paradas.
//        //Como el parar, abrir y cerrar puertas es despreciable, siempre tardará menos
//        //en venir del piso anterior.
//        //No tiene sentido esperarlo en el piso más adelantado ya que no podemos llamarlo hasta estar allí
//        //Para agilizar el cálculo, podemos bajar tantos pisos de golpe como tiempo tarde en llegar el ascensor al
//        //último piso donde se le llamó
//
//        while (true) {
//            System.err.println("Piso Actual:" + pisoActual + " - Distancia Ascensor: " + distanciaAscensor);
//
//            // Subirse al ascensor en el pisoActual y bajarse en destino
//            int tiempoAscensor = (distanciaAscensor + Math.abs(pisoActual - destino)) * tAscensor;
//            int total = tiempoAndando + tiempoAscensor;
//
//            mejorTiempo = Math.min(mejorTiempo, total);
//
//            if (pisoActual == destino) break;
//
////            // Avanzar un piso andando
////            int next = pisoActual + dir;
////            tiempoAndando += tAndando;
////            distanciaAscensor += Math.abs(pisoActual - next);
////            pisoActual = next;
//
//            //Avanzar varios pisos
//            int tiempoEnVenirElAscensor = distanciaAscensor * tAscensor;
//            int pisosAndandoMientrasLlegaAscensor = Math.min(tiempoEnVenirElAscensor / tAndando, Math.abs(pisoActual-destino));
//            int next = pisoActual + dir*pisosAndandoMientrasLlegaAscensor;
//            tiempoAndando+=pisosAndandoMientrasLlegaAscensor*tAndando;
//            distanciaAscensor += Math.abs(pisoActual - next);
//            pisoActual = next;
//
//        }
//
//        return mejorTiempo;
//    }


    private static int procesaCaso(int origen, int destino, int ascensor, int tAndando, int tAscensor) {
        int plantasFranInicio = Math.abs(destino - origen);
        int tiempoFran = plantasFranInicio * tAndando;
        int min = tiempoFran;
        if (tAndando <= tAscensor || min == 0) {
            return min;
        }
        int plantasAscensor;
        int tiempoAscensor;
        int tiempoTotal;
        int plantasFranHastaDestino;
        int plantaActual = origen;

        //Sube o baja?
        int direccion = destino > origen ? 1 : -1;

        //Bucle del número de plantas a desplazarse.
        for (int i = 0; i < plantasFranInicio; i++) {
            //numero de plantas que le faltan por caminar a Francesca
            plantasFranHastaDestino = Math.abs(destino - plantaActual);
            //tiempo empleado andando
            tiempoFran = i * tAndando;
            //numero de plantas que se ha movido el ascensor hasta la planta actual + plantasFranHastaDestino
            plantasAscensor = Math.abs(plantaActual - ascensor) + plantasFranHastaDestino;
            //tiempo empleado por el ascensor
            tiempoAscensor = plantasAscensor * tAscensor;
            //tiempo total
            tiempoTotal = tiempoAscensor + tiempoFran;
            //System.out.println("plantas FranHastaDestino: " + plantasFranHastaDestino + " plantasAscensor: " + plantasAscensor + " tiempoTotal: " + tiempoTotal);
            //Me lo guardo si es el mínimo
            if (tiempoTotal < min) {
                min = tiempoTotal;
            }
            //Pruebo con la siguiente planta
            plantaActual+=direccion;  //Incrementa o decrementa según si sube o baja
        }

        return min;
    }

}
