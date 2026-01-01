package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Parece el típico de ventana deslizante
 *      con un ancho igual a los días que están de vacaciones
 *      y minimizando el acumulado.
 *      MLE!!
 *
 * v2.- No es necesario leerlos todos en memoria, se pueden procesar
 *      a medida que se leen
 *      WA!!
 *
 * v3.- Me he dejado un detalle!!!  Se requiere que la ventana tenga el día de menor precipitación!!!
 *      Y en caso de empate, el que acumule menos precipitación total.
 *      Sigue el WA!!
 *
 *  v4.- Cambio de enfoque
 *      MLE!!!  he caído en el mismo error de la v1
 *
 *  v5.- AC!
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

//public class p836_J_OptimizandoElForfait {
//
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//
//        //Mientras no llegue un cero
//        int numDiasTemporada = scan.nextInt();
//        int numDiasVacaciones = scan.nextInt();
//
//        while (numDiasTemporada != 0 || numDiasVacaciones!=0) {
//
//            //Guardar solo los elementos de la ventana
//            ArrayList<Long> precipitaciones = new ArrayList<>();
//
//            //Ventana inicial
//            long suma = 0L;
//            int diaMenorPrecipitacion = 0;
//            long menorPrecipitacion = Long.MAX_VALUE;
//            for (int i=1; i<=numDiasVacaciones; i++) {
//                long valor = scan.nextLong();
//                precipitaciones.add(valor);
//                suma += valor;
//                menorPrecipitacion = Math.min(menorPrecipitacion, valor);
//            }
//
//            //Empezar el desplazamiento
//            int primerDia = 1;
//            long mejorSuma = suma;
//            int minPrimerDia = 1;
//            int ultimoDia = numDiasVacaciones;
//            while (ultimoDia < numDiasTemporada) {
//                //Eliminar la precipitación del primer día
//                suma -= precipitaciones.get(0);
//                precipitaciones.remove(0);
//                //Desplazar la ventana
//                primerDia++;
//                ultimoDia++;
//                //Añadir la precipitación del último día
//                long valor = scan.nextLong();
//                suma += valor;
//                precipitaciones.add(valor);
//
//                //Actualiza el dia de menor precipitacion
//                if (valor < menorPrecipitacion) {
//                    minPrimerDia = primerDia;
//                    menorPrecipitacion = valor;
//                    mejorSuma = suma;
//                }
//
//                //Actualiza la mejor suma, si incluye el dia de mejor precipitacion
//                if (suma < mejorSuma &&  primerDia>=minPrimerDia) {
//                    minPrimerDia = primerDia;
//                    mejorSuma = suma;
//                }
//
//                //¿Cómo actualizar la ventana si hay empate en el día de menor precipitación?
//                if (valor == menorPrecipitacion && suma < mejorSuma) {
//                    minPrimerDia = primerDia;
//                    mejorSuma = suma;
//                }
//
//            }
//
//            //Mostrar resultado
//            System.out.println(minPrimerDia + " " + mejorSuma);
//
//            //Siguiente caso
//            numDiasTemporada = scan.nextInt();
//            numDiasVacaciones = scan.nextInt();
//        }
//
//    }
//
//}

import java.util.*;

public class p836_J_OptimizandoElForfait {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numDiasTemporada = scan.nextInt();
        int numDiasVacaciones = scan.nextInt();

        while (numDiasTemporada != 0 || numDiasVacaciones != 0) {

            long[] ventana = new long[numDiasVacaciones]; // ventana circular
            long sumaVentana = 0;

            Deque<Integer> dequeMin = new ArrayDeque<>(); // indices del mínimo
            int minPrimerDia = 1;
            long minDiaVentana = Long.MAX_VALUE;
            long minSumaVentana = Long.MAX_VALUE;

            // Leer ventana inicial
            for (int i = 0; i < numDiasVacaciones; i++) {
                long val = scan.nextLong();
                ventana[i] = val;
                sumaVentana += val;

                while (!dequeMin.isEmpty() && ventana[dequeMin.peekLast()] >= val) {
                    dequeMin.pollLast();
                }
                dequeMin.offerLast(i);
            }

            // Revisar ventana inicial
            minDiaVentana = ventana[dequeMin.peekFirst()];
            minSumaVentana = sumaVentana;
            minPrimerDia = 1;

            // Procesar el resto de días
            for (int i = numDiasVacaciones; i < numDiasTemporada; i++) {
                long val = scan.nextLong();

                // índice del día que sale de la ventana
                int salir = i % numDiasVacaciones;
                sumaVentana -= ventana[salir];
                sumaVentana += val;

                ventana[salir] = val;

                // Mantener deque del mínimo
                while (!dequeMin.isEmpty() && dequeMin.peekFirst() == salir) {
                    dequeMin.pollFirst();
                }
                while (!dequeMin.isEmpty() && ventana[dequeMin.peekLast()] >= val) {
                    dequeMin.pollLast();
                }
                dequeMin.offerLast(salir);

                long minActual = ventana[dequeMin.peekFirst()];
                int primerDiaActual = i - numDiasVacaciones + 2; // +2 porque días empiezan en 1

                if (minActual < minDiaVentana ||
                        (minActual == minDiaVentana && sumaVentana < minSumaVentana)) {
                    minDiaVentana = minActual;
                    minSumaVentana = sumaVentana;
                    minPrimerDia = primerDiaActual;
                }
            }

            System.out.println(minPrimerDia + " " + minSumaVentana);

            numDiasTemporada = scan.nextInt();
            numDiasVacaciones = scan.nextInt();
        }
    }
}
