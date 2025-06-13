package _24en23._2025;

import java.util.Scanner;

public class p797_AnunciosDeMoviles {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {

            int numFilas = scan.nextInt();
            int numColumnas = scan.nextInt();

            //No hay mas casos
            if (numFilas == 0 && numColumnas == 0) break;

            int numCambios = 0;
            int numApagados = 0;
            int numEncendidos = 0;
            int numReiniciosAEncendidos = 0;

            //Leer las imágenes iniciales y deseadas
            for (int i = 0; i < numFilas; i++) {
                String inicial =scan.next();
                String imagen = scan.next();

                //Comparar cada posición
                for (int j = 0; j < numColumnas; j++) {
                    if (inicial.charAt(j) == 'R') {
                        //Se reinicia el móvil al estado que se precise
                        numCambios++;
                        if (imagen.charAt(j) == '0') {
                            //Si se reinicia a apagado, me lo apunto por si puede intercambiarse por un apagado que necesite encenderse
                            numReiniciosAEncendidos++;
                        }

                    }
                    else if (inicial.charAt(j) != imagen.charAt(j)) {
                        //No son iguales, me guardo el estado inicial de los móviles
                        if (inicial.charAt(j) == '0') {
                            numApagados++;
                        }
                        else {
                            numEncendidos++;
                        }
                    }
                }
            }

            //Intercambiar los encendidos con los apagados
            int numIntercambios = Math.min(numEncendidos, numApagados);
            //Cada intercambio es un nuevo cambio
            numCambios += numIntercambios;
            //Restamos los encendidos y apagados disponibles
            numEncendidos -= numIntercambios;
            numApagados -= numIntercambios;

            //Ahora, o bien los encendidos son 0 o bien los apagados son 0, o ambos son 0

            //Si sobran apagados, éstos no podrán encenderse, por lo tanto es imposible
            if (numApagados > 0) {
                if (numApagados > numReiniciosAEncendidos) {
                    System.out.println("IMPOSIBLE");
                }
                else {
                    //Si hay apagados, pero se pueden encender con los reinicios, sumamos los apagados
                    System.out.println((numCambios + numApagados));
                }
            }
            else {
                System.out.println((numCambios + numEncendidos));
            }

        }
    }
}

