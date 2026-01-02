package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Aplicar la fórmula del zapatero (Shoelace formula) para calcular el área de un polígono irregular
 *      area = 1/2 * SUMA[i=0 .. n-1] Xi*Yi+1 - Xi+1Yi   // XiYi son las coordenadas del vértice i
 *      Empezar en el vértice 0,0 y guardar los vértices en orden
 *
 *      Posible optimización: calcular el área según van llegando los vértices sin necesidad de almacenarlos
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class p835_I_Irving {

    //Clase para registrar los puntos de cada vértice
    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        //Cada caso es una línea
        //Finaliza cuando no hayan más datos a la entrada
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue; //

            //Lectura de los puntos
            StringTokenizer st = new StringTokenizer(line);
            ArrayList<Point> vertices = new ArrayList<Point>();

            //Primer vértice (ficticio)
            long x = 0, y = 0;
            vertices.add(new Point(x, y));

            //Lectura de los movimientos
            while (st.hasMoreTokens()) {
                //Lee la instrucción y le asigna distancia 1
                String token = st.nextToken();
                char dir = token.charAt(0);
                long dist = 1;

                // Si es mayúscula, la distancia viene en el siguiente token
                if (Character.isUpperCase(dir)) {
                    dist = Long.parseLong(st.nextToken());
                }

                //Para simplificar el switch, pone la dirección en mayúsculas
                dir = Character.toUpperCase(dir);

                //Actualiza las coordenadas según la dirección del movimiento
                switch (dir) {
                    case 'N': y += dist; break;
                    case 'S': y -= dist; break;
                    case 'E': x += dist; break;
                    case 'O': x -= dist; break;
                }

                //Añadir el vértice a la lista
                vertices.add(new Point(x, y));
            }

            // Cálculo del área con la fórmula del zapatero
            long area2 = 0;
            int n = vertices.size();
            for (int i = 0; i < n; i++) {
                Point p1 = vertices.get(i);
                Point p2 = vertices.get((i + 1) % n);
                area2 += p1.x * p2.y - p2.x * p1.y;
            }
            long area = Math.abs(area2) / 2;

            //Muestra el resultado
            System.out.println(area);
        }
    }
}
