import java.io.*;

public class QuadraticHashing {
    private String[] hashTable;
    private int[] collisionCounts;
    private boolean[] occupied;
    private int tableSize;
    private int collisionCount;

    public QuadraticHashing(int size) {
        this.tableSize = size;
        this.hashTable = new String[tableSize];
        this.collisionCounts = new int[tableSize];
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

        while (probes < tableSize) {
            index = (primaryIndex + probes + probes * probes) % tableSize;

            if (!occupied[index]) {
                hashTable[index] = word;
                occupied[index] = true;
                collisionCounts[index] = probes; // ← store the number of collisions at index
                collisionCount += probes;
                return;
            }

            probes++;

            if (index == primaryIndex && probes != 0) {
                System.out.printf("Failed to insert '%s': We returned to the starting point after a full loop.\n", word);
                return;
            }
        }
        System.out.println("Table is full. Couldn't insert: " + word);
    }

    public void printTable() {
        System.out.println("\nQuadratic Probing:");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        System.out.println("| Table Index |  Stored Word   | Original Hash | Collisions at Index |");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        for (int i = 0; i < tableSize; i++) {
            String word = (occupied[i]) ? hashTable[i] : "Empty";
            String hashValue = (occupied[i]) ? String.valueOf(hash(word) % tableSize) : "0";
            int collisions = (occupied[i]) ? collisionCounts[i] : 0; // assumes you have a collisionCounts[] array
            if (!word.equals("Empty")) {
                System.out.printf("| %11d | %14s | %13s | %19d |\n", i, word, hashValue, collisions);
            }
        }
        System.out.print("+-------------+----------------+---------------+---------------------+");
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