package src;
public class Node {
    int data;
    Node left = null;
    Node right = null;
    int height = 1;

    public Node(int data) {
        this.data = data;
    }
}