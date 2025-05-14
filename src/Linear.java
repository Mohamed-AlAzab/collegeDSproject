//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class LinearHashing {
//
//    static final int TABLE_SIZE = 52;
//    static Character[] hashTable = new Character[TABLE_SIZE];
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<Character> character = new ArrayList<>();
//
//        System.out.println("Enterrrrrrrrrrrrrrrrrrrrr : ");
//        String input = scanner.nextLine();
//
////        for (char c : input.toCharArray()) {
////            if (Character.isLetter(c)) {
////                insert(c);
////            }
////        }
//
//        for (char c : input.toCharArray()) {
//            if (Character.isLetter(c)) {
//                character.add(c);
//            }
//        }
//
//        for (char c : character) {
//            insert(c);
//        }
//        printHashTable();
//    }
//
//    public static void insert(char c) {
//        int hash = hashFunction(c);
//        int index = hash % TABLE_SIZE;
//
//        while (hashTable[index] != null) {
//            index = (index + 1) % TABLE_SIZE;
//        }
//
//        hashTable[index] = c;
//    }
//
//    public static int hashFunction(char c) {
//        if (Character.isLowerCase(c)) {
//            return c - 'a';
//        } else {
//            return (c - 'A') + 26;
//        }
//    }
//
//    public static void printHashTable() {
//        System.out.println("Hash table :");
//        for (int i = 0; i < TABLE_SIZE; i++) {
//            System.out.println(i + ": " + (hashTable[i] != null ? hashTable[i] : "null"));
//        }
//    }
//}
