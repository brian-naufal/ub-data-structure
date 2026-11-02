
package src;

public class Tree {
    private Node root;
    private int size;

    public Tree() {
        root = null;
        size = 0;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public Node getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public boolean contains(int data) {
        Node cur = root;

        while (cur != null) {
            if (data == cur.data) {
                return true;
            }
            cur = data < cur.data ? cur.left : cur.right;
        }

        return false;
    }

    // Insert
    public void insertNode(int data) {
        if (contains(data))
            return;

        if (isEmpty()) {
            root = new Node(data);
        } else {
            root.insertData(data);
        }

        size++;
    }

    // Delete
    public void deleteNode(int data) {
        if (!contains(data))
            return;

        root = delete(root, data);
        size--;
    }

    private Node delete(Node node, int data) {
        if (node == null)
            return null;

        if (data < node.data) {
            node.left = delete(node.left, data);
        } else if (data > node.data) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            Node min = node.right;

            while (min.left != null) {
                min = min.left;
            }

            node.data = min.data;
            node.right = delete(node.right, min.data);
        }

        return node;
    }

    // Traversal
    private String preOrder(Node node) {
        if (node == null)
            return "";

        return node.data + " " +
                preOrder(node.left) +
                preOrder(node.right);
    }

    private String inOrder(Node node) {
        if (node == null)
            return "";

        return inOrder(node.left) +
                node.data + " " +
                inOrder(node.right);
    }

    private String postOrder(Node node) {
        if (node == null)
            return "";

        return postOrder(node.left) +
                postOrder(node.right) +
                node.data + " ";
    }

    public String preOrderTravel() {
        return preOrder(root);
    }

    public String inOrderTravel() {
        return inOrder(root);
    }

    public String postOrderTravel() {
        return postOrder(root);
    }
}
