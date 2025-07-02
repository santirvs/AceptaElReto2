package _24en23._2025;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author santi
 * @date 02/07/2025
 */

// v1. Parece ser un claro ConvexHull

public class p818_UnPradoParaMisOvejas_MLE {


    static class Point implements Comparable<Point> {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            if (this.x != p.x) return Long.compare(this.x, p.x);
            return Long.compare(this.y, p.y);
        }
    }

    static long cross(Point O, Point A, Point B) {
        return (A.x - O.x) * (B.y - O.y) - (A.y - O.y) * (B.x - O.x);
    }

    static List<Point> convexHull(Point[] points) {
        Arrays.sort(points);
        int n = points.length;
        List<Point> hull = new ArrayList<>();

        // Lower hull
        for (int i = 0; i < n; i++) {
            while (hull.size() >= 2 &&
                    cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        // Upper hull
        int t = hull.size() + 1;
        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() >= t &&
                    cross(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) <= 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        hull.remove(hull.size() - 1); // remove duplicate point
        return hull;
    }

    static double polygonArea(List<Point> poly) {
        double area = 0.0;
        int n = poly.size();
        for (int i = 0; i < n; i++) {
            Point p1 = poly.get(i);
            Point p2 = poly.get((i + 1) % n);
            area += (p1.x * p2.y - p2.x * p1.y);
        }
        return Math.abs(area) / 2.0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            int n = Integer.parseInt(line.trim());
            if (n == 0) break;

            Point[] points = new Point[n];
            String[] coords = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                long x = Long.parseLong(coords[2 * i]);
                long y = Long.parseLong(coords[2 * i + 1]);
                points[i] = new Point(x, y);
            }

            List<Point> hull = convexHull(points);
            long area2x = (long) (polygonArea(hull) * 2L); // Dobla el área para evitar decimales

            if (area2x % 2 == 0) {
                System.out.println(area2x / 2);
            } else {
                System.out.println((area2x / 2) + ".5");
            }

        }
    }
}
