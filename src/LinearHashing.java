public class LinearHashing {
    static int tableSize;
    static String[] linearTable;
    static int[] linearCollisions;
    static int totalCollisions = 0;

    public static void init(int size) {
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
            System.out.println("    [Collision] At index: " + idx + ", word: " + word + ", moving to: " + ((hash + (i + 1)) % tableSize));
            collisions++;
            i++;
            idx = (hash + i) % tableSize;
        }
        linearTable[idx] = word;
        linearCollisions[hash] += collisions;
        totalCollisions += collisions;
        System.out.println("  [Linear] Inserted at: " + idx + (collisions > 0 ? " (collision)" : ""));
    }

    public static void printTable() {
        for (int i = 0; i < tableSize; i++) {
            String val = linearTable[i];
            String col = linearCollisions[i] > 0 ? " [Collisions: " + linearCollisions[i] + "]" : "";
            System.out.println(i + ": " + (val != null ? val : "null") + col);
        }
        System.out.println("Total Collisions: " + totalCollisions);
    }
}