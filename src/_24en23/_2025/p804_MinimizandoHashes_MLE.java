package _24en23._2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author santi
 * @date 19/06/2025
 */

// v1.- Usar un Trie (arbol de prefijos) para almacenar los hashes
//      Y contar cuántos hashes pasan por cada nodo

public class p804_MinimizandoHashes_MLE {

    // Clase interna para representar un nodo del Trie
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0; // nombre de hashes que passen per aquest node
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            sc.nextLine();
            // No hay más casos
            if (n == 0) break;

            // Caso especial: si hay una única hash, no se necesita ningún caracter
            if (n == 1) {
                sc.nextLine(); // leer la única hash, pero sin procesarla
                System.out.println(0);
                continue;
            }

            String[] hashes = new String[n];
            for (int i = 0; i < n; i++) {
                hashes[i] = sc.nextLine();
            }

            TrieNode root = new TrieNode();

            // Inserim totes les hashes al trie
            for (String hash : hashes) {
                TrieNode node = root;
                for (char c : hash.toCharArray()) {
                    if (!node.children.containsKey(c)) {
                        // Si el caracter no existe, lo añadimos
                        node.children.put(c, new TrieNode());
                    }
                    node = node.children.get(c);
                    node.count++;
                }
            }

            // Para cada hash, recorremos el Trie y contamos los caracteres necesarios
            // para que sea único
            int totalMinChars = 0;
            for (String hash : hashes) {
                TrieNode node = root;
                int prefixLength = 0;
                for (char c : hash.toCharArray()) {
                    node = node.children.get(c);
                    prefixLength++;
                    if (node.count == 1) break;
                }
                totalMinChars += prefixLength;
            }

            // Imprimimos el total de caracteres mínimos necesarios
            System.out.println(totalMinChars);
        }
    }
}

