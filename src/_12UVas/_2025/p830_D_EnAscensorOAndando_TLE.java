package _12UVas._2025;
/**
 * @author santi
 * @since 31/12/2025
 *
 * Concurso de las 12 UVas de 2025
 */

/**
 * v1.- Planteamiento general del problema
 *      Caminar hacia la planta de destino llamando al ascensor en cada planta si no es que nos acercamos a él
 *      Calcular lo que tardará en llegar el ascensor a la planta
 *      Esperarlo y subir a él
 *      O bien seguir caminando.
 *      WA!
 *
 *
 *  v2.- Cambio de planteamiento.
 *       Plantearlo como un Diskjstra calculando el coste de llegar a cada planta andando o en ascensor
 *
 *
 */

import java.io.*;
import java.util.*;

public class p830_D_EnAscensorOAndando_TLE {

    static class State implements Comparable<State> {
        int floor;
        int mode; // 0 = andando, 1 = ascensor
        long dist;

        State(int f, int m, long d) {
            floor = f;
            mode = m;
            dist = d;
        }

        public int compareTo(State o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int tWalk = Integer.parseInt(st.nextToken());
            int tElev = Integer.parseInt(st.nextToken());

            if (A == 0 && B == 0 && E == 0 && tWalk == 0 && tElev == 0)
                break;

            System.out.println(dijkstra(A, B, E, tWalk, tElev));
        }
    }

    static long dijkstra(int A, int B, int E, int tWalk, int tElev) {
        long[][] dist = new long[MAX + 1][2];
        for (int i = 0; i <= MAX; i++)
            Arrays.fill(dist[i], Long.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[A][0] = 0;
        pq.add(new State(A, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.dist != dist[cur.floor][cur.mode]) continue;

            if (cur.floor == B)
                return cur.dist;

            // ANDANDO
            if (cur.mode == 0) {
                // caminar
                for (int d : new int[]{-1, 1}) {
                    int nf = cur.floor + d;
                    if (nf >= 0 && nf <= MAX) {
                        long nd = cur.dist + tWalk;
                        if (nd < dist[nf][0]) {
                            dist[nf][0] = nd;
                            pq.add(new State(nf, 0, nd));
                        }
                    }
                }

                // subir al ascensor
                long cost = cur.dist + Math.abs(E - cur.floor) * (long) tElev;
                if (cost < dist[cur.floor][1]) {
                    dist[cur.floor][1] = cost;
                    pq.add(new State(cur.floor, 1, cost));
                }
            }

            // EN ASCENSOR
            else {
                // moverse en ascensor
                for (int d : new int[]{-1, 1}) {
                    int nf = cur.floor + d;
                    if (nf >= 0 && nf <= MAX) {
                        long nd = cur.dist + tElev;
                        if (nd < dist[nf][1]) {
                            dist[nf][1] = nd;
                            pq.add(new State(nf, 1, nd));
                        }
                    }
                }

                // bajarse
                if (cur.dist < dist[cur.floor][0]) {
                    dist[cur.floor][0] = cur.dist;
                    pq.add(new State(cur.floor, 0, cur.dist));
                }
            }
        }

        return -1;
    }
}
