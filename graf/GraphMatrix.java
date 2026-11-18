public class GraphMatrix {
    private int[][] matrix;
    private int jNode;

    public GraphMatrix(int n) {
        jNode = n;
        matrix = new int[jNode][jNode];
    }

    public void addEdge(int src, int dst) {
        matrix[src][dst] = 1;
    }

    public void cetak(String s) {
        System.out.println(s);
        for (int i = 0; i < jNode; i++) {
            System.out.print("[" + i + "] ");
            for (int j = 0; j < jNode; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMatrix g = new GraphMatrix(5);
        g.addEdge(0, 3);
        g.addEdge(0, 1);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(2, 1);
        g.addEdge(4, 3);
        g.cetak("Kondisi awal Adjacency Matrix");
    }
}