package _24en23._2025.Entrenamiento.ProblemasMatematicos;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author santi
 * @date 30/10/2021
 */


public class p190_DividirFactoriales {

        public static void main(String[] args) {

            Scanner scan = new Scanner(System.in);
            long numerador = 1;
            long denominador = 1;
            long resultado = 1;

            while (numerador >= denominador) {
                numerador = scan.nextInt();
                denominador = scan.nextInt();
                resultado = 1;
                if (numerador >= denominador) {
                    for (long i = numerador; i > denominador; i--) {
                        resultado *= i;
                    }
                    System.out.println(resultado);
                    //System.out.println(Long.MAX_VALUE);
                }
            }

        }

    }
 