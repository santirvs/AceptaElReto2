package _12UVas._2017;

/**
 * @author santi
 * @since 29/12/2025
 *
 * Preparando las 12 UVas de 2025
 */

/**
 * v1.- Leer el caso. Determinar la notación y transformar a la notación deseada
 *      Si contiene _ es snake_case
 *      Si contiene - es kebab-case
 *      sino es CamelCase
 *
 */

import java.util.Scanner;
import java.util.Stack;

public class p442_CamellosSerpientesKebabs {

    public static final String SNAKE = "snake_case";
    public static final String KEBAB = "kebab-case";
    public static final String CAMEL = "CamelCase";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            //Leer la línea
            String[] linea = scan.nextLine().split(" ");
            String variable = linea[0];
            String formatoDeseado = linea[1];

            String formatoOriginal = determinarFormato(variable);

            String variableTransformada = transformarFormato(variable, formatoOriginal, formatoDeseado);

            System.out.println(variableTransformada);
        }
    }

    public static String determinarFormato(String variable) {
        String resultado = "";

        if (variable.contains("-")) resultado = KEBAB;
        else if (variable.contains("_")) resultado = SNAKE;
        else if (Character.isUpperCase(variable.charAt(0))) resultado = CAMEL;
        else resultado = SNAKE;  // Es indiferente que sea SNAKE o KEBAB, lo importante es que no es CAMEL

        return resultado;
    }

    public static String transformarFormato(String variable, String formatoOriginal, String formatoDestino) {

        //De entrada se le asigna el mismo valor, no sea que se pida transformar al mismo formato
        String variableTransformada = variable;

        if (!formatoDestino.equals(formatoOriginal)) {
            //Ahora ya estamos seguros que será un formato diferente
            if (formatoOriginal.equals(SNAKE)) {
                if (formatoDestino.equals(KEBAB)) {
                    //Cambiar - por _
                    variableTransformada = variable.replace('_', '-');
                } else {
                    variableTransformada = transformarToCamel(variable, "_");
                }
            } else if (formatoOriginal.equals(KEBAB)) {
                if (formatoDestino.equals(SNAKE)) {
                    //Cambiar _ por -
                    variableTransformada = variable.replace('-', '_');
                } else {
                    variableTransformada = transformarToCamel(variable, "-");
                }
            } else { // el formato original es Camel
                if (formatoDestino.equals(SNAKE)) {
                    variableTransformada = transformarFromCamel(variable, "_");
                } else {
                    variableTransformada = transformarFromCamel(variable, "-");
                }
            }
        }
        return variableTransformada;
    }



    public static String transformarToCamel(String variable, String patron) {
        //Cambiar - por mayúscula
        String[] variablePartes = variable.split(patron);
        StringBuilder transform = new StringBuilder();

        for (String p : variablePartes) {
            char primerCaracter = Character.toUpperCase(p.charAt(0));
            transform.append(primerCaracter);
            transform.append(p.substring(1));
        }
        return transform.toString();
    }

    public static String transformarFromCamel(String variable, String patron) {
        StringBuilder transform = new StringBuilder();

        transform.append( Character.toLowerCase(variable.charAt(0) ));

        for (int pos=1; pos<variable.length(); pos++) {
            char c = variable.charAt(pos);
            if (Character.isUpperCase(c)) {
                transform.append(patron);
                transform.append(Character.toLowerCase(c));
            } else {
                transform.append(c);
            }
        }

        return transform.toString();
    }

}


