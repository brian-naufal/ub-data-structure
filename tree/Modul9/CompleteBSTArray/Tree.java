public class Tree {
    private int[] data;
    private int size;

    public Tree(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public void insertNode(int value) {
        if (size == data.length) {
            System.out.println("Tree penuh");
            return;
        }
        data[size] = value;
        size++;
    }

    public int size() {
        return size;
    }

    private int countLeaves(int index) {
        if (index >= size) {
            return 0;
        }

        if (2 * index + 1 >= size)
            return 1;

        return countLeaves(2 * index + 1) + countLeaves(2 * index + 2);
    }

    public int leafTotal() {
        return countLeaves(0);
    }

    private int countHeight(int index) {
        if (index >= size) {
            return 0;
        }

        int leftHeight = countHeight(2 * index + 1);
        int rightHeight = countHeight(2 * index + 2);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int height() {
        return countHeight(0);
    }

    private void preOrder(int index) {
        if (index >= size)
            return;

        System.out.printf("%d ", data[index]);
        preOrder(2 * index + 1);
        preOrder(2 * index + 2);
    }

    private void inOrder(int index) {
        if (index >= size)
            return;

        inOrder(2 * index + 1);
        System.out.printf("%d ", data[index]);
        inOrder(2 * index + 2);
    }

    private void postOrder(int index) {
        if (index >= size)
            return;

        postOrder(2 * index + 1);
        postOrder(2 * index + 2);
        System.out.printf("%d ", data[index]);
    }

    public void preOrderTravel() {
        preOrder(0);
    }

    public void inOrderTravel() {
        inOrder(0);
    }

    public void postOrderTravel() {
        postOrder(0);
    }
}