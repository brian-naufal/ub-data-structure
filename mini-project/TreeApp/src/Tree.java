package src;
public class Tree {
    private Node root;
    private int size;

    public Tree() {
        root = null;
        size = 0;
    }

    public Node getRoot() {
        return root;
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;

        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    // balance factor = tinggi kiri - tinggi kanan
    public int balanceFactor(Node node) {
        if (node == null)
            return 0;

        return getHeight(node.left) - getHeight(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // Menyeimbangkan node, return node yang menjadi root subtree setelahnya
    private Node balance(Node node) {
        updateHeight(node);
        int bf = balanceFactor(node);

        if (bf > 1) {
            if (balanceFactor(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (bf < -1) {
            if (balanceFactor(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private Node insertData(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.left = insertData(node.left, data);
        else if (data > node.data)
            node.right = insertData(node.right, data);

        return balance(node);
    }

    // return false jika data sudah ada
    public boolean insertNode(int data) {
        if (search(data))
            return false;

        root = insertData(root, data);
        size++;
        return true;
    }

    private boolean searchNode(Node node, int data) {
        if (node == null)
            return false;

        if (data < node.data)
            return searchNode(node.left, data);
        else if (data > node.data)
            return searchNode(node.right, data);

        return true;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // return null jika tree kosong
    public Integer min() {
        return root == null ? null : findMin(root).data;
    }

    public Integer max() {
        return root == null ? null : findMax(root).data;
    }

    private Node deleteNode(Node node, int data) {
        if (node == null)
            return null;

        if (data < node.data) {
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            node.data = findMin(node.right).data;
            node.right = deleteNode(node.right, node.data);
        }
        return balance(node);
    }

    // return false jika data tidak ditemukan
    public boolean deleteData(int data) {
        if (!search(data))
            return false;

        root = deleteNode(root, data);
        size--;
        return true;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int leafTotal() {
        return countLeaves(root);
    }

    public int height() {
        return getHeight(root);
    }

    private void preOrder(Node node, StringBuilder sb) {
        if (node == null)
            return;

        sb.append(node.data).append(" ");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    private void inOrder(Node node, StringBuilder sb) {
        if (node == null)
            return;

        inOrder(node.left, sb);
        sb.append(node.data).append(" ");
        inOrder(node.right, sb);
    }

    private void postOrder(Node node, StringBuilder sb) {
        if (node == null)
            return;

        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.data).append(" ");
    }

    public String preOrderTravel() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    public String inOrderTravel() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }

    public String postOrderTravel() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString();
    }
}