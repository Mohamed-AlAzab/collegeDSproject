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
        System.out.println();
        System.out.println("=========================");
        System.out.println("| 1 | Double Hashing    |");
        System.out.println("=========================");
        System.out.println("| 2 | Quadratic Hashing |");
        System.out.println("=========================");
        System.out.println("| 3 | Linear Hashing    |");
        System.out.println("=========================");
        System.out.println("| 4 | Open Hashing      |");
        System.out.println("=========================");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the hashing type(1 - 2 - 3 - 4): ");
        int hashType = Integer.parseInt(scanner.nextLine());
        switch (hashType){
            case 1:
                DoubleHashFunction(scanner);
                break;
            case 2:
                QuadraticHashFunction(scanner);
                break;
            case 3:
                LinearHashFunction(scanner);
                break;
            case 4:
                OpenHashFunction(scanner);
                break;
            default:
                System.out.println("Hash type not found!");
        }
    }
    public static void QuadraticHashFunction(Scanner scanner){
        System.out.print("Write the table size: ");
        int tableSize = Integer.parseInt(scanner.nextLine());
        QuadraticProbing quadraticProbing = new QuadraticProbing(tableSize);

        quadraticProbing.loadFromFile("words.txt");
        quadraticProbing.printTable();

        System.out.println("\nTotal Collisions: " + quadraticProbing.getCollisionCount());
    }

    public static void DoubleHashFunction(Scanner scanner){
        System.out.print("Write the table size: ");
        int tableSize = Integer.parseInt(scanner.nextLine());
        DoubleHash doubleHash = new DoubleHash(tableSize);

        doubleHash.loadFromFile("words.txt");
        doubleHash.printTable();

        System.out.println("\nTotal Collisions: " + doubleHash.getCollisionCount());
    }

    public static void LinearHashFunction(Scanner scanner){
        System.out.print("Write the table size: ");
        int tableSize = Integer.parseInt(scanner.nextLine());
    }

    public static void OpenHashFunction(Scanner scanner){
        System.out.print("Write the table size: ");
        int tableSize = Integer.parseInt(scanner.nextLine());
    }
}