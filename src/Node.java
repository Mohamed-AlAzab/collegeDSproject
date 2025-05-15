public class Node {
    int Data ;
    Node Next;
    String word;

    public Node(int data) {
        Data = data;
        this.Next = null;
    }

    Node(String word) {
        this.word = word;
        this.Next = null;
    }
}
