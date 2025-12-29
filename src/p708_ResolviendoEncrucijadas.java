/**
 *
 * @author santi
 *
 * v1 - Recuperado un envío anterior de 30/12/2023 con WA
 *
 * v2 - Simplifico el ajuste del primer y último múltiplo de 3
 *
 * v3 - Lo he interpretado mal: el coste de imprimir 12 no es 12 sino 1+3
 *      Aplicar Programación Dinámica por dígitos
 *
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p708_ResolviendoEncrucijadas {

    static char[] digits;
    static long[][][] memoCount;
    static long[][][] memoSum;
    static boolean[][][] visited;
    static int len;

    static long[] dp(int pos, int mod, int tight) {
        if (pos == len) {
            if (mod == 0) return new long[]{1, 0};
            return new long[]{0, 0};
        }

        if (tight == 0 && visited[pos][mod][0]) {
            return new long[]{memoCount[pos][mod][0], memoSum[pos][mod][0]};
        }

        int limit = (tight == 1) ? digits[pos] - '0' : 9;
        long count = 0;
        long sum = 0;

        for (int d = 0; d <= limit; d++) {
            long[] res = dp(pos + 1, (mod + d) % 3, (tight == 1 && d == limit) ? 1 : 0);
            count += res[0];
            sum += res[1] + res[0] * d;
        }

        if (tight == 0) {
            visited[pos][mod][0] = true;
            memoCount[pos][mod][0] = count;
            memoSum[pos][mod][0] = sum;
        }

        return new long[]{count, sum};
    }

    static long solve(long n) {
        if (n < 0) return 0;

        digits = Long.toString(n).toCharArray();
        len = digits.length;

        memoCount = new long[len][3][1];
        memoSum = new long[len][3][1];
        visited = new boolean[len][3][1];

        return dp(0, 0, 1)[1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            StringTokenizer st = new StringTokenizer(line);
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (a == 0 && b == 0) break;

            long result = solve(b) - solve(a - 1);
            System.out.println(result);
        }
    }
}
