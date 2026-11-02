package LinkedList;
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

    public void insertNode(int data) {
        if (isEmpty()) {
            root = new Node(data);
        } else {
            root.insertData(data);
        }
        size++;
    }

    public int size() {
        return size;
    }

    private int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null)
            return 1;

        return countLeaves(node.left) + countLeaves(node.right);
    }

    public int leafTotal() {
        return countLeaves(root);
    }

    private int countHeight(Node node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = countHeight(node.left);
        int rightHeight = countHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int height() {
        return countHeight(root);
    } 

    public int level(Tree tree) {
        if (tree == null) {
            return 0;
        }
        return countHeight(tree.root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;

        System.out.printf("%d ", node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.printf("%d ", node.data);
        inOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.printf("%d ", node.data);
    }

    public void preOrderTravel() {
        preOrder(root);
    }

    public void inOrderTravel() {
        inOrder(root);
    }

    public void postOrderTravel() {
        postOrder(root);
    }
}
