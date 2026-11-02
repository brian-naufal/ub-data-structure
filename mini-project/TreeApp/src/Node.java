package src;

public class Node {
    int data;
    Node left = null;
    Node right = null;

    public Node(int data) {
        this.data = data;
    }

    public void insertData(int data) {
        if (data < this.data) {
            if (this.left == null) {
                left = new Node(data);
            } else {
                left.insertData(data);
            }
        } else if (data > this.data) {
            if (this.right == null) {
                right = new Node(data);
            } else {
                right.insertData(data);
            }
        }
    }
}