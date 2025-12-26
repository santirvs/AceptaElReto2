import java.util.*;

// Genera todos los primos hasta Raiz(máx) con la criba de Erastótenes
// Factoriza cada número de entrada usando esos primos.
// Por cada primo en la factorización, calcula 2*exponente + 1 y multiplica.
// Si queda un factor primo > √k, multiplica por 3.
// Divide entre 2 (sumando 1 antes) para obtener el número de divisores cuadrados.

// Dado un número k
// Mostrar cuantas combinaciones diferentes de parejas existen para 1/k = 1/x + 1/y

public class p226_ReescribiendoFracciones {
    static final int RAIZ_MAX = 46340;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> primos = new ArrayList<>();
        boolean[] marcado = new boolean[RAIZ_MAX + 1];

        // Criba de Eratóstenes hasta RAIZ_MAX
        for (int i = 2; i <= RAIZ_MAX; i++) {
            if (!marcado[i]) {
                primos.add(i);
                for (int j = i * i; j <= RAIZ_MAX; j += i) {
                    marcado[j] = true;
                }
            }
        }

        //Lee cada uno de los casos de prueba
        while (sc.hasNextInt()) {
            //
            int k = sc.nextInt();
            int div_cuad = 1;
            int tempK = k;

            //Buscar los factores primos de k
            for (int primo : primos) {
                if (primo * primo > tempK) break;
                int veces = 0;
                while (tempK % primo == 0) {
                    tempK /= primo;
                    veces++;
                }
                // Por cada primo en la factorización, calcula 2*exponente + 1 y multiplica.
                if (veces > 0) div_cuad *= (2 * veces + 1);
            }

            // Si queda un factor primo > √k, multiplica por 3.
            if (tempK != 1) div_cuad *= 3;

            //Divide entre 2 (sumando 1 antes) para obtener el número de divisores cuadrados.
            System.out.println((div_cuad + 1) / 2);
        }
        sc.close();
    }
}
