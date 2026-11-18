class StackInt {
    int[] arr;
    int count = 0;

    StackInt(int size) {
        arr = new int[size];
    }

    void push(int item) {
        arr[count++] = item;
    }

    int pop() {
        return arr[--count];
    }

    boolean isEmpty() {
        return count == 0;
    }
}

class QueueInt {
    int[] arr;
    int rear = 0;

    QueueInt(int size) {
        arr = new int[size];
    }

    void enqueue(int item) {
        for (int i = rear; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = item;
        rear++;
    }

    int dequeue() {
        return arr[--rear];
    }

    boolean isEmpty() {
        return rear == 0;
    }
}

public class GraphTraversal {
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

    public GraphTraversal(int n) {
        jNode = n;
        node = new Node[jNode];
    }

    public void addAdj(int head, int adj) {
        Node n = new Node(adj, node[head]);
        node[head] = n;
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[jNode];
        QueueInt q = new QueueInt(jNode);

        visited[start] = true;
        q.enqueue(start);

        System.out.print("BFS : ");
        while (!q.isEmpty()) {
            int curr = q.dequeue();
            System.out.print(curr + " ");

            Node temp = node[curr];
            while (temp != null) {
                int tetangga = temp.getDt();
                if (!visited[tetangga]) {
                    visited[tetangga] = true;
                    q.enqueue(tetangga);
                }
                temp = temp.getNext();
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[jNode];
        StackInt s = new StackInt(jNode * jNode);

        s.push(start);

        System.out.print("DFS : ");
        while (!s.isEmpty()) {
            int curr = s.pop();

            if (!visited[curr]) {
                visited[curr] = true;
                System.out.print(curr + " ");

                Node temp = node[curr];
                while (temp != null) {
                    int tetangga = temp.getDt();
                    if (!visited[tetangga]) {
                        s.push(tetangga);
                    }
                    temp = temp.getNext();
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphTraversal g = new GraphTraversal(5);
        g.addAdj(0, 3);
        g.addAdj(0, 1);
        g.addAdj(1, 4);
        g.addAdj(1, 2);
        g.addAdj(2, 4);
        g.addAdj(2, 1);
        g.addAdj(4, 3);

        g.bfs(0);
        g.dfs(0);
    }
}