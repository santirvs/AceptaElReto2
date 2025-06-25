package _24en23._2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author santi
 * @date 24/06/2025
 */

// v0-Empiezo de nuevo después varios intentos infructuosos combinando LIS

// v1-Para cada vehiculo me guardo el valor más alto, el más bajo y la cantidad de coches cargados
// Cuando llega un vehículo nuevo:
// a) es más ligero que el actual?  --> Se carga por delante, se actualiza el ligero y se incrementa 1
// b) es más pesado que el actual?  --> Se carga por detrás, se actualiza el pesado y se incrementa 1
// c) es intermedio? habrá que descartarlo o descartar algunos de los que ya se hayan cargado
//     cuales se descartan?



public class p810_ElMozoDeEstacion_TLE2 {


    static boolean canBuild(int[] arr, int len) {
        Deque<Integer> deque = new ArrayDeque<Integer>();
        return canBuild(arr, len, 0, deque);
    }

    static boolean canBuild(int[] arr, int len, int start, Deque<Integer> deque) {
        if (len == 0) return true;
        if (start >= arr.length) return false;

        for (int i = start; i <= arr.length - len; i++) {
            int val = arr[i];
            // Check if we can insert val to the front
            if (deque.isEmpty() || val < deque.getFirst()) {
                deque.addFirst(val);
                if (canBuild(arr, len - 1, i + 1, deque)) return true;
                deque.removeFirst();
            }
            // Check if we can insert val to the back
            if (deque.isEmpty() || val > deque.getLast()) {
                deque.addLast(val);
                if (canBuild(arr, len - 1, i + 1, deque)) return true;
                deque.removeLast();
            }
        }
        return false;
    }

    static int solve(int[] arr) {
        int low = 1, high = arr.length, best = 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canBuild(arr, mid)) {
                best = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return best;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.equals("0")) break;
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);
            String[] parts = br.readLine().trim().split(" ");
            int[] cars = new int[n];
            for (int i = 0; i < n; i++) {
                cars[i] = Integer.parseInt(parts[i]);
            }

            System.out.println(solve(cars));
        }
    }
}
