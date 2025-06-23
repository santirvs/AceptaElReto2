package _24en23._2025;

import java.io.*;
import java.util.*;

/**
 *
 * @author santi
 * @date 22/06/2025
 */

// v1.- Búsqueda binaria en el espacio de soluciones para encontrar la máxima puntuación posible
    // Errores cometidos:
    // interpretar mal el enunciado. (Al no superar un enemigo, se pierde una vida pero el enemigo no se elimina y vuelve a atacar con toda la energía)
    // cantidad máxima de energía inicial es 10^12 (10^7 * 10^5), por lo que es necesario usar long, con int no es suficiente
    // usar un valor máximo de energía inicial de 10*12 + 1 para poder superar el nivel

public class p808_EnergiaDeSalida {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Leer hasta que no haya más casos
        while (sc.hasNext()) {
            // Leer el número de vidas y el número de niveles
            int vidas = sc.nextInt();
            int personajes = sc.nextInt();

            //Lectura de los niveles
            long high = 1L; // Acumulará la energía máxima necesaria para superar todos los niveles en base a los enemigos
                            // Si no hay personajes, la energía inicial es 1
            int[] nivel = new int[personajes];
            for (int i = 0; i < personajes; i++) {
                nivel[i] = sc.nextInt();
                if (nivel[i] < 0) {
                    // Si el nivel es negativo, no se puede superar
                    high-=nivel[i]; // Sumar la energía necesaria para superar el enemigo (se resta porque es negativo)
                }
            }

            long low = 1;  // Mínimo de energía inicial es 0
            long result = high;

            while (low <= high) {
                long mid = low + (high - low) / 2;
                if (superaNivel(mid, vidas, nivel)) {
                    result = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            System.out.println(result);
        }
    }


    static boolean superaNivel(long energiaInicial, int vidas, int[] eventos) {
        long energia = energiaInicial;

        for (int i = 0; i < eventos.length; i++) {
            int evento = eventos[i];

            if (evento > 0) {
                // Si el evento es positivo, se recupera energía (botiquín)
                energia = Math.min(energia + evento, energiaInicial);
            } else {
                // Si el evento es negativo, se pierde energía (enemigo)
                int enemigo = -evento;

                // Mientras la energía sea menor o igual al enemigo, se pierde una vida
                // El enemigo no pierde energía si no se le vence.
                // Volveremos a atacar al enemigo con una vida menos y la energía reiniciada al valor inicial
                while (energia <= enemigo) {
                    //Restar una vida, si llega a 0, se pierde el nivel
                    vidas--;
                    if (vidas == 0) return false;
                    // Si se pierde una vida, se reinicia la energía al valor inicial
                    energia = energiaInicial;
                }

                energia -= enemigo;
            }
        }

        // Si se llega aquí es que se ha superado el nivel
        return true;
    }
}

