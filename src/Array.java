import java.util.Scanner;

public class Array {
    public Node[] ptr;
    private int length;
    private int size;

    public Array(int arraySize) { ptr = new Node[arraySize]; }

    public void insert(int index, int newItem) {
        Node newnode = new Node(newItem);
        Node temp = ptr[index];
        if (ptr[index] == null) {
            ptr[index] = newnode;
        } else {
            while (temp.Next != null) {
                temp = temp.Next;
            }
            temp.Next = newnode;
            newnode = null;
        }
    }

    public void insert(int index, String newItem) {
        Node newnode = new Node(newItem);
        Node temp = ptr[index];
        if (ptr[index] == null) {
            ptr[index] = newnode;
        } else {
            while (temp.Next != null) {
                temp = temp.Next;
            }
            temp.Next = newnode;
            newnode = null;
        }
    }

    public void display1() {
        int len = ptr.length;
        for (int x = 0; x < len; x++) {
            System.out.print("index " + x + ": ");
            Node temp = ptr[x];
            while (temp != null) {
                System.out.print(temp.Data + "->");
                temp = temp.Next;
            }
            System.out.println("null");
        }
    }

    public void display2() {
        int len = ptr.length;
        for (int x = 0; x < len; x++) {
            System.out.print("index " + x + ": ");
            Node temp = ptr[x];
            while (temp != null) {
                System.out.print((char)temp.Data + "->");
                temp = temp.Next;
            }
            System.out.println("null");
        }
    }
}