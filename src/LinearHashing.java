import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LinearHashing {
    static final int TABLE_SIZE = 52;
    static Character[] hashTable = new Character[TABLE_SIZE];

    public static void insert(char c) {
        int hash = hashFunction(c);
        int index = hash % TABLE_SIZE;

        while (hashTable[index] != null) {
            index = (index + 1) % TABLE_SIZE;
        }

        hashTable[index] = c;
    }

    public static int hashFunction(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a';
        } else {
            return (c - 'A') + 26;
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) { /* Make insert method take string */ }
                }
            }
        } catch (IOException e) { System.err.println("Error reading file: " + e.getMessage()); }
    }

    public static void printHashTable() {
        System.out.println("Hash table :");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.println(i + ": " + (hashTable[i] != null ? hashTable[i] : "null"));
        }
    }
}