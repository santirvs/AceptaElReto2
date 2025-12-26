package _24en23._2025.Entrenamiento.BuenPlanteamiento;

import java.io.*;
import java.util.*;

//Busca los posibles divisores de la primera división
//  --> Calcula la longitud del primer divisor en base al número de restos
//  --> Busca los posibles divisores de la primera división (debe estar entre 1 y 9)
//Entre los posibles divisores de la primera división, revisa los que dan toda la secuencia de restos
//Va construyendo los posibles divisores y cocientes

public class p789_LaDivisionPerdida {

    static class Solucion {
        int divisor;
        String cociente;

        Solucion(int d, String c) {
            divisor = d;
            cociente = c;
        }
    }

    // Busca los posibles divisores para un número que den el resto
    static List<Solucion> divisoresFactibles(String numStr, int resto) {
        List<Solucion> divisores = new ArrayList<>();

        int num = Integer.parseInt(numStr);
        //El problema se traduce a buscar los divisores de num-resto
        int numObjetivo = num - resto;

        //La lista de los divisores ha de estar entre 1 y 9
        for (int i = 1; i <= 9; i++) {
            if (numObjetivo % i == 0) {
                //Si es divisible, el divisor candidato es numObjetivo/i
                int candidato = numObjetivo / i;
                //Descartar los candidatos menores o igual al resto
                if (candidato > resto) {
                    divisores.add(new Solucion(candidato, Integer.toString(i)));
                }
            }
        }
        return divisores;
    }

    //Verifica las posibles soluciones
    static List<Solucion> divisoresVerificados(
            List<Solucion> posibles,
            int[] restos,
            String numStr) {

        List<Solucion> soluciones = new ArrayList<>();

        //Recorre la lista de posibles soluciones
        for (Solucion cand : posibles) {
            int divisor = cand.divisor;
            StringBuilder cociente = new StringBuilder(cand.cociente);

            boolean esValido = true;
            int iResto = 0;

            //Comprueba todos los restos
            while (esValido && iResto < restos.length - 1) {
                //El dividendo es el resto * 10 y le bajamos el siguiente digito
                int valorDiv = restos[iResto] * 10 + (numStr.charAt(iResto) - '0');

                //Si la división del valor entre el divisor no genera el siguiente resto, se descarta la solución
                if (valorDiv % divisor != restos[iResto + 1]) {
                    esValido = false;
                }

                //Actualiza el nuevo cociente
                cociente.append((char) ((valorDiv / divisor) + '0'));
                iResto++;
            }

            //Si es válido para todos los restos, queda verificado
            if (esValido) {
                soluciones.add(new Solucion(divisor, cociente.toString()));
            }
        }

        return soluciones;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String dividendo = br.readLine();
        //Salir cuando el dividendo sea 0
        while (!dividendo.equals("0")) {

            //Lectura del número de restos
            st = new StringTokenizer(br.readLine());
            int numRestos = Integer.parseInt(st.nextToken());

            //Lectura de los restos
            int[] restos = new int[numRestos];
            for (int i = 0; i < numRestos; i++) {
                restos[i] = Integer.parseInt(st.nextToken());
            }

            //Calcula la longitud del dividendo inicial
            int longDividendoInicial = dividendo.length() - numRestos + 1;

            // Calcula la lista de los posibles divisores
            List<Solucion> posiblesDivisores = divisoresFactibles(dividendo.substring(0, longDividendoInicial), restos[0]);

            // Verifica los divisiores
            List<Solucion> divisoresSol = divisoresVerificados(posiblesDivisores,restos,dividendo.substring(longDividendoInicial));

            // Construye la salida
            StringBuilder salida = new StringBuilder();
            for (int i = 0; i < divisoresSol.size(); i++) {
                Solucion s = divisoresSol.get(i);
                salida.append(s.divisor).append(" ").append(s.cociente);
                // Separa con guión las diferentes salidas
                if (i < divisoresSol.size() - 1) {
                    salida.append(" - ");
                }
            }

            System.out.println(salida);
            dividendo = br.readLine();
        }
    }
}
