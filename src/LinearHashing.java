import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LinearHashing {
    private int size; 
    private String[] hashtable; 
    final int m = (int) (Math.pow(10, 9) + 9); // Large prime number for less collision
    private HashMap<String, ArrayList<Integer>> collisionPlaces = new HashMap<>();
    // Stores number of collisions for each word
    private HashMap<String, Integer> collisionTimes = new HashMap<>();

    public LinearHashing(int capacity) {
        size = capacity;
        hashtable = new String[size];
    }

    private int hash(String word) {
        long hash = 0;
        for (int i = 0; i < word.length(); i++) {
            hash = (hash * 31 + word.charAt(i)) % m;
        }
        return (int)(hash % size);
    }

    public void insert(String word) {
        int tech = hash(word);
        int i = 0;
        boolean inserted = false;
        ArrayList<Integer> places = new ArrayList<>();
        int collisions = 0;

        while (i < size) {
            int index = (tech + i) % size;
            if (i > 0 && index == tech) break;

            if (hashtable[index] == null) {
                hashtable[index] = word;
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
            System.out.println("Table is full or infinite loop. Couldn't insert: " + word);
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

    public void printTable() {
        System.out.println("+-------------+----------------+---------------+---------------------+");
        System.out.println("| Table Index |  Stored Word   | Original Hash | Collisions at Index |");
        System.out.println("+-------------+----------------+---------------+---------------------+");
        for (int i = 0; i < size; i++) {
            String word = hashtable[i];
            String displayWord = (word == null) ? "Empty" : word;
            String originalHash = "";
            int collisionsAtIndex = 0;
            if (word != null) {
                originalHash = String.valueOf(hash(word) % size);
                for (ArrayList<Integer> places : collisionPlaces.values()) {
                    for (int idx : places)
                        if (idx == i) collisionsAtIndex++;
                }
            }
            if(!Objects.equals(displayWord, "Empty"))
                System.out.printf("| %11d | %14s | %13s | %19d |\n", i, displayWord, originalHash, collisionsAtIndex);
        }
        System.out.print("+-------------+----------------+---------------+---------------------+");
    }
    
    public int getCollisionCount() {
        int total = 0;
        for (int count : collisionTimes.values()) total += count;
        return total;
    }
}