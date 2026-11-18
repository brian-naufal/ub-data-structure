public class Graph {
    private class Node {
        private int data;
        private Node next;

        public Node(int dt, Node n) {
            data = dt;
            next = n;
        }

        public int getDt() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node[] node;
    private int jNode;

    public Graph(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj) {
        Node n = new Node(adj, node[head]);
        node[head] = n;
    }

    public void cetak(String komentar) {
        System.out.println(komentar);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print("->" + n.getDt());
                n = n.getNext();
            }
            System.out.println();
        }
    }

    // keluar
    public int edgeKeluar(int i) {
        int sum = 0;
        Node n = node[i];
        while (n != null) {
            sum++;
            n = n.getNext();
        }
        return sum;
    }

    // masuk
    public int edgeMasuk(int m) {
        int sum = 0;
        for (int i = 0; i < jNode; i++) {
            Node n = node[i];
            while (n != null) {
                if (n.getDt() == m)
                    sum++;
                n = n.getNext();
            }
        }
        return sum;
    }

    // jumlah
    public int jumlahTetangga(int v) {
        int sum = 0;
        Node n = node[v];
        while (n != null) {
            boolean exist = false;
            Node m = node[v];
            while (m != n) {
                if (m.getDt() == n.getDt())
                    exist = true;
                m = m.getNext();
            }
            if (!exist && n.getDt() != v)
                sum++;
            n = n.getNext();
        }
        return sum;
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);
        g.addAdj(0, 3);
        g.addAdj(0, 1);
        g.addAdj(1, 4);
        g.addAdj(1, 2);
        g.addAdj(2, 4);
        g.addAdj(2, 1);
        g.addAdj(4, 3);
        g.cetak("Kondisi awal");

        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println("Node " + i);
            System.out.println("  Edge masuk   : " + g.edgeMasuk(i));
            System.out.println("  Edge keluar  : " + g.edgeKeluar(i));
            System.out.println("  Jumlah tetangga : " + g.jumlahTetangga(i));
        }
    }
}