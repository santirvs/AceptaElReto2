package _24en23._2025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 20/06/2025
 */

// v1.- Leer todas las monedas y ordenarlas
    //  Para cada moneda, comprobar si su valor es superior al valor acumulado de las anteriores
    //  Si es así, añadirla al resultado y contarla
    //  Si no es así, no añadirla y seguir con la siguiente

//v2.- Leer las monedas sin ordenarlas (ya viene garantizado que están ordenadas)
    // Usar siempre la primera moneda (ya que no genera conflicto con la anterior)
    // Para cada moneda, comprobar si la suma de las anteriores más la actual es menor que la siguiente
    // Usar también la última moneda (ya que no genera conflicto con la siguiente)


public class p805_EstrenandoElEuro {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int numMonedas = sc.nextInt();
            // No hay más casos
            if (numMonedas == 0) break;

            //Lectura de las monedas
            //No es necesario ordenarlas, ya que se garantiza que están ordenadas
            int[] monedas = new int[numMonedas];
            for (int i = 0; i < numMonedas; i++) {
                monedas[i] = sc.nextInt();
            }

            int numMonedasUsadas = 1;  // Siempre usamos la primera moneda
            long valorAcumulado = monedas[0];

            for (int i = 1; i < numMonedas - 1; i++) {
                if (valorAcumulado + monedas[i] < monedas[i + 1]) {
                    numMonedasUsadas++;
                    valorAcumulado += monedas[i];
                }
            }

            // La última moneda siempre se usa salvo que sea la única (que ya hemos contado)
            if (numMonedas > 1)
                numMonedasUsadas++; // Siempre podemos usar la última moneda también

            // Imprimir el resultado
            System.out.println(numMonedasUsadas);
        }

        sc.close();
    }
}




