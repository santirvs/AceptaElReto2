package _24en23._2025.Entrenamiento.EstructurasDatos;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author santi
 * @date 05/03/2025
 */


public class p139_NumerosCubinfinitos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        while (num != 0) {
            Set<Long> numeros = new HashSet<>();
            boolean encontrado = false;
            System.out.print(num);
            while (!encontrado) {
                numeros.add(num);

                long num2 = num;
                long suma = 0L;

                while (num2 > 0) {
                    long digit = num2 %10;
                    suma += (digit * digit * digit);
                    num2 = num2 / 10;
                }

//                long suma = 0L;
//                for (String d : digitos) {
//                    int digito = Integer.parseInt(d);
//                    suma += (long) digito * digito * digito;
//                }

                if (num == 1) {
                    System.out.println(" -> cubifinito.");
                    encontrado = true;
                } else if (numeros.contains(suma)) {
                    System.out.print(" - " + suma);
                    System.out.println(" -> no cubifinito.");
                    encontrado = true;
                } else {
                    System.out.print(" - " + suma);
                    num = suma;
                }
            }
            num = scanner.nextLong();
        }
    }
}
 