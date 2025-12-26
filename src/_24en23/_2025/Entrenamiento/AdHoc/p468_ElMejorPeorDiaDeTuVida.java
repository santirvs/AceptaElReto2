package _24en23._2025.Entrenamiento.AdHoc;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author srivas
 *
 * v1.- Después de mucho deducir que és exactamente lo que piden, planteo una
 *      solución lineal y me da TLE ¿¿¿???
 *
 * v2.- Bucle + lectura, sin tratamiento. A ver qué pasa con el TLE...
 *      Sigue el TLE!!!  8-O . Relectura del enunciado!
 *
 * v3.- No veo nada raro... Pruebo a leer toda la línea de golpe.
 *      Ahora da MLE!!!  200.000 números x 4 bytes = 800 KB <<  8192 KB   //>>
 *      Pero como lo leo en String pueden ser 200.000 * (10(caracteres)+8(puntero) + 1(espacio)) = 200.000 * 19 = 4.000.000 , sigue siendo menor de 8MB
 *      No entiendo nada!!!
 *
 * v4.- Copio de https://github.com/MiYazJE/Acepta-el-reto/blob/master/p468.java y analizo
 *      Es bien simple! Está claro que no entendí el enunciado. :-(
 *      Da TLE!!!
 *
 * v5.- Añado un FastReader --> Ahora da MLE!!!
 *      Cambio el FastReader con StringTokenizer por otro basado en InputStream que soporte EOF
 *
 *
 *
 */

public class p468_ElMejorPeorDiaDeTuVida {

    public static class FastReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16]; // 64 KB buffer
        private int pointer = 0;
        private int bytesRead = 0;

        public FastReader(InputStream in) {
            this.in = in;
        }

        private byte read() throws IOException {
            if (pointer >= bytesRead) {
                bytesRead = in.read(buffer);
                pointer = 0;
                if (bytesRead == -1) return -1; // EOF
            }
            return buffer[pointer++];
        }

        public Integer nextInt() throws IOException {
            int number = 0;
            byte b = read();

            // Detectar EOF antes de leer un número
            if (b == -1) return null;

            // Saltar espacios, tabs, saltos de línea
            while (b <= ' ') {
                b = read();
                if (b == -1) return null; // EOF
            }

            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = read();
            }

            while (b >= '0' && b <= '9') {
                number = number * 10 + (b - '0');
                b = read();
            }

            return negative ? -number : number;
        }

        public Long nextLong() throws IOException {
            long number = 0;
            byte b = read();
            if (b == -1) return null;

            while (b <= ' ') {
                b = read();
                if (b == -1) return null;
            }

            boolean negative = false;
            if (b == '-') {
                negative = true;
                b = read();
            }

            while (b >= '0' && b <= '9') {
                number = number * 10 + (b - '0');
                b = read();
            }

            return negative ? -number : number;
        }
    }

    public static void main(String[] args) {

        FastReader s = new FastReader(System.in);

        Integer casos;
        int diferencia;
        int dia;
        int dia2;

        try {
            while ((casos = s.nextInt()) != null) { // null indica EOF

                //casos = s.nextInt();
                dia = s.nextInt();

                diferencia = 0;

                for (int i = 1; i < casos; i++) {

                    dia2 = s.nextInt();

                    if (dia2 < dia) dia = dia2;
                    else diferencia = Math.max(diferencia, dia2 - dia);

                }

                System.out.println(diferencia);

            }
        }
        catch (Exception e) {
            //Final del fichero
        }

    }

}


/*
public class p468_ElPeorMejorDiaDeMiVida {


    public static void main(String[] args) {

        //Scanner s = new Scanner(System.in);
        FastReader1 s = new FastReader1();

        int cantDias;
        int diferencia;
        int diaPeor;
        int dia;

        //Mientras hayan datos  (número de cantDias indefinidos)
        while (s.hasNext()) {

            //Leer la cantidad de días
            cantDias = s.nextInt();
            //Diferencia respecto al primer día
            diferencia = 0;

            //Ignoro todas las lecturas
            for (int i=0; i< cantDias; i++)
                s.nextInt();


//            //El primer día ya es el peor
//            diaPeor = Integer.parseInt(restoDias[0]);
//            //Leer el resto de días
//            for (int i = 1; i < cantDias; i++) {
//                //Lee el dia
//                dia = Integer.parseInt(restoDias[i]);
//
//                //Si el dia es menor que el diaPeor, ahora éste pasará a ser el peor
//                //sino, se busca el máximo entre la diferencia actual y la diferencia entre el valor leído y el peor
//                if (dia < diaPeor) {
//                    diaPeor = dia;
//                } else {
//                    diferencia = Math.max(diferencia, dia - diaPeor);
//                }
//
//            }
//

//Mostrar el resultado
            System.out.println(diferencia);

        }

                }

                }
 */