import java.io.*;
import java.util.Scanner;

public class QuadraticProbing {
    private String[] hashTable;
    private boolean[] occupied;
    private int tableSize;
    private int collisionCount;

    public QuadraticProbing(int size) {
        this.tableSize = size;
        this.hashTable = new String[tableSize];
        this.occupied = new boolean[tableSize];
        this.collisionCount = 0;
    }

    private long hash(String word) {
        long hash = 0;
        long b = 31;
        long m = 1000000009L;
        int n = word.length();

        for (int i = 0; i < n; i++) {
            long power = modPow(b, n - i - 1, m);
            hash = (hash + (word.charAt(i) * power) % m) % m;
        }
        return hash;
    }

    private long modPow(long base, int exponent, long mod) {
        long result = 1;
        base %= mod;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exponent >>= 1;
        }
        return result;
    }

    private int primaryHash(String word) { return (int)(hash(word) % tableSize); }

    public void insert(String word) {
        int primaryIndex = primaryHash(word);
        int index;
        int probes = 0;

        System.out.printf("Trying to insert '%s'. Primary index: %d\n", word, primaryIndex);

        while (probes < tableSize) {
            index = (primaryIndex + probes + probes * probes) % tableSize;

            if (!occupied[index]) {
                hashTable[index] = word;
                occupied[index] = true;
                System.out.printf("Inserted '%s' at index %d (probes: %d)\n", word, index, probes);
                collisionCount += probes;
                return;
            } else {
                System.out.printf("Collision at index %d (probe %d)\n", index, probes);
            }

            probes++;

            if (index == primaryIndex && probes != 0) {
                System.out.printf("Failed to insert '%s': We returned to the starting point after a full loop.\n", word);
                return;
            }
        }

        System.out.printf("Table is full. Could not insert '%s'.\n", word);
    }

    public void printTable() {
        System.out.println("\nQuadratic Probing Hash Table:");
        for (int i = 0; i < tableSize; i++) {
            System.out.printf("    Index %d: %s\n", i, occupied[i] ? hashTable[i] : "empty");
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) { insert(word); }
                }
            }
        } catch (IOException e) { System.err.println("Error reading file: " + e.getMessage()); }
    }

    public int getCollisionCount() { return collisionCount; }
}