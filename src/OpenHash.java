import java.util.Scanner;

public class OpenHash {
    Array array;
    Scanner sc = new Scanner(System.in);
    private int size;

    public OpenHash(int size) {
        array = new Array(size);
        this.size =size;
    }


    public OpenHash() {
        array = new Array(10);
        this.size =10;

    }

    public void insert(int newitem) {
        int index = newitem % size;
        array.insert(index, newitem);
    }

    public void insert(String newItem) {
        int x = newItem.length();
        for (int i = 0; i < x; i++) {
            char ch = newItem.charAt(i);
            int index = ch % size;
            array.insert(index, ch);
            display2();
        }
    }

    public void display2() {
        array.display2();
    }

    public void display1() {
        array.display1();
    }

//    //if doc want insert value by scanner
//    public void insertv2() {
//        System.out.println("enter the number you want put Hash ");
//        int newitem = sc.nextInt();
//        System.out.println("enter the number you want mod it ");
//        int the_Number_you_want_mod = sc.nextInt();
//        int index = newitem % the_Number_you_want_mod;
//        array.insert(index, newitem);
//    }

}
