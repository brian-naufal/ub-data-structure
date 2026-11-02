public class Node {
    Object data;
    Node next;
    public Node prev;

    public Node() {
    }

    public Node(Object data) {
        this.data = data;
        this.next = null;
    }
}
