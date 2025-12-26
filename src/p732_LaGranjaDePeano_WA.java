import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p732_LaGranjaDePeano_WA {

    /**
     * Dado (x, y), devuelve la posición lineal en el recorrido Peano.
     * Usa interpretación base 3 de cada coordenada y mapea a un índice lineal.
     */
    static long peanoPos(long x, long y) {
        long pos = 0L;
        long factor = 1L;

        // Convertir recursivamente desde menos significativo al más importante
        for (int i = 0; i < 20; i++) {
            int xi = (int) (x % 3);
            int yi = (int) (y % 3);
            x /= 3;
            y /= 3;

            /*
             * El número base 9 define qué subcuadrante de 3x3 corresponde.
             * La curva de Peano sigue un patrón específico:
             *  0 1 2
             *  3 4 5
             *  6 7 8
             */
            int index = xi * 3 + yi;

            // Añade el valor a la posición
            pos += factor * index;
            factor *= 9L;
        }
        return pos;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine().trim());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());

            long p1 = peanoPos(x1, y1);
            long p2 = peanoPos(x2, y2);

            long answer = Math.abs(p1 - p2);
            System.out.println(answer);
        }
    }
}
