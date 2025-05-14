public class Node {
    int Data ;
    Node Next;
    String StrData;

    public Node() {
        Data = 0;
        Next = null;
    }

    public Node(int data) {
        Data = data;
        Next = null;
    }

    public Node(String strData) {
        StrData = strData;
        Next = null;
    }
}
