package _24en23._2025;

import java.io.*;
import java.util.*;

/**
 *
 * @author santi
 * @date 30/06/2025
 */

public class p816_PlanDeFormacion {


        static class Edge {
            int to, rev;
            int cap;

            Edge(int to, int rev, int cap) {
                this.to = to;
                this.rev = rev;
                this.cap = cap;
            }
        }

        static List<Edge>[] grafo;
        static int[] nivel;
        static int[] iter;
        static int N; // número de nodos totales
        static int fuente, sumidero;

        @SuppressWarnings("unchecked")
        public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            while (sc.hasNext()) {
                int C = sc.nextInt();
                int P = sc.nextInt();

                N = C + P + 2;
                fuente = 0;
                sumidero = N - 1;

                grafo = new List[N];
                for (int i = 0; i < N; i++) {
                    grafo[i] = new ArrayList<Edge>();
                }

                // Conectar categorías al sumidero
                for (int i = 1; i <= C; i++) {
                    int cap = sc.nextInt();
                    agregarArista(i, sumidero, cap);
                }

                // Conectar fuente a problemas y problemas a categorías
                for (int i = 1; i <= P; i++) {
                    int nodoProblema = C + i;
                    agregarArista(fuente, nodoProblema, 1);

                    while (true) {
                        int cat = sc.nextInt();
                        if (cat == 0) break;
                        agregarArista(nodoProblema, cat, 1);
                    }
                }

                System.out.println(flujoMaximo());
            }

            sc.close();
        }

        // Agrega aristas bidireccionales con capacidades y referencias para grafo residual
        static void agregarArista(int from, int to, int cap) {
            grafo[from].add(new Edge(to, grafo[to].size(), cap));
            grafo[to].add(new Edge(from, grafo[from].size() - 1, 0));
        }

        // BFS para construir niveles
        static void bfs() {
            Arrays.fill(livel, -1);
            Queue<Integer> cola = new ArrayDeque<Integer>();
            livel[fuente] = 0;
            cola.add(fuente);

            while (!cola.isEmpty()) {
                int v = cola.poll();
                for (Edge e : grafo[v]) {
                    if (e.cap > 0 && livel[e.to] < 0) {
                        livel[e.to] = livel[v] + 1;
                        cola.add(e.to);
                    }
                }
            }
        }

        // DFS para encontrar flujos bloqueantes
        static int dfs(int v, int t, int f) {
            if (v == t) return f;
            for (; iter[v] < grafo[v].size(); iter[v]++) {
                Edge e = grafo[v].get(iter[v]);
                if (e.cap > 0 && livel[v] < livel[e.to]) {
                    int d = dfs(e.to, t, Math.min(f, e.cap));
                    if (d > 0) {
                        e.cap -= d;
                        grafo[e.to].get(e.rev).cap += d;
                        return d;
                    }
                }
            }
            return 0;
        }

        // Dinic's algorithm principal
        static int flujoMaximo() {
            int flujo = 0;
            livel = new int[N];
            iter = new int[N];
            while (true) {
                bfs();
                if (livel[sumidero] < 0) break;
                Arrays.fill(iter, 0);
                int f;
                while ((f = dfs(fuente, sumidero, Integer.MAX_VALUE)) > 0) {
                    flujo += f;
                }
            }
            return flujo;
        }

        static int[] livel; // array global por eficiencia
    }
