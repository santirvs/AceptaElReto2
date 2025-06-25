package _24en23._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author santi
 * @date 24/06/2025
 */

// v0-Empiezo de nuevo después varios intentos infructuosos combinando LIS

// v1-Procesar los puntos de los coches segun llegan al tren
    // Mantener una lista de los coches que se han cargado en el tren
    // Si el coche es más pesado que el último de la lista, se añade al final de la lista
    // Si el coche es más ligero que el primero de la lista, se añade al principio de la lista
    // Si el coche es más pesado que el primero de la lista y más ligero que el último,
    // se busca su posición en la lista (que estará ordenada)
    // y se reemplaza el coche en esa posición


public class p810_ElMozoDeEstacion_MLE {


    // Método para resolver el problema
    public static int resolver(int[] pesos) {
        // Lista para almacenar los coches en el tren
        List<Integer> tren = new ArrayList<>();

        for (int peso : pesos) {
            // Si el tren está vacío, añadir el coche
            if (tren.isEmpty()) {
                tren.add(peso);
            } else if (peso > tren.get(tren.size() - 1)) {
                // Si el coche es más pesado que el último, añadir al final
                tren.add(peso);
            } else if (peso < tren.get(0)) {
                // Si el coche es más ligero que el primero, añadir al principio
                tren.add(0, peso);
            } else {
                // Buscar la posición correcta para insertar el coche
                int pos = Collections.binarySearch(tren, peso);
                if (pos < 0) {
                    pos = -pos - 1; // Posición donde se debería insertar
                    if (pos -1 == 0) {
                        // Si está entre el primer coche y el segundo, lo posicionamos al principio
                        tren.set(0, peso);
                    } else {
                        // Reemplazar el coche en la posición encontrada
                        tren.set(pos, peso);
                    }
                }
            }
        }

        return tren.size(); // Devolver el número de coches en el tren
    }


        public static void main(String[] args) throws IOException {

            //Lectura de los datos con BufferedReader en lugar de Scanner
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                //Leer hasta encontrar un 0
                String line = br.readLine();
                line = line.trim();
                if (line.equals("0")) break;

                //Leer la cantidad de coches que van a llegar
                int numCoches = Integer.parseInt(line);
                //Lectura del peso de los n coches
                String[] parts = br.readLine().trim().split(" ");
                int[] pesos = new int[numCoches];
                for (int i = 0; i < numCoches; i++) {
                    pesos[i] = Integer.parseInt(parts[i]);
                }

                //Calcular la solución
                int res = resolver(pesos);
                System.out.println(res);
            }
        }
    }
