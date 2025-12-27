import java.util.*;

/**
     *
     * @author santi
     *
     * v1 - Recuperado un envío anterior de 01/01/2024 con WA
     *
     * v2 - Análisis. La lista de meses estaba mal definida:
     *       4 años + 1 bisiesto + 4 años debe ser 1 bisiesto + 3 años + 1 bisiesto + 3 años + 1 bisiesto
     *       Cambio Lista por HashSet para el conjunto de días posibles (no son necesarios los repetidos)
     */

    public class p719_1260Dias {

    static List<Integer> diasMeses = Collections.unmodifiableList(
            Arrays.asList(
                    31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31,
                    31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31));

    static HashSet<Integer> combinacionesDias = new HashSet<Integer>();

    public static int calcularSumaDias(int inicio, int numMeses) {
        int cantDias = 0;

        for (int i = inicio; i < inicio + numMeses; i++) {
            cantDias += diasMeses.get(i);
        }

        return cantDias;
    }

    public static void construirCantidadDias(int numMeses) {
        for (int i = 0; i < diasMeses.size() - numMeses; i++) {
            int numDias = calcularSumaDias(i, numMeses);
            combinacionesDias.add(numDias);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Construir todas las combinaciones posibles de meses entre 0 meses y 47 meses
        for (int i = 0; i <= 47; i++) {
            construirCantidadDias(i);
        }

        int numDias = scan.nextInt();

        while (numDias > 0) {
            //Le quito los ciclos de 4 años
            numDias = numDias % (365 * 4 + 1);

            //Busco el numero de dias entre las combinaciones posibles
            if (combinacionesDias.contains(numDias))
                System.out.println("SI");
            else
                System.out.println("NO");

            numDias = scan.nextInt();
        }

    }

}
