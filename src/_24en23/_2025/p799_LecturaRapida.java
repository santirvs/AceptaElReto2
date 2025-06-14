package _24en23._2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 14/06/2025
 */

public class p799_LecturaRapida {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt();
        scan.nextLine(); // Consumir el salto de línea después del número de casos

        while (numCasos-- > 0) {

            String diccionario = scan.nextLine();
            String texto = scan.nextLine();

            // Dividir el diccionario en palabras
            // Crear un conjunto para las palabras del diccionario
            String[] palabrasDiccionario = diccionario.split(" ");

            // Dividir el texto en palabras
            String[] palabrasTexto = texto.split(" ");

            //Imprimir las palabras del texto, traducidas si es posible
            for (String palabra : palabrasTexto) {
                // Verificar si la palabra está en el diccionario
                if (!palabra.equals(".")) {
                    String traduccion = buscarPalabraEnDiccionario(palabra, palabrasDiccionario);
                    if (traduccion != null) {
                        System.out.print(traduccion + " ");
                    } else {
                        // Si no está, o no se puede deducir, imprimir la misma palabra
                        System.out.print(palabra + " ");
                    }
                }
                else {
                    // Si la palabra es un punto, imprimirlo directamente y salto de línea
                    System.out.println(".");
                }
            }


        }
    }

    private static String buscarPalabraEnDiccionario(String palabra, String[] palabrasDiccionario) {

        String result = null;
        int encontrados = 0; // Contador de palabras encontradas

        // Buscar la palabra en el diccionario
        for (String dicPalabra : palabrasDiccionario) {
            if (palabra.startsWith(String.valueOf(dicPalabra.charAt(0))) &&
                palabra.endsWith(String.valueOf(dicPalabra.charAt(dicPalabra.length() - 1))) &&
                palabra.length() == dicPalabra.length() &&
                interiorIgual (palabra, dicPalabra)) {
                result = dicPalabra; // Si la palabra está en el diccionario, devolverla
                encontrados++;
            }
        }

        // Si no se encuentra, o no se puede deducir, devolver null
        if (encontrados == 1) {
            return result; // Si se encontró exactamente una coincidencia, devolverla
        } else return null;


    }

    private static boolean interiorIgual(String palabra, String dicPalabra) {

        List<Character> letrasPalabra = new ArrayList<>();
        List<Character> letrasDicPalabra = new ArrayList<>();

        // Añadir las letras interiores de la palabra y del diccionario a las listas
        for (int i = 1; i < palabra.length() - 1; i++) {
            letrasPalabra.add(palabra.charAt(i));
        }

        for (int i = 1; i < dicPalabra.length() - 1; i++) {
            letrasDicPalabra.add(dicPalabra.charAt(i));
        }
        // Ordenar las listas
        Collections.sort(letrasPalabra);
        Collections.sort(letrasDicPalabra);
        // Comparar las listas
        return letrasPalabra.equals(letrasDicPalabra);

    }
}

