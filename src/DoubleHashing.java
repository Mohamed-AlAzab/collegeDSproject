import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class DoubleHashing {
    private Entry[] table;
    private int[] collisionMap;
    private int size;
    private int capacity;
    private int collisionCount;

    private final int B = 31;
    private final int M = 1000000009;

    public DoubleHashing(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.collisionMap = new int[capacity];
        this.size = 0;
        this.collisionCount = 0;
    }

    private static class Entry {
        String word;
        int insertCollisions;

        Entry(String word, int insertCollisions) {
            this.word = word;
            this.insertCollisions = insertCollisions;
        }
    }

    public int hash(String s) {
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = (hash + (long) s.charAt(i) * power(B, s.length() - 1 - i)) % M;
        }
        return (int) hash;
    }

    private long power(int base, int exp) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % M;
        }
        return result;
    }

    public int primaryHash(String s) {
        return hash(s) % capacity;
    }

    public int secondaryHash(String s) {
        return 1 + (hash(s) % (capacity - 1)); // Ensure h2 mod capacity != 0
    }

    public void insert(String word) {
        word = word.toLowerCase();
        int h1 = primaryHash(word);
        int h2 = secondaryHash(word);
        int wordCollisions = 0; // Track collisions for this word
        boolean[] visited = new boolean[capacity];

        for (int i = 0; i < capacity; i++) {
            int index = (h1 + i * h2) % capacity;

            if (visited[index]) {
                System.out.println("Cycle detected. Couldn't insert: " + word);
                return;
            }
            visited[index] = true;

            if (table[index] == null) {
                table[index] = new Entry(word, wordCollisions);
                size++;
                collisionCount += wordCollisions; // Only count collisions for successful insertions
                System.out.printf("Inserted '%s' at index %d", word, index);
                if (wordCollisions > 0) {
                    System.out.printf(" (with %d collision%s)", wordCollisions, wordCollisions > 1 ? "s" : "");
                }
                System.out.println();
                return;
            }

            collisionMap[index]++;
            wordCollisions++;
        }

        System.out.println("Table is full. Couldn't insert: " + word);
    }

    public void loadFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        insert(word);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void printTable() {
        System.out.println("\nDouble Hashing:");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        System.out.println("| Table Index |  Stored Word   | Original Hash | Collisions at Index |");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        for (int i = 0; i < capacity; i++) {
            String word = (table[i] == null) ? "Empty" : table[i].word;
            String hashValue = (table[i] == null) ? "0" : String.valueOf(hash(word) % size);
            int collisions = (table[i] == null) ? 0 : table[i].insertCollisions;
            if(!Objects.equals(word, "Empty"))
                System.out.printf("| %11d | %14s | %13s | %19d |\n", i, word, hashValue, collisions);
        }
        System.out.print("+-------------+----------------+---------------+---------------------+");
    }

    public int getCollisionCount() {
        return collisionCount;
    }
}