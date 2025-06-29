package _24en23._2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


/**
 *
 * @author santi
 * @date 29/06/2025
 */

public class p815_LaRebelionDeLasTorres {

        static final int MAXN = 105;
        static char[][] board = new char[MAXN][MAXN];
        static int[][] hComp = new int[MAXN][MAXN]; // componentes horizontales
        static int[][] vComp = new int[MAXN][MAXN]; // componentes verticales
        static ArrayList<Integer>[] graph;
        static int[] match;
        static boolean[] visited;
        static int N, hCount, vCount;

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = br.readLine();
                if (line == null || line.trim().isEmpty()) continue;
                N = Integer.parseInt(line.trim());
                if (N == 0) break;

                for (int i = 0; i < N; i++) {
                    board[i] = br.readLine().trim().toCharArray();
                }

                hCount = labelComponents(hComp, true);
                vCount = labelComponents(vComp, false);

                // Construcción del grafo bipartito
                graph = new ArrayList[hCount];
                for (int i = 0; i < hCount; i++) {
                    graph[i] = new ArrayList<Integer>();
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] == '.') {
                            int h = hComp[i][j];
                            int v = vComp[i][j];
                            graph[h].add(v);
                        }
                    }
                }

                // Matching
                match = new int[vCount];
                Arrays.fill(match, -1);
                int result = 0;

                for (int u = 0; u < hCount; u++) {
                    visited = new boolean[hCount];
                    if (dfs(u)) result++;
                }

                System.out.println(result);
            }
        }

        // Etiquetado de componentes horizontales o verticales
        static int labelComponents(int[][] comp, boolean horizontal) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                int j = 0;
                while (j < N) {
                    while (j < N && board[horizontal ? i : j][horizontal ? j : i] == 'X') j++;
                    if (j == N) break;
                    while (j < N && board[horizontal ? i : j][horizontal ? j : i] == '.') {
                        comp[horizontal ? i : j][horizontal ? j : i] = count;
                        j++;
                    }
                    count++;
                }
            }
            return count;
        }

        static boolean dfs(int u) {
            if (visited[u]) return false;
            visited[u] = true;
            for (int v : graph[u]) {
                if (match[v] == -1 || dfs(match[v])) {
                    match[v] = u;
                    return true;
                }
            }
            return false;
        }
    }
