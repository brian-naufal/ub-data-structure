public class GraphShort {
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

    public GraphShort(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj, int rnge) {
        Node n = new Node(adj, rnge, node[head]);
        node[head] = n;
    }

    public void jarakTerpendek(int src, int dst) {
        int[] dist = new int[jNode];
        boolean[] visited = new boolean[jNode];

        for (int i = 0; i < jNode; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[src] = 0;

        for (int i = 0; i < jNode - 1; i++) {
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < jNode; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    u = j;
                }
            }

            if (u == -1)
                break;
            visited[u] = true;

            Node temp = node[u];
            while (temp != null) {
                int v = temp.getDt();
                int bobot = temp.getJarak();
                if (!visited[v] && dist[u] + bobot < dist[v]) {
                    dist[v] = dist[u] + bobot;
                }
                temp = temp.getNext();
            }
        }

        if (dist[dst] == Integer.MAX_VALUE) {
            System.out.println("Tidak ada jalur dari " + src + " ke " + dst);
        } else {
            System.out.println("Jarak terpendek dari node " + src + " ke node " + dst + " adalah: " + dist[dst]);
        }
    }

    public static void main(String[] args) {
        GraphShort g = new GraphShort(5);
        g.addAdj(0, 3, 10);
        g.addAdj(0, 1, 2);
        g.addAdj(1, 4, 5);
        g.addAdj(1, 2, 3);
        g.addAdj(2, 4, 1);
        g.addAdj(2, 1, 3);
        g.addAdj(4, 3, 2);

        g.jarakTerpendek(0, 3);
    }
}