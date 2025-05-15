import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearHashing {
    static int tableSize;
    static String[] linearTable;
    static int[] linearCollisions;
    static int totalCollisions = 0;

    public LinearHashing(int size) {
        tableSize = size;
        linearTable = new String[tableSize];
        linearCollisions = new int[tableSize];
        totalCollisions = 0;
    }

    public static int hashFunction(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash += s.charAt(i);
        }
        return hash % tableSize;
    }

    public static void insertLinear(String word, int hash) {
        int i = 0;
        int idx = (hash + i) % tableSize;
        int collisions = 0;
        while (linearTable[idx] != null) {
            if (i >= tableSize) {
                System.out.println("Table is full! Could not insert: " + word);
                return;
            }
            System.out.println("[Collision] At index: " + idx + ", word: " + word + ", moving to: " + ((hash + (i + 1)) % tableSize));
            collisions++;
            i++;
            idx = (hash + i) % tableSize;
        }
        linearTable[idx] = word;
        linearCollisions[hash] += collisions;
        totalCollisions += collisions;
        System.out.println("[Linear] Inserted at: " + idx);
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) { insertLinear(word, hashFunction(word)); }
                }
            }
        } catch (IOException e) { System.err.println("Error reading file: " + e.getMessage()); }
    }

    public static void printTable() {
        for (int i = 0; i < tableSize; i++) {
            String val = linearTable[i];
            String col = linearCollisions[i] > 0 ? " [Collisions: " + linearCollisions[i] + "]" : "";
            System.out.println(i + ": " + (val != null ? val : "null") + col);
        }
    }

    public static int getCollisionCount() { return totalCollisions; }
}