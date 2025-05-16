import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearHashing {
    private int tableSize;
    private String[] linearTable;
    private int[] linearCollisions;
    private int totalCollisions;

    public LinearHashing(int size) {
        this.tableSize = size;
        this.linearTable = new String[tableSize];
        this.linearCollisions = new int[tableSize];
        this.totalCollisions = 0;
    }

    public int hashFunction(String s) {
        int hash = 7;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * 31 + s.charAt(i);
        }
        return Math.abs(hash % tableSize);
    }

    public void insertLinear(String word) {
        int hash = hashFunction(word);
        int i = 0;
        int idx = (hash + i) % tableSize;
        int collisions = 0;

        while (linearTable[idx] != null) {
            if (linearTable[idx].equals(word)) {
                System.out.println("[Duplicate] Word '" + word + "' already exists at index: " + idx);
                return;
            }

            if (i >= tableSize) {
                System.out.println("Table is full! Could not insert: " + word);
                return;
            }

            collisions++;
            i++;
            idx = (hash + i) % tableSize;
        }

        linearTable[idx] = word;
        linearCollisions[idx] = collisions;
        totalCollisions += collisions;
        System.out.println("[Linear] Inserted '" + word + "' at: " + idx + " (Collisions: " + collisions + ")");
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        insertLinear(word);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void printTable() {
        System.out.println("\nLinear Hashing Table:");
        for (int i = 0; i < tableSize; i++) {
            String val = linearTable[i];
            String col = linearCollisions[i] > 0 ? " [Collisions: " + linearCollisions[i] + "]" : "";
            System.out.println("    " + i + ": " + (val != null ? val : "null") + col);
        }
    }

    public int getCollisionCount() { return totalCollisions; }
}
