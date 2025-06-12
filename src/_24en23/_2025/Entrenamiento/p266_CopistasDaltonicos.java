package _24en23._2025.Entrenamiento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class p266_CopistasDaltonicos {
    

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

                if (transformacionesInversas.containsKey(original)) {
                    //Buscar la clave que mapea al original y cambiarla
                    //char origen = transformacionesInversas.get(original);
                    //transformaciones.put(origen, cambio); //Actualizar la clave
                    //transformacionesInversas.put(cambio, origen);
                }

                transformaciones.put(original, cambio);
                //transformacionesInversas.put(cambio, original);


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

 