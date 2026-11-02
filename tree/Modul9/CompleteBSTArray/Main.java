import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(10);
        int data;
        Random randomNumber = new Random();

        System.out.println("\nSisip nilai data berikut: ");

        for (int i = 1; i <= 10; i++) {
            data = randomNumber.nextInt(100);
            System.out.printf("%d ", data);
            tree.insertNode(data);
        }

        System.out.println("\n\nPreOrder traversal:");
        tree.preOrderTravel();

        System.out.println("\n\nInOrder traversal:");
        tree.inOrderTravel();

        System.out.println("\n\nPostOrder traversal:");
        tree.postOrderTravel();

        System.out.println("\n\nBanyak Node: ");
        System.out.print(tree.size());

        System.out.println("\n\nTotal leaf: ");
        System.out.print(tree.leafTotal());

        System.out.println("\n\nTinggi tree: ");
        System.out.print(tree.height());

        System.out.println();
        System.out.println();
    }
}