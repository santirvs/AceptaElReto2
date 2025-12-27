import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

/**
 *
 * @author santi
 * @since 27/12/2025
 *
 *
 * v1.- Recuperado un envío del 03/01/2023 - MLE
 *
 * v2.- Duplicaba los datos, primero en un array y luego en una lista.
 *      Elimino el array y lo cargo directamente en la lista - WA
 *
 * v3.- Reorganizar en 2 pasos - AC
 *
 *
 */
public class p671_AbuelosFelices {

    static class Rango implements Comparable {
        int tiempo;
        char tipo;

        Rango(int time, char type) {
            this.tiempo = time;
            this.tipo = type;
        }

        @Override
        public int compareTo(Object o) {
            Rango ro = (Rango) o;
            if (this.tiempo != ro.tiempo) {
                return this.tiempo - ro.tiempo;
            }
            return this.tipo - ro.tipo;
        }
    }

    public static void overlap(List<Rango> data) {
        int maxOverlap = 0;
        int count = 0;

        // Ordenar por tiempo (y por tipo implícitamente)
        Collections.sort(data);

        // Primer recorrido: encontrar el máximo solapamiento
        for (Rango p : data) {
            if (p.tipo == 'e') count++;  //Entrada
            if (p.tipo == 's') count--;  //Salida
            if (count > maxOverlap) maxOverlap = count;
        }

        int lastMaxOverlap = 0;
        int timeMax = 0;
        count = 0; // importante reiniciar

        // Segundo recorrido: calcular tiempo total con máximo solapamiento
        for (Rango p : data) {
            if (p.tipo == 'e') {   //Entrada
                count++;
                if (count == maxOverlap) {
                    lastMaxOverlap = p.tiempo;
                }
            }

            if (p.tipo == 's') {   // Salida
                if (count == maxOverlap) {
                    timeMax += (p.tiempo - lastMaxOverlap)+1;
                }
                count--;
            }
        }

        System.out.println(maxOverlap + " " + timeMax);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numComensales = scan.nextInt();


        while (numComensales > 0) {
            int i = 0;
            ArrayList<Rango> data = new ArrayList<>();
            while (i < numComensales) {
                int entrada = scan.nextInt();
                int salida = scan.nextInt();

                data.add(new Rango(entrada, 'e'));   // entrada
                data.add(new Rango(salida-1, 's'));   // salida

                i++;
            }

            new p671_AbuelosFelices().overlap(data);

            numComensales = scan.nextInt();
        }

    }


}


//    static class pair {
//
//        public int first;
//        public char second;
//
//        pair(int first, char second) {
//            this.first = first;
//            this.second = second;
//        }
//    }
//
//
//    static class SortByCustomOrder implements Comparator<pair>
//    {
//        // Used for sorting in ascending order of
//        // roll number
//        public int compare(pair a, pair b)
//        {
//            int result;
//
//            if (a.first == b.first)
//                result = (a.second - b.second);
//            else
//                result = (a.first - b.first);
//
//            return result;
//        }
//    }
//
//
//    // Function that print maximum overlap among ranges
//    public void overlap(ArrayList<pair> data) {
//
//        // Variable to store the maximum
//        // count
//        int maxInvitados = 0;
//        int tiempoMaxInvitados = 0;
//        int ans = 0;
//        int count = 0;
//
//        // Sorting of ranges
//        Collections.sort(data, new SortByCustomOrder());
//
//        // Traverse the data vector to
//        // count number of overlaps
//        for (int i = 0; i < data.size(); i++) {
//
//            // If x occur it means a new range
//            // is added so we increase count
//            if (data.get(i).second == 'x') {
//                count++;
//            }
//
//            // If y occur it means a range
//            // is ended so we decrease count
//            if (data.get(i).second == 'y') {
//                count--;
//            }
//
//            // Updating the value of ans
//            // after every traversal
//            ans = Math.max(ans, count);
//
//            //actualiza el valor del maxInvitados y el tiempo de permanencia
//            if (count > maxInvitados) {
//                maxInvitados = count;
//                tiempoMaxInvitados = 1;
//            } else if (count + 1 == maxInvitados) {
//                tiempoMaxInvitados += data.get(i).first - data.get(i - 1).first;
//            } else if (count == maxInvitados) {
//                tiempoMaxInvitados++;
//            }
//        }
//
//        // Printing the maximum value
//        System.out.println(maxInvitados + " " + tiempoMaxInvitados);
//    }