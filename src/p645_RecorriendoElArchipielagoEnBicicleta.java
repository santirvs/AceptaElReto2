import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santi
 * @since 27/12/2025
 *
 *
 * v1.- Recuperado un envío del 27/06/2022 - TLE
 *
 * v2.- Hecho en C++. Implementaba el algoritmo del viajante de comercio
 *      Debe implementarse el algoritmo de Kruskal
 *      Dado que buscamos un árbol generador mínimo (MST)
 *      Algoritmo de Kruskal
 *       - Ordenar todos los puentes por coste ascendente
 *       - Inicialmente, cada isla está en su propio conjunto
 *       - Recorremos los puentes en orden:
 *       - Si conecta dos islas no conectadas aún, lo añadimos
 *       - Parar cuando:
 *          - se han unido I - 1 puentes → MST completo
 *          - Si no se puede llegar a I - 1 puentes → no hay solución
 *
 */
import java.io .*;
import java.util .*;
public class p645_RecorriendoElArchipielagoEnBicicleta {

    static class Edge {
        int origen, destino;
        int coste;

        Edge(int origen, int destino, int coste) {
            this.origen = origen;
            this.destino = destino;
            this.coste = coste;
        }
    }

    // Union-Find (Disjoint Set Union)
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false;

            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int numIslas = Integer.parseInt(line);
            int numPuentes = Integer.parseInt(br.readLine().trim());

            List<Edge> puentes = new ArrayList<>(numPuentes);
            for (int i = 0; i < numPuentes; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int origen = Integer.parseInt(st.nextToken());
                int destino = Integer.parseInt(st.nextToken());
                int costes = Integer.parseInt(st.nextToken());
                puentes.add(new Edge(origen, destino, costes));
            }

            //Ordenar los puentes por coste
            Collections.sort(puentes, new Comparator<Edge>() {
                @Override
                public int compare(Edge a, Edge b) {
                    return Integer.compare(a.coste, b.coste);
                }
            });

            //Inicialmente todas las islas están separadas
            // y las iremos uniendo por los puentes de menor coste
            // que no sean redundantes
            // Disjoint set union
            DSU dsu = new DSU(numIslas);
            long costeTotal = 0;
            int puentesUsados = 0;

            //Recorrer todos los puentes
            for (Edge e : puentes) {
                //Si origen y destino no están unidos, usaremos ese puente
                if (dsu.union(e.origen, e.destino)) {
                    costeTotal += e.coste;
                    puentesUsados++;
                    //Paramos si ya hemos usado n-1 puentes
                    if (puentesUsados == numIslas - 1) break;
                }
            }

            //Imprimir el resultado
            if (puentesUsados == numIslas - 1) {
                out.append(costeTotal).append('\n');
            } else {
                out.append("No hay puentes suficientes").append('\n');
            }
        }

        System.out.print(out);
    }
}

