package _24en23._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author santi
 * @date 25/06/2025
 */

// v1.- Parece simple...  según el plan de formación debería hacerse con un Segment Tree

public class p811_PositivoNegativoYCero {

    static int[] tree;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = in.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length < 2) continue;

            N = Integer.parseInt(parts[0]);
            int C = Integer.parseInt(parts[1]);
            if (N == 0 && C == 0) break;

            // Tree size for iterative segment tree: 2*N
            tree = new int[2 * N];

            String[] vals = in.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                tree[N + i] = sign(Integer.parseInt(vals[i]));
            }

            build();

            StringBuilder output = new StringBuilder();
            for (int i = 0; i < C; i++) {
                parts = in.readLine().split(" ");
                if (parts[0].equals("C")) {
                    int idx = Integer.parseInt(parts[1]) - 1;
                    int val = sign(Integer.parseInt(parts[2]));
                    update(idx, val);
                } else if (parts[0].equals("?")) {
                    int l = Integer.parseInt(parts[1]) - 1;
                    int r = Integer.parseInt(parts[2]) - 1;
                    int result = query(l, r);
                    output.append(result == 0 ? '0' : (result > 0 ? '+' : '-'));
                }
            }
            System.out.println(output.toString());
        }
    }

    static int sign(int x) {
        if (x == 0) return 0;
        return x > 0 ? 1 : -1;
    }

    // Build the segment tree
    static void build() {
        for (int i = N - 1; i > 0; i--) {
            tree[i] = tree[i << 1] * tree[i << 1 | 1];
        }
    }

    // Update value at position idx to val
    static void update(int idx, int val) {
        idx += N;
        tree[idx] = val;
        while (idx > 1) {
            idx >>= 1;
            tree[idx] = tree[idx << 1] * tree[idx << 1 | 1];
        }
    }

    // Query product in range [l, r]
    static int query(int l, int r) {
        int res = 1;
        l += N;
        r += N + 1;
        while (l < r) {
            if ((l & 1) == 1) res *= tree[l++];
            if ((r & 1) == 1) res *= tree[--r];
            l >>= 1;
            r >>= 1;
            if (res == 0) break; // Early exit on zero
        }
        return res;
    }

}