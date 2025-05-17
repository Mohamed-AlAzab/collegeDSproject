import java.util.ArrayList;
import java.util.HashMap;
public class Linear {
    private int size;
    private String[]hashtable;
    final int m = (int) (Math.pow(10, 9) + 9);
    private HashMap<String, ArrayList<Integer>> collisionPlaces = new HashMap<>();
    private HashMap<String, Integer> collisionTimes = new HashMap<>();
    public Linear(int capacity){
        size=capacity;
        hashtable=new String[size];
    }
    public  Linear(ArrayList<String>word){
        size=word.size();
        hashtable=new String[size];
    }
    private int Technique(char c, int i, int n) {
        return (int) ((c * Math.pow(31, (n - 1 - i))) % m);
    }
    public void insert(ArrayList<String>strings){
        for(String word:strings){
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
                    //System.out.println("Cannot insert element ❌: " + word + " (Returned to starting index)");
                    break;
                }
                if (hashtable[index] == null) {
                    hashtable[index] = word;
                    inserted = true;
                    break;
                } else {
                    collisions++;
                    places.add(index); // تسجّل موقع التصادم
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
    private void dispalyPalces(){
        for(String word: collisionPlaces.keySet()){
            if(collisionTimes.get(word)!=0) {
                System.out.print("The Word: " + word);
                System.out.println(collisionPlaces.get(word));
                System.out.println("Number of collisions"+collisionTimes.get(word));
            }
        }
    }
    private void dispalyHashTable(){
        System.out.println("Array content📦");
        for (int i = 0; i < size; i++)  {
            System.out.println(i + ": " + (hashtable[i] == null ? "Empty" : hashtable[i]));
        }
    }
    public void display(){
        dispalyPalces();
        dispalyHashTable();
    }
}
