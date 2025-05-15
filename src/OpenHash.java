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

    public void Filereader(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.trim().toLowerCase().split("\\s+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    insert(word);
                }
            }
        }
        reader.close();
    }

    private int hashFunction(String word) {
        int h = 0;
        for (int i = 0; i < word.length(); i++) {
            h = (h * b + word.charAt(i)) % m;
        }
        return h;
    }


    public void insert(String word) {
        word = word.toLowerCase();
        int hashvalue = hashFunction(word);
        int index = Math.abs(hashvalue) % size;

        Node newnode = new Node(word);

        if (hashTable[index] != null) {
            collisionCount[index]++;
            newnode.Next = hashTable[index];
        }
        hashTable[index] = newnode;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            if (hashTable[i] != null) {
                System.out.print("Index " + i + " [collision] "+collisionCount[i]+":");
//                System.out.print("Inde

                Node current = hashTable[i];

                while (current != null) {
                    System.out.print(current.word);
//                    System.out.print(current.Data);
                    if (current.Next != null) {
                        System.out.print(" -> ");
                    }
                    current = current.Next;
                }
                System.out.println(" -> null");
            }
        }
    }


}
