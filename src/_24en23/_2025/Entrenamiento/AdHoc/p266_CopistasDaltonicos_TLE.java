package _24en23._2025.Entrenamiento.AdHoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 12/06/2025
 */

// v1. Hecho con String.replace  --> TLE
// v2. Cambio a char[] en lugar de String --> TLE
// v3. Minimizar los cambios!
//     Crear un diccionario y acumular las transformaciones.
//     Hacer una única transformación al final.

public class p266_CopistasDaltonicos_TLE {
    

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            int numFilas = scan.nextInt();
            int numColumnas = scan.nextInt();

            //No hay más casos
            if (numFilas == 0 && numColumnas == 0) break;

            //Definir tamanyo
            char[][] cuadro = new char[numFilas][numColumnas];

            //Leer el cuadro
            for (int i = 0; i < numFilas; i++) {
                String linea = scan.next();
                for (int j = 0; j < numColumnas; j++) {
                    cuadro[i][j] = linea.charAt(j);
                }
            }

            //Leer el número de copistas
            int numCopistas = scan.nextInt();
            HashMap<Character,Character> transformaciones = new HashMap<Character,Character>();
            HashMap<Character, List<Character>> transformacionesInversas = new HashMap<Character,List<Character>>();

            //Tratar cada copista
            //Usamos un mapa doble
            //Pero el inverso es un mapa de destino, lista de orígenes!!!

            for (int i = 0; i < numCopistas; i++) {
                char original = scan.next().charAt(0);
                char cambio = scan.next().charAt(0);

                //Si llegamos al original con alguna transformación hay que actualizar esas transformaciones
                //para que lleguen al nuevo destino
                if (transformacionesInversas.containsKey(original)) {
                    //Buscar todas las claves que llevan al original y cambiarlas
                    List<Character> origen = transformacionesInversas.get(original);
                    for (Character c: origen) {
                        transformaciones.put(c, cambio); //Actualizar la clave
                    }
                    //Añadir la nueva transformación
                    origen.add(original);
                }

                //Si ya se llegaba a este destino, hay que añadir el origen a la lista de transformaciones inversas
                if (transformacionesInversas.containsKey(cambio)) {
                    List<Character> origen = transformacionesInversas.get(cambio);
                    origen.add(original);
                }
                //Si aún no se llegaba a este destino, crear una nueva lista y añadirlo a las inversas
                else {
                    List<Character> destinos = new ArrayList<Character>() ;
                    destinos.add(original);
                    transformacionesInversas.put(cambio, destinos);
                }

                //Añadir la transformación directa, sólo si no se había transformado antes el origen
                if (!transformaciones.containsKey(original)) {
                    transformaciones.put(original, cambio);
                }
            }

            //Aplicar cambios
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    if (transformaciones.containsKey(cuadro[i][j])) {
                        cuadro[i][j] = transformaciones.get(cuadro[i][j]);
                    }
                }
            }

            //Imprimir el resultado final
            for (int i=0; i<numFilas; i++) {
                for (int j=0; j<numColumnas; j++) {
                    System.out.print(cuadro[i][j]);
                }
                System.out.println();
            }

        }

    }
}

 