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
 *      Adaptado AC!
 *
 *
 * v6.- Intento una versión no iterativa.
 *      WA!
 *
 *
 *
 */
import java.util.Scanner;

public class p830_D_EnAscensorOAndando_v6 {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int origen = scan.nextInt();
        int destino = scan.nextInt();
        int ascensor = scan.nextInt();
        int tAndando = scan.nextInt();
        int tAscensor = scan.nextInt();

        while (!(origen == 0 && destino == 0 && ascensor == 0 && tAndando == 0 && tAscensor == 0)) {
            int respuesta = procesaCaso(origen, destino, ascensor, tAndando, tAscensor);

            System.out.println(respuesta);

            origen = scan.nextInt();
            destino = scan.nextInt();
            ascensor = scan.nextInt();
            tAndando = scan.nextInt();
            tAscensor = scan.nextInt();
        }
    }

    private static int procesaCaso(int origen, int destino, int ascensor, int tAndando, int tAscensor) {

        //Si andando voy igual o más rápido que en ascensor, voy andando
        int tiempoAndando = Math.abs(origen - destino) * tAndando;
        if (tAndando <= tAscensor) return tiempoAndando;

        //A partir de aquí sabemos que el ascensor es más rápido que andando
        int resultado = 0;

        //Escenario A F D  / D F A (Francesca está entre el Destino y el Ascensor)
        if ((ascensor >= origen && origen >= destino) || (ascensor <= origen && origen <= destino)) {
            //Decido quien tarda menos, si andando o el ascensor. Si es el ascensor, le llamo y me subo cuando llegue
            int tiempoAscensor = Math.abs(ascensor - destino) * tAscensor;
            resultado= Math.min(tiempoAndando, tiempoAscensor);
        }

        //Escenario F A D / D A F (Ascensor ente Destino y Francesca)
        else if ((origen >= ascensor && ascensor >= destino) || (origen <= ascensor && ascensor <= destino)) {
            //Ya sabemos que el ascensor es más rápido, así que vamos andando hasta el ascensor y nos subimos allí,
            // o bien decido en qué piso sale a cuenta llamarle.
            // Si se estudia un poco más, se ve que o bien le llamas en el piso inicial
            // (cuando el ascensor tarda la mitad o menos que andando, caso 3 10 7 4 2)
            // o bien no vale la pena llamarle y me subo cuando llegue a la planta en la que está el ascensor. (Caso 3 10 7 3 2)
            if ((tAndando / tAscensor) >= 2) {
                int tiempoAscensor = (Math.abs(ascensor - origen) + Math.abs(origen - destino)) * tAscensor;
                resultado = tiempoAscensor;
            } else {
                tiempoAndando = Math.abs(ascensor - origen) * tAndando;
                int tiempoAscensor = Math.abs(ascensor - destino) * tAscensor;
                resultado = tiempoAscensor + tiempoAndando;
            }
        }

        //Escenario A D F / F D A (Destino ente Ascensor y Francesca)
        else if ((ascensor >= destino && destino >= origen) || (origen <= destino && destino <= ascensor)) {
            // Aquí el ascensor tiene que venir a buscarnos al piso desde el que se le llama, mientras esperamos a que llegue.
            // Seguir andando mientras llega no tiene sentido, ya que le tendríamos que volver a llamar desde otro piso.
            // Si se estudia un poco más, es un caso muy parecido al anterior y solo interesa cuando el tiempo del ascensor
            // es la mitad o menos que andando y entonces se le llama desde el piso inicial.
            if ((tAndando / tAscensor) >=2) {
                int tiempoAscensor = (Math.abs(ascensor - origen) + Math.abs(origen - destino)) * tAscensor;
                resultado = tiempoAscensor;
            } else {
                resultado = tiempoAndando;
            }
        }

        //Devuelve el resultado
        return resultado;

    }

}
