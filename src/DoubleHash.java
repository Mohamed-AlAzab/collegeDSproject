import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DoubleHash {
    private Entry[] table;
    private int[] collisionMap; // Tracks how many times each index was probed due to collision
    private int size;
    private int capacity;
    private int collisionCount;

    private final int B = 31;
    private final int M = 1000000009;

    public DoubleHash(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.collisionMap = new int[capacity];
        this.size = 0;
        this.collisionCount = 0;
    }

    // Internal class to store word and number of collisions before it was inserted
    private static class Entry {
        String word;
        int insertCollisions;

        Entry(String word, int insertCollisions) {
            this.word = word;
            this.insertCollisions = insertCollisions;
        }
    }

    // Polynomial rolling hash function
    public int hash(String s) {
        long hash = 0;
        long power = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            hash = (hash + (s.charAt(i) * power) % M) % M;
            power = (power * B) % M;
        }

        return (int) hash;
    }

    public int primaryHash(String s) { return hash(s) % capacity; }

    public int secondaryHash(String s) { return 97 - (hash(s) % 97); }

    public void insert(String word) {
        word = word.toLowerCase();
        int h1 = primaryHash(word);
        int h2 = secondaryHash(word);
        int originalCollisionCount = collisionCount;

        for (int i = 0; i < capacity; i++) {
            int index = (h1 + i * h2) % capacity;

            if (table[index] == null) {
                int wordCollisions = collisionCount - originalCollisionCount;
                table[index] = new Entry(word, wordCollisions);
                size++;

                System.out.printf("Inserted '%s' at index %d", word, index);
                if (wordCollisions > 0) {
                    System.out.printf(" (with %d collision%s)", wordCollisions, wordCollisions > 1 ? "s" : "");
                }
                System.out.println();
                return;
            }

            if (table[index].word.equals(word)) {
                System.out.printf("Skipped duplicate '%s'\n", word);
                return;
            }

            // A collision occurred at this index
            collisionMap[index]++;
            collisionCount++;
        }

        System.out.println("Table is full. Couldn't insert: " + word);
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

    public void printTable() {
        System.out.println("\n--- Double Hashing Table ---");
        for (int i = 0; i < capacity; i++) {
            String status = (table[i] == null) ? "empty" : table[i].word;
            System.out.printf("%d: %s (collisions at this index: %d)\n", i, status, collisionMap[i]);
        }
    }

    public int getCollisionCount() {
        return collisionCount;
    }
}