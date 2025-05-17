import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Linear {
    private int size; 
    private String[] hashtable; 
    final int m = (int) (Math.pow(10, 9) + 9); // Large prime number for less collision
    private HashMap<String, ArrayList<Integer>> collisionPlaces = new HashMap<>();
    // Stores number of collisions for each word
    private HashMap<String, Integer> collisionTimes = new HashMap<>();

    public Linear(int capacity) {
        size = capacity;
        hashtable = new String[size];
    }

    // Constructor that sets size based on input word list
    public Linear(ArrayList<String> word) {
        size = word.size();
        hashtable = new String[size];
    }

    private int Technique(char c, int i, int n) {
        return (int) ((c * Math.pow(31, (n - 1 - i))) % m);
    }

    // Insert a list of strings into the hash table 
    public void insert(ArrayList<String> strings) {
        for (String word : strings) {
            int tech = 0;
            // Calculate hash value for the word
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                tech += Technique(c, j, word.length());
            }
            int i = 0;
            boolean inserted = false;
            ArrayList<Integer> places = new ArrayList<>(); // To record collision indices
            int collisions = 0;
            while (i < size) {
                int index = (tech + i) % size;
                // Prevent infinite loop if table is full
                if (i > 0 && index == tech) {
                    break;
                }
                if (hashtable[index] == null) {
                    hashtable[index] = word; // Insert word if slot is empty
                    inserted = true;
                    break;
                } else {
                    collisions++; 
                    places.add(index); 
                }
                i++;
            }
            collisionPlaces.put(word, places); 
            collisionTimes.put(word, collisions); 

            if (!inserted) {
                System.out.println("The table is full❌ or infinite loop occured at word: " + word);
            }
        }
    }

    public void loadFromFile(String filename) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        insert(words);
    }

    public void printTable() {
        System.out.println("\nLinear:");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        System.out.println("| Table Index |  Stored Word   | Original Hash | Collisions at Index |");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        for (int i = 0; i < size; i++) {
            String word = hashtable[i];
            String displayWord = (word == null) ? "Empty" : word;
            String originalHash = "";
            int collisionsAtIndex = 0;
            if (word != null) {
                // Calculate original hash for display
                int tech = 0;
                for (int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);
                    tech += Technique(c, j, word.length());
                }
                originalHash = String.valueOf(tech % size);
                // Count how many collisions in this index 
                collisionsAtIndex = 0;
                for (ArrayList<Integer> places : collisionPlaces.values()) {
                    for (int idx : places) {
                        if (idx == i) collisionsAtIndex++;
                    }
                }
            }
            System.out.printf("| %11d | %14s | %13s | %19d |\n", i, displayWord, originalHash, collisionsAtIndex);
        }
        System.out.println("+-------------+----------------+---------------+---------------------+");
    }
    
    public int getCollisionCount() {
        int total = 0;
        for (int count : collisionTimes.values()) {
            total += count;
        }
        return total;
    }
}
