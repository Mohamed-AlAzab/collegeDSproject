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
                DoubleHashFunction();
                break;
            case 2:
                QuadraticHashFunction();
                break;
            case 3:
                LinearHashFunction();
                break;
            case 4:
                OpenHashFunction();
                break;
            default:
                System.out.println("Hash type not found!");
        }
    }
    public static void QuadraticHashFunction(){
        int tableSize = 5;
        QuadraticProbing quadraticProbing = new QuadraticProbing(tableSize);

        quadraticProbing.loadFromFile("words.txt");
        quadraticProbing.printTable();

        System.out.println("\nTotal Collisions: " + quadraticProbing.getCollisionCount());
    }

    public static void DoubleHashFunction(){
        int tableSize = 5;
        DoubleHash doubleHash = new DoubleHash(tableSize);

        doubleHash.loadFromFile("words.txt");
        doubleHash.printTable();

        System.out.println("\nTotal Collisions: " + doubleHash.getCollisionCount());
    }

    public static void LinearHashFunction(){}

    public static void OpenHashFunction(){}
}