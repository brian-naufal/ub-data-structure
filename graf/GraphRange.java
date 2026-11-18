public class GraphRange {
    private class Node {
        private int data;
        private int jarak;
        private Node next;

        public Node(int dt, int rnge, Node n) {
            data = dt;
            jarak = rnge;
            next = n;
        }

        public int getDt() {
            return data;
        }

        public int getJarak() {
            return jarak;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node[] node;
    private int jNode;

    public GraphRange(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj, int jarak) {
        Node n = new Node(adj, jarak, node[head]);
        node[head] = n;
    }

    public void cetak(String s) {
        System.out.println(s);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "]");
            Node n = node[i];
            while (n != null) {
                System.out.print("->" + n.getDt() + "(" + n.getJarak() + ")");
                n = n.getNext();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphRange g = new GraphRange(5);
        g.addAdj(0, 3, 10);
        g.addAdj(0, 1, 2);
        g.addAdj(1, 4, 5);
        g.addAdj(1, 2, 3);
        g.addAdj(2, 4, 1);
        g.addAdj(2, 1, 3);
        g.addAdj(4, 3, 2);
        g.cetak("Kondisi awal dengan jarak");
    }
}