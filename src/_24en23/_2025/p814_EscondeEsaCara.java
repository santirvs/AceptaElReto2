package _24en23._2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author santi
 * @date 28/06/2025
 */

public class p814_EscondeEsaCara {

        static final int[] dx = {0, 1, 0, -1}; // derecha, abajo, izquierda, arriba
        static final int[] dy = {-1, 0, 1, 0};

        static class Orientation {
            int top, bottom, front, back, left, right;

            Orientation(int t, int b, int f, int ba, int l, int r) {
                top = t; bottom = b; front = f; back = ba; left = l; right = r;
            }

            Orientation roll(int dir) {
                // 0: izquierda, 1: abajo, 2: derecha, 3: arriba
                if (dir == 0) return new Orientation(right, left, front, back, top, bottom);
                if (dir == 1) return new Orientation(back, front, top, bottom, left, right);
                if (dir == 2) return new Orientation(left, right, front, back, bottom, top);
                return new Orientation(front, back, bottom, top, left, right);
            }

            @Override
            public int hashCode() {
                return Arrays.hashCode(new int[]{top, bottom, front, back, left, right});
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Orientation)) return false;
                Orientation o = (Orientation) obj;
                return top == o.top && bottom == o.bottom &&
                        front == o.front && back == o.back &&
                        left == o.left && right == o.right;
            }
        }

        static class CubeState {
            int x, y, steps;
            Orientation ori;

            CubeState(int x, int y, int steps, Orientation ori) {
                this.x = x; this.y = y; this.steps = steps; this.ori = ori;
            }
        }

        static int tx, ty;
        static char[][] map;

        static boolean[][][] passable(int tx, int ty, char[][] map) {
            boolean[][][] pass = new boolean[ty][tx][4]; // [y][x][direction]
            for (int y = 0; y < ty; y++) {
                for (int x = 0; x < tx; x++) {
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= tx || ny >= ty) continue;
                        int mx = 2 * x + 1 + dx[d];
                        int my = 2 * y + 1 + dy[d];
                        if (map[my][mx] == ' ') pass[y][x][d] = true;
                    }
                }
            }
            return pass;
        }

        static int bfsSimple(int sx, int sy, int ex, int ey, boolean[][][] pass) {
            boolean[][] visited = new boolean[ty][tx];
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.add(new int[]{sx, sy, 0});
            visited[sy][sx] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0], y = current[1], steps = current[2];
                if (x == ex && y == ey) return steps;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= tx || ny >= ty) continue;
                    if (!pass[y][x][d]) continue;
                    if (visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    queue.add(new int[]{nx, ny, steps + 1});
                }
            }
            return -1;
        }

        static int bfsQbo(int sx, int sy, int ex, int ey, boolean[][][] pass) {
            Map<String, Boolean> visited = new HashMap<String, Boolean>();
            Queue<CubeState> queue = new LinkedList<CubeState>();
            Orientation start = new Orientation(0, 1, 2, 3, 4, 5);
            queue.add(new CubeState(sx, sy, 0, start));
            visited.put(sx + "," + sy + "," + start.hashCode(), true);

            while (!queue.isEmpty()) {
                CubeState current = queue.poll();
                if (current.x == ex && current.y == ey && current.ori.bottom == 1) return current.steps;

                for (int d = 0; d < 4; d++) {
                    int nx = current.x + dx[d];
                    int ny = current.y + dy[d];
                    if (nx < 0 || ny < 0 || nx >= tx || ny >= ty) continue;
                    if (!pass[current.y][current.x][d]) continue;

                    Orientation newOri = current.ori.roll(d);
                    String key = nx + "," + ny + "," + newOri.hashCode();
                    if (visited.containsKey(key)) continue;

                    visited.put(key, true);
                    queue.add(new CubeState(nx, ny, current.steps + 1, newOri));
                }
            }
            return -1;
        }

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while (!(line = br.readLine()).equals("0 0")) {
                String[] parts = line.trim().split(" ");
                tx = Integer.parseInt(parts[0]);
                ty = Integer.parseInt(parts[1]);

                int rows = 2 * ty + 1;
                map = new char[rows][];
                int sx = 0, sy = 0, ex = 0, ey = 0;

                for (int i = 0; i < rows; i++) {
                    map[i] = br.readLine().toCharArray();
                    for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == 'O') {
                            sx = j / 2;
                            sy = i / 2;
                        } else if (map[i][j] == 'D') {
                            ex = j / 2;
                            ey = i / 2;
                        }
                    }
                }

                boolean[][][] pass = passable(tx, ty, map);
                int normal = bfsSimple(sx, sy, ex, ey, pass);
                int qbo = bfsQbo(sx, sy, ex, ey, pass);
                System.out.println((normal == -1 ? "IMPOSIBLE" : normal) + " " +
                        (qbo == -1 ? "IMPOSIBLE" : qbo));
            }
        }
    }