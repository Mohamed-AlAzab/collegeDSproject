import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Linear {
    private int size;
    private String[] hashtable;
    final int m = (int) (Math.pow(10, 9) + 9);
    private HashMap<String, ArrayList<Integer>> collisionPlaces = new HashMap<>();
    private HashMap<String, Integer> collisionTimes = new HashMap<>();

    public Linear(int capacity) {
        size = capacity;
        hashtable = new String[size];
    }

    public Linear(ArrayList<String> word) {
        size = word.size();
        hashtable = new String[size];
    }

    private int Technique(char c, int i, int n) {
        return (int) ((c * Math.pow(31, (n - 1 - i))) % m);
    }

    public void insert(ArrayList<String> strings) {
        for (String word : strings) {
            int tech = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                tech += Technique(c, j, word.length());
            }
            int i = 0;
            boolean inserted = false;
            ArrayList<Integer> places = new ArrayList<>();
            int collisions = 0;
            while (i < size) {
                int index = (tech + i) % size;
                if (i > 0 && index == tech) {
                    break;
                }
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
                // cal original hash
                int tech = 0;
                for (int j = 0; j < word.length(); j++) {
                    char c = word.charAt(j);
                    tech += Technique(c, j, word.length());
                }
                originalHash = String.valueOf(tech % size);
                // num of coli in this place
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
