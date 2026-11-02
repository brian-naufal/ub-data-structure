package src;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TreeGUI extends JFrame {
    private Tree tree = new Tree();
    private JTextField input = new JTextField(5);
    private JLabel output = new JLabel(" ", SwingConstants.CENTER);
    private JPanel canvas;

    public TreeGUI() {
        setTitle("Tree");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawNode(g, tree.getRoot(), getWidth() / 2, 40, getWidth() / 4);
            }
        };
        canvas.setBackground(Color.WHITE);

        JButton insertBtn = new JButton("Insert");
        JButton deleteBtn = new JButton("Delete");
        JButton randomBtn = new JButton("Random 10");
        JButton inBtn = new JButton("Inorder");
        JButton preBtn = new JButton("Preorder");
        JButton postBtn = new JButton("Postorder");
        JButton resetBtn = new JButton("Reset");

        insertBtn.addActionListener(e -> insert());
        input.addActionListener(e -> insert());
        deleteBtn.addActionListener(e -> delete());
        randomBtn.addActionListener(e -> {
            tree = new Tree();
            Random rand = new Random();
            while (tree.size() < 10) {
                tree.insertNode(rand.nextInt(100));
            }
            update();
        });
        inBtn.addActionListener(e -> output.setText("Inorder: " + tree.inOrderTravel()));
        preBtn.addActionListener(e -> output.setText("Preorder: " + tree.preOrderTravel()));
        postBtn.addActionListener(e -> output.setText("Postorder: " + tree.postOrderTravel()));
        resetBtn.addActionListener(e -> {
            tree = new Tree();
            update();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Value:"));
        top.add(input);
        top.add(insertBtn);
        top.add(deleteBtn);
        top.add(randomBtn);
        top.add(resetBtn);

        JPanel traversal = new JPanel();
        traversal.add(inBtn);
        traversal.add(preBtn);
        traversal.add(postBtn);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.add(traversal);
        bottom.add(output);

        add(top, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        update();
    }

    private void insert() {
        try {
            tree.insertNode(Integer.parseInt(input.getText().trim()));
            input.setText("");
            update();
        } catch (NumberFormatException e) {
            output.setText("Enter a valid number");
        }
    }

    private void delete() {
        try {
            tree.deleteNode(Integer.parseInt(input.getText().trim()));
            input.setText("");
            update();
        } catch (NumberFormatException e) {
            output.setText("Enter a valid number");
        }
    }

    private void update() {
        output.setText(" ");
        canvas.repaint();
    }

    private void drawNode(Graphics g, Node node, int x, int y, int gap) {
        if (node == null)
            return;

        g.setColor(Color.GRAY);
        if (node.left != null) {
            g.drawLine(x, y, x - gap, y + 60);
            drawNode(g, node.left, x - gap, y + 60, gap / 2);
        }
        if (node.right != null) {
            g.drawLine(x, y, x + gap, y + 60);
            drawNode(g, node.right, x + gap, y + 60, gap / 2);
        }

        String text = String.valueOf(node.data);
        g.setColor(new Color(100, 170, 255));
        g.fillOval(x - 16, y - 16, 32, 32);
        g.setColor(Color.BLACK);
        g.drawString(text, x - 4 * text.length(), y + 5);
    }
}