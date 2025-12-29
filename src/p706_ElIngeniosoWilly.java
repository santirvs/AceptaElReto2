/**
 *
 * @author santi
 * @since 29/12/2025
 *
 * v1 - Buscar los caminos mínimos del banco a la guarida y eliminar
 *      cualquier camino que pase por esas calles
 *      Para calcular las calles de los caminos mínimos hay que calcular
 *      Dijkstra banco->guarida y Dijkstra guarida->banco
 *      Cualquier camino mínimo pasará por una de sus calles
 *      Eliminar todas las calles de esos dos caminos
 *      Con el resto, volver a calcular Dijkstra banco->guarida
 *
 */
import java.io.*;
import java.util.*;

public class p706_ElIngeniosoWilly {

    static class Edge {
        int destino, coste, id;
        Edge(int destino, int coste, int id) {
            this.destino = destino;
            this.coste = coste;
            this.id = id;
        }
    }

    static class State implements Comparable<State> {
        int node;
        long dist;
        State(int n, long d) {
            node = n;
            dist = d;
        }
        public int compareTo(State o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    // MAX_VALUE / 4 para tener un margen de seguridad y no causar overflow que conduciría a soluciones incorrectas
    static final long INF = Long.MAX_VALUE / 4;

    // Calcula el coste de llegar a cualquier nodo desde el inicio
    // evitando la lista de aristas prohibidas
    static long[] dijkstra(List<Edge>[] grafo, int inicio, boolean[] prohibidas) {
        int tamano = grafo.length;
        long[] distancias = new long[tamano];
        Arrays.fill(distancias, INF);
        distancias[inicio] = 0;

        //cola de prioridad para buscar siempre el más corto
        PriorityQueue<State> pq = new PriorityQueue<State>();
        pq.add(new State(inicio, 0));

        //Mientras queden nodos por explorar
        while (!pq.isEmpty()) {
            //recuperar el nodo de menor coste
            State cur = pq.poll();
            //Si ya se ha llegado al él con un coste menor, ignorarlo
            if (cur.dist != distancias[cur.node]) continue;
            //Para cada arista desde este nodo
            for (Edge e : grafo[cur.node]) {
                //Si la arista está prohibida, ignorarla
                if (prohibidas != null && prohibidas[e.id]) continue;
                //Calcular la nueva distancia (lo que ha costado llegar aquí, más el coste de la arista)
                long nd = cur.dist + e.coste;
                //Actualizar la distancia del nodo de destino, sólo si es menor al coste actual de llegar a él
                if (nd < distancias[e.destino]) {
                    distancias[e.destino] = nd;
                    //Añadir a la cola
                    pq.add(new State(e.destino, nd));
                }
            }
        }
        //Devuelva la lista de costes de llegar a cada uno de los nodos
        return distancias;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            StringTokenizer st = new StringTokenizer(line);
            int numIntersecciones = Integer.parseInt(st.nextToken());
            int numCalles = Integer.parseInt(st.nextToken());

            //Define el grafo (lista de intersecciones)
            // de tamaño numIntersecciones +1, para facilitar el uso. g[0] no se usará nunca!
            // las intersecciones se numeran de 1 a NI
            List<Edge>[] grafo = new ArrayList[numIntersecciones + 1];
            for (int i = 1; i <= numIntersecciones; i++)
                grafo[i] = new ArrayList<Edge>();

            //Define array de origenes, destinos y costes
            int[] origen = new int[numCalles];
            int[] destino = new int[numCalles];
            int[] coste = new int[numCalles];

            //Lee cada una de las intersecciones
            for (int i = 0; i < numCalles; i++) {
                st = new StringTokenizer(br.readLine());
                origen[i] = Integer.parseInt(st.nextToken());
                destino[i] = Integer.parseInt(st.nextToken());
                coste[i] = Integer.parseInt(st.nextToken());

                //Añade las calles (aristas) en ambos sentidos
                grafo[origen[i]].add(new Edge(destino[i], coste[i], i));
                grafo[destino[i]].add(new Edge(origen[i], coste[i], i));
            }

            //Calcula els dijsktra de 1 a N y de N a 1
            long[] dist1 = dijkstra(grafo, 1, null);
            long[] distN = dijkstra(grafo, numIntersecciones, null);

            //Define las calles prohibidas
            boolean[] banned = new boolean[numCalles];
            long shortest = dist1[numIntersecciones];

            //Buscar si una calle se encuentra en alguno de los caminos más cortos
            //Esto se dará si el coste de ir hasta esa calle (dist1[i])
            //          + el coste de cruzar la calle (coste[i])
            //          + el coste de ir desde esa calle al final (distN[i])
            //          es igual al camino más corto
            //Si es así, se prohibe el uso de la calle
            for (int i = 0; i < numCalles; i++) {
                if (dist1[origen[i]] + coste[i] + distN[destino[i]] == shortest ||
                        dist1[destino[i]] + coste[i] + distN[origen[i]] == shortest) {
                    banned[i] = true;
                }
            }

            //calcular el camino alternativo con la lista de calles prohibidas
            long[] distAlt = dijkstra(grafo, 1, banned);

            //Imprimir el resultado de llegar al nodo final
            if (distAlt[numIntersecciones] >= INF)
                System.out.println("IMPOSIBLE");
            else
                System.out.println(distAlt[numIntersecciones]);
        }
    }
}
