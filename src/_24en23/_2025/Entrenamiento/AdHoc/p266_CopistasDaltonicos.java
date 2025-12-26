package _24en23._2025.Entrenamiento.AdHoc;
import java.util.*;

public class p266_CopistasDaltonicos {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int filas = sc.nextInt();
            int columnas = sc.nextInt();

            while (filas != 0 && columnas != 0) {
                char[][] imagen = new char[filas][columnas];

                // Leer la imagen
                for (int i = 0; i < filas; i++) {
                    String linea = sc.next();
                    for (int j = 0; j < columnas; j++) {
                        imagen[i][j] = linea.charAt(j);
                    }
                }

                int copistas = sc.nextInt();

                // Mapa que almacena para cada color original cuál es su color final
                Map<Character, Character> colorAFinal = new HashMap<>();

                // Inicializamos el mapa: cada color inicialmente apunta a sí mismo
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        char color = imagen[i][j];
                        if (!colorAFinal.containsKey(color)) {
                            colorAFinal.put(color, color);
                        }
                    }
                }

                // Procesar las sustituciones de copistas
                for (int i = 0; i < copistas; i++) {
                    char colorAntiguo = sc.next().charAt(0);
                    char colorNuevo = sc.next().charAt(0);

                    // Actualizamos todos los colores que actualmente apuntan a colorAntiguo
                    for (Map.Entry<Character, Character> entry : colorAFinal.entrySet()) {
                        if (entry.getValue() == colorAntiguo) {
                            entry.setValue(colorNuevo);
                        }
                    }
                }

                // Imprimir la imagen final reemplazando cada color por su color final
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        System.out.print(colorAFinal.get(imagen[i][j]));
                    }
                    System.out.println();
                }

                // Leer siguiente caso
                filas = sc.nextInt();
                columnas = sc.nextInt();
            }

            sc.close();
        }
    }
