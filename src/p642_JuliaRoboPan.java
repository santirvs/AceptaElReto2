import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class p642_JuliaRoboPan {

    static class UnionFind {
        private int[] p, rank, setSize;
        private int numSets;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            setSize = new int[N];
            numSets = N;
            for (int i = 0; i < N; ++i) {
                p[i] = i;
                setSize[i] = 1;        }    }

        public int findSet(int i) {  if (p[i] == i) return i; else  return p[i] = findSet(p[i]);    }

        public boolean isSameSet(int i, int j) {  return findSet(i) == findSet(j);   }

        public int numDisjointSets() {  return numSets;  }

        public void unionSet(int i, int j) {
            if (isSameSet(i, j)) return;
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {  int temp = x; x = y; y = temp;    }
            p[x] = y;
            if (rank[x] == rank[y]) rank[y]++;
            setSize[y] += setSize[x];
            numSets--;    }

        // Devuelve el tamaño del conjunto al que pertenece el elemento i
        public int sizeOfSet(int i) {   return setSize[findSet(i)];    }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int numNinos = Integer.parseInt(line);
            UnionFind uf = new UnionFind(numNinos);

            line= br.readLine();
            String[] parts = line.split(" ");

            for (int i=0; i<numNinos ; i++) {
                uf.unionSet(i, Integer.parseInt(parts[i])-1);
            }

            System.out.println(uf.numSets);
        }


    }
}
