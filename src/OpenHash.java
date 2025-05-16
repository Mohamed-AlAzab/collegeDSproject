import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class OpenHash {
    private Node[] hashTable;
    private int size;
    private int[] collisionCount;
    private int m = 1000000009;
    private int b = 31;

    OpenHash(int size) {
        this.size = size;
        this.hashTable = new Node[size];
        this.collisionCount = new int[size];
    }

    private int hashFunction(String word) {
        int h = 0;
        for (int i = 0; i < word.length(); i++) {
            h = (h * b + word.charAt(i)) % m;
        }
        return h;
    }

    public void insert(String word) {
        int hashValue = hashFunction(word);
        int index = Math.abs(hashValue) % size;

        Node newnode = new Node(word);

        if (hashTable[index] != null) {
            collisionCount[index]++;
            newnode.Next = hashTable[index];
        }
        hashTable[index] = newnode;
    }

    public void loadFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.trim().toLowerCase().split("\\s+");

                for (String word : words) {
                    if (!word.isEmpty()) { insert(word.toLowerCase()); }
                }
            }
        } catch (IOException e) { System.err.println("Error reading file: " + e.getMessage()); }
    }

    public void printTable() {
        System.out.println("\nOpen Hashing Table:");
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                System.out.print("    Index " + i + " [collision] " + collisionCount[i] + " : ");
                Node current = hashTable[i];

                while (current != null) {
                    System.out.print(" -> " + current.word);
                    current = current.Next;
                }
                System.out.println(" -> null");
            }
        }
    }
}