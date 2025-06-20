package _24en23._2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 20/06/2025
 */

// v1.- Leer todas las monedas y ordenarlas
    //  Para cada moneda, comprobar si su valor es superior al valor acumulado de las anteriores
    //  Si es así, añadirla al resultado y contarla
    //  Si no es así, no añadirla y seguir con la siguiente

public class p805_EstrenandoElEuro_WA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int numMonedas = sc.nextInt();
            // No hay más casos
            if (numMonedas == 0) break;

            // Leer las monedas
            List<Integer> monedas = new ArrayList<Integer>();
            for (int i = 0; i < numMonedas; i++) {
                monedas.add(sc.nextInt());
            }

            // Comprobar las monedas
            long valorAcumulado = 0;
            int numMonedasUsadas = 0;
            for (int moneda : monedas) {
                // Si la moneda es mayor que el valor acumulado, la usamos
                if (moneda > valorAcumulado) {
                    valorAcumulado += moneda;
                    numMonedasUsadas++;
                } else {
                    // Si no, la descartamos y pasamos a la siguiente
                }
            }

            // Imprimir el resultado
            System.out.println(numMonedasUsadas);

        }
        sc.close();
    }


}




