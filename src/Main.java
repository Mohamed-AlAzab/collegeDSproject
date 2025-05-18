import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("|  #    #   #    #### #    #    #     #   #   #####   |");
        System.out.println("|  #    #  # #  #     #    #    ##   ##  # #  #    #  |");
        System.out.println("|  #    # #   # #     #    #    # # # # #   # #    #  |");
        System.out.println("|  ###### #####  ###  ######    #  #  # ##### #####   |");
        System.out.println("|  #    # #   #     # #    #    #     # #   # #       |");
        System.out.println("|  #    # #   #     # #    #    #     # #   # #       |");
        System.out.println("|  #    # #   # ####  #    #    #     # #   # #       |");
        System.out.println("=======================================================");
        System.out.println("| 1 | Double Hashing    |");
        System.out.println("=========================");
        System.out.println("| 2 | Quadratic Hashing |");
        System.out.println("=========================");
        System.out.println("| 3 | Linear Hashing    |");
        System.out.println("=========================");
        System.out.println("| 4 | Open Hashing      |");
        System.out.println("=========================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the hashing type(1 - 2 - 3 - 4): ");
        int hashType = Integer.parseInt(scanner.nextLine());
        System.out.print("Write the table size: ");
        int tableSize = Integer.parseInt(scanner.nextLine());
        if(tableSize <= 1){
            System.out.println("Wrong table size.");
            return;
        }
        switch (hashType){
            case 1:
                DoubleHashFunction(tableSize);
                break;
            case 2:
                QuadraticHashFunction(tableSize);
                break;
            case 3:
                LinearHashFunction(tableSize);
                break;
            case 4:
                OpenHashFunction(tableSize);
                break;
            default:
                System.out.println("Hash type not found!");
        }
    }

    public static void DoubleHashFunction(int tableSize){
        DoubleHashing doubleHash = new DoubleHashing(tableSize);

        doubleHash.loadFromFile("words.txt");
        doubleHash.printTable();

        System.out.println("\nTotal Collisions: " + doubleHash.getCollisionCount());
    }

    public static void QuadraticHashFunction(int tableSize){

        QuadraticHashing quadraticProbing = new QuadraticHashing(tableSize);

        quadraticProbing.loadFromFile("words.txt");
        quadraticProbing.printTable();

        System.out.println("\nTotal Collisions: " + quadraticProbing.getCollisionCount());
    }

    public static void LinearHashFunction(int tableSize){
        LinearHashing linearHashing = new LinearHashing(tableSize);

        linearHashing.loadFromFile("words.txt");
        //System.out.println("Table:");
        linearHashing.printTable();

        System.out.println("\nTotal Collisions: " + linearHashing.getCollisionCount());
    }

    public static void OpenHashFunction(int tableSize){
        OpenHashing openHash = new OpenHashing(tableSize);

        openHash.loadFromFile("words.txt");
        openHash.printTable();
    }
}