package _24en23._2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author santi
 * @date 27/06/2025
 */

// v1.- Parece ser un claro Dijkstra...

public class p813_RapidEats {

        static class Edge {
            int to, time;
            Edge(int to, int time) {
                this.to = to;
                this.time = time;
            }
        }

        static class Node implements Comparable<Node> {
            int id, dist;
            Node(int id, int dist) {
                this.id = id;
                this.dist = dist;
            }

            public int compareTo(Node other) {
                return Integer.compare(this.dist, other.dist);
            }
        }

        static int[] dijkstra(List<List<Edge>> graph, int start) {
            int n = graph.size();
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.offer(new Node(start, 0));

            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                if (curr.dist > dist[curr.id]) continue;

                for (Edge edge : graph.get(curr.id)) {
                    int next = edge.to;
                    int newDist = dist[curr.id] + edge.time;
                    if (newDist < dist[next]) {
                        dist[next] = newDist;
                        pq.offer(new Node(next, newDist));
                    }
                }
            }

            return dist;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                String[] first = line.trim().split("\\s+");
                int N = Integer.parseInt(first[0]); // Número de puntos
                int C = Integer.parseInt(first[1]); // Número de tramos

                // Lista de adyacencia (indexada desde 1 hasta N)
                List<List<Edge>> graph = new ArrayList<List<Edge>>(N + 1);
                for (int i = 0; i <= N; i++) {
                    graph.add(new ArrayList<Edge>());
                }

                for (int i = 0; i < C; i++) {
                    String[] parts = br.readLine().trim().split("\\s+");
                    int u = Integer.parseInt(parts[0]);
                    int v = Integer.parseInt(parts[1]);
                    int t = Integer.parseInt(parts[2]);
                    graph.get(u).add(new Edge(v, t));
                    graph.get(v).add(new Edge(u, t));
                }

                int K = Integer.parseInt(br.readLine().trim()); // Número de pedidos
                int[][] pedidos = new int[K][2];

                Set<Integer> puntosOrigen = new HashSet<Integer>();
                for (int i = 0; i < K; i++) {
                    String[] parts = br.readLine().trim().split("\\s+");
                    pedidos[i][0] = Integer.parseInt(parts[0]);
                    pedidos[i][1] = Integer.parseInt(parts[1]);
                    puntosOrigen.add(pedidos[i][0]);
                }

                // Guardamos los resultados de Dijkstra por origen para no repetir
                Map<Integer, int[]> distanciasMap = new HashMap<Integer, int[]>();
                for (int origen : puntosOrigen) {
                    distanciasMap.put(origen, dijkstra(graph, origen));
                }

                for (int i = 0; i < K; i++) {
                    int origen = pedidos[i][0];
                    int destino = pedidos[i][1];
                    int[] distancias = distanciasMap.get(origen);
                    int d = distancias[destino];
                    if (d == Integer.MAX_VALUE) {
                        System.out.println("NO LLEGA");
                    } else {
                        System.out.println(d);
                    }
                }
                System.out.println("---");
            }
        }
    }


/* * Ejemplo de entrada:
4 4
1 2 15
1 3 30
2 3 20
4 3 10
2
1 3
4 1
4 2
1 3 10
2 4 20
2
1 2
3 1
/* Ejemplo de salida:
30
40
---
NO LLEGA
10
---
 */