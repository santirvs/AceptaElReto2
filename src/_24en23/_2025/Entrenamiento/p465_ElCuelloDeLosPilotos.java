package _24en23._2025.Entrenamiento;

import java.util.Scanner;

public class p465_ElCuelloDeLosPilotos {

    enum Direccion {
        NORTE,
        SUR,
        ESTE,
        OESTE,
        INDETERMINADO
    };

    public static final char ASFALTO = '#';
    public static final char SALIDA = 'O';
    public static final char TIERRA = '.';


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            //Leer dimensiones del circuito
            int ancho = scan.nextInt();
            int alto = scan.nextInt(); scan.nextLine();
            char[][] circuito = new char[alto][ancho];

            //Leer el circuito
            for (int i=0; i<alto; i++) {
                circuito[i]=scan.nextLine().toCharArray();
            }

            int fila =0 ;
            int columna = 0;
            boolean encontrado = false;
            //Buscar la salida
            while (fila<alto && !encontrado) {
                columna = 0;
                while (columna<ancho && !encontrado) {
                    if (circuito[fila][columna] == SALIDA)
                        encontrado = true;
                    else
                        columna++;
                }
                if (!encontrado) fila++;
            }


            //Ya tenemos el circuito cargado y la salida localizada.
            //Empezamos a correr (en sentido de las agujas del reloj)
            //Suponemos que sólo podemos ir hacia un sitio.
            //La salida nunca está situada en una curva, por lo tanto tendrá circuito antes y después

            Direccion direccion;
            if (fila > 0 && fila< alto-1 &&
                    circuito[fila+1][columna] == ASFALTO &&
                    circuito[fila-1][columna] == ASFALTO) {
                //Hay circuito arriba y abajo. Asumo ir en direccion NORTE
                direccion = Direccion.NORTE;
            }
            else {
                //Si no hay circuito arriba y abajo debe haberlo a los lados.
                //Asumo ir en direccion ESTE
                direccion = Direccion.ESTE;
            }

            int giroHorario =0;
            int giroAntihorario=0;

            //Vamos avanzando y contando curvas
            do {
                //Si se puede avanzar, se avanza
                if (direccion == Direccion.NORTE && fila >0 && circuito[fila-1][columna]!=TIERRA)fila--;
                else if (direccion == Direccion.OESTE && columna > 0&& circuito[fila][columna-1]!=TIERRA) columna--;
                else if (direccion == Direccion.SUR && fila < (alto-1)&& circuito[fila+1][columna]!=TIERRA) fila++;
                else if (direccion == Direccion.ESTE && columna < (ancho-1)&& circuito[fila][columna+1]!=TIERRA) columna++;
                else {
                    //No se ha podido avanzar, llega el momento de la curva
                    //Mirar a ver a donde se puede girar y apuntar si se gira en sentido horario o antihorario
                    if (direccion == Direccion.NORTE && columna > 0 && circuito[fila][columna-1]!=TIERRA) {
                        columna--;
                        giroAntihorario++;
                        direccion = Direccion.OESTE;
                    }
                    else if (direccion == Direccion.NORTE && columna < (ancho-1) && circuito[fila][columna+1]!=TIERRA) {
                        columna++;
                        giroHorario++;
                        direccion = Direccion.ESTE;
                    }
                    else if (direccion == Direccion.SUR && columna > 0 && circuito[fila][columna-1]!=TIERRA) {
                        columna--;
                        giroHorario++;
                        direccion = Direccion.OESTE;
                    }
                    else if (direccion == Direccion.SUR && columna < (ancho-1) && circuito[fila][columna+1]!=TIERRA) {
                        columna++;
                        giroAntihorario++;
                        direccion = Direccion.ESTE;
                    }
                    else if (direccion == Direccion.ESTE && fila > 0 && circuito[fila-1][columna]!=TIERRA) {
                        fila--;
                        giroAntihorario++;
                        direccion = Direccion.NORTE;
                    }
                    else if (direccion == Direccion.ESTE && fila < (alto-1) && circuito[fila+1][columna]!=TIERRA) {
                        fila++;
                        giroHorario++;
                        direccion = Direccion.SUR;
                    }
                    else if (direccion == Direccion.OESTE && fila < (alto-1) && circuito[fila+1][columna]!=TIERRA) {
                        fila++;
                        giroAntihorario++;
                        direccion = Direccion.SUR;
                    }
                    else if (direccion == Direccion.OESTE && fila > 0 && circuito[fila-1][columna]!=TIERRA) {
                        fila--;
                        giroHorario++;
                        direccion = Direccion.NORTE;
                    }
                }
                //... hasta que volvamos a pasar por la salida
            } while (circuito[fila][columna]!=SALIDA);

            //Si la suposición fué correcta, el número de giros horarios debe ser superior al número de giros antihorarios
            //Si no es así, lo tenemos al revés
            if (giroHorario > giroAntihorario)
                System.out.println(giroAntihorario + " " + giroHorario);
            else
                System.out.println(giroHorario + " " + giroAntihorario);

        }

    }


}