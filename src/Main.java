public class Main {
    public static void main(String[] args) {
        int tableSize = 5;
        DoubleHash dh = new DoubleHash(tableSize);

        dh.loadFromFile("words.txt"); 
        dh.printTable();

        System.out.println("\nTotal Collisions: " + dh.getCollisionCount());
    }
}
