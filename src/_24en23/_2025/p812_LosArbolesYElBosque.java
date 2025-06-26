package _24en23._2025;

import java.util.*;

/**
 *
 * @author santi
 * @date 26/06/2025
 */

// v1.- Se construye el árbol y se analiza contando los nodos aislados, los árboles y los ciclos.

public class p812_LosArbolesYElBosque {

        static int nv, ne;
        static List<List<Integer>> adyacencia;
        static boolean[] visitado;
        static boolean tieneCiclo;
        static int tamanoComponente;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            while (sc.hasNext()) {
                nv = sc.nextInt();
                ne = sc.nextInt();

                adyacencia = new ArrayList<List<Integer>>();
                for (int i = 0; i <= nv; i++) {
                    adyacencia.add(new ArrayList<Integer>());
                }

                int[] grados = new int[nv + 1];

                for (int i = 0; i < ne; i++) {
                    int u = sc.nextInt();
                    int v = sc.nextInt();
                    adyacencia.get(u).add(v);
                    adyacencia.get(v).add(u);
                    grados[u]++;
                    grados[v]++;
                }

                visitado = new boolean[nv + 1];
                int aislados = 0, arboles = 0, conCiclos = 0;

                for (int i = 1; i <= nv; i++) {
                    if (grados[i] == 0) {
                        aislados++;
                        visitado[i] = true;
                    }
                }

                for (int i = 1; i <= nv; i++) {
                    if (!visitado[i]) {
                        tieneCiclo = false;
                        tamanoComponente = 0;
                        dfs(i, -1);
                        if (tieneCiclo) {
                            conCiclos++;
                        } else if (tamanoComponente >= 2) {
                            arboles++;
                        }
                    }
                }

                System.out.println(aislados + " " + arboles + " " + conCiclos);
            }

            sc.close();
        }

        private static void dfs(int u, int padre) {
            visitado[u] = true;
            tamanoComponente++;

            for (int v : adyacencia.get(u)) {
                if (!visitado[v]) {
                    dfs(v, u);
                } else if (v != padre) {
                    tieneCiclo = true;
                }
            }
        }
    }


/*Ejemplo de entrada:
7 5
2 3
4 5
5 6
6 4
4 7
*/
// Ejemplo de salida:
// 1 1 1