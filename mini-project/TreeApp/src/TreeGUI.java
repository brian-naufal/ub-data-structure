package src;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TreeGUI extends JFrame {
    private Tree tree = new Tree();
    private TreePanel treePanel = new TreePanel();
    private JTextField inputField = new JTextField(5);
    private JLabel infoLabel = new JLabel(" ");
    private JLabel resultLabel = new JLabel(" ");

    public TreeGUI() {
        super("AVL Tree");

        JButton addButton = new JButton("Tambah");
        JButton searchButton = new JButton("Cari");
        JButton deleteButton = new JButton("Hapus");
        JButton randomButton = new JButton("Acak 10");
        JButton clearButton = new JButton("Reset");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nilai:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);
        inputPanel.add(deleteButton);
        inputPanel.add(randomButton);
        inputPanel.add(clearButton);

        JButton preButton = new JButton("PreOrder");
        JButton inButton = new JButton("InOrder");
        JButton postButton = new JButton("PostOrder");

        JPanel travelPanel = new JPanel();
        travelPanel.add(preButton);
        travelPanel.add(inButton);
        travelPanel.add(postButton);

        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.add(travelPanel);
        bottomPanel.add(resultLabel);
        bottomPanel.add(infoLabel);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addButton.addActionListener(e -> addValue());
        inputField.addActionListener(e -> addValue());
        searchButton.addActionListener(e -> searchValue());
        deleteButton.addActionListener(e -> deleteValue());
        randomButton.addActionListener(e -> addRandom());
        clearButton.addActionListener(e -> {
            tree.clear();
            resultLabel.setText(" ");
            refresh();
        });
        preButton.addActionListener(e -> resultLabel.setText("PreOrder: " + tree.preOrderTravel()));
        inButton.addActionListener(e -> resultLabel.setText("InOrder: " + tree.inOrderTravel()));
        postButton.addActionListener(e -> resultLabel.setText("PostOrder: " + tree.postOrderTravel()));

        add(inputPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 500);
        setLocationRelativeTo(null);
        refresh();
    }

    // Membaca angka dari input, return null jika tidak valid
    private Integer readInput() {
        try {
            return Integer.parseInt(inputField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan bilangan bulat yang valid.");
            return null;
        } finally {
            inputField.setText("");
            inputField.requestFocus();
        }
    }

    private void addValue() {
        Integer value = readInput();
        if (value == null)
            return;

        if (!tree.insertNode(value)) {
            JOptionPane.showMessageDialog(this, "Nilai " + value + " sudah ada di tree.");
        }
        refresh();
    }

    private void searchValue() {
        Integer value = readInput();
        if (value == null)
            return;

        refresh();
        if (tree.search(value)) {
            treePanel.highlight = value;
            treePanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Nilai " + value + " tidak ditemukan.");
        }
    }

    private void deleteValue() {
        Integer value = readInput();
        if (value == null)
            return;

        if (!tree.deleteData(value)) {
            JOptionPane.showMessageDialog(this, "Nilai " + value + " tidak ditemukan.");
        }
        refresh();
    }

    private void addRandom() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            tree.insertNode(random.nextInt(100));
        }
        refresh();
    }

    private void refresh() {
        String min = tree.min() == null ? "-" : String.valueOf(tree.min());
        String max = tree.max() == null ? "-" : String.valueOf(tree.max());

        infoLabel.setText("Node: " + tree.size() + " | Leaf: " + tree.leafTotal()
                + " | Tinggi: " + tree.height() + " | Min: " + min + " | Max: " + max);
        treePanel.highlight = null;
        treePanel.repaint();
    }

    // Panel untuk menggambar tree
    private class TreePanel extends JPanel {
        Integer highlight = null;

        public TreePanel() {
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            drawNode(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }

        private void drawNode(Graphics g, Node node, int x, int y, int offset) {
            if (node == null)
                return;

            int nextOffset = Math.max(offset / 2, 15);

            if (node.left != null) {
                g.drawLine(x, y, x - offset, y + 50);
                drawNode(g, node.left, x - offset, y + 50, nextOffset);
            }
            if (node.right != null) {
                g.drawLine(x, y, x + offset, y + 50);
                drawNode(g, node.right, x + offset, y + 50, nextOffset);
            }

            // node hasil pencarian diberi warna kuning
            g.setColor(highlight != null && highlight == node.data ? Color.YELLOW : Color.WHITE);
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.BLACK);
            g.drawOval(x - 15, y - 15, 30, 30);

            String text = String.valueOf(node.data);
            FontMetrics fm = g.getFontMetrics();
            g.drawString(text, x - fm.stringWidth(text) / 2, y + fm.getAscent() / 2 - 1);

            // balance factor ditampilkan kecil di samping node
            Font font = g.getFont();
            g.setFont(font.deriveFont(10f));
            g.setColor(Color.GRAY);
            g.drawString("bf=" + tree.balanceFactor(node), x + 17, y - 10);
            g.setFont(font);
        }
    }
}