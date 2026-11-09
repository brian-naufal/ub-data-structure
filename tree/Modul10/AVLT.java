class Node {
    int data;
    int tinggi; // tinggi node
    Node pKiri;
    Node pKanan;
    Node pInduk; // pointer ke induk

    // constructor node
    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

public class AVLT {
    private Node root;

    public AVLT() {
        root = null;
    }

    // cari dt di tree, mengembalikan true jika ditemukan
    // dan false jika tidak
    public boolean cariDt(int dt) {
        Node temp = root;

        while (temp != null) {
            if (dt == temp.data)
                return true;
            // cariDt subtree pKiri
            else if (dt < temp.data)
                temp = temp.pKiri;
            // cariDt subtree pKanan
            else
                temp = temp.pKanan;
        }
        // dt tidak ditemukan
        return false;
    }

    // sisip dt ke dalam tree, returns true if berhasil,
    // false jika gagal
    // tree diseimbangkan menggunakan algoritma AVL
    public boolean sisipDt(int dt) {
        if (root == null) {
            // sisip dt di root
            root = new Node(dt, 1, null, null, null);
            return true;
        }
        // tree tidak kosong
        else {
            // mulai dari root
            Node temp = root;
            Node prev = null;

            // cari lokasi penyisipan dt
            while (temp != null) {
                if (dt == temp.data)
                    return false;
                // sisip dt di subtree pKiri
                else if (dt < temp.data) {
                    prev = temp;
                    temp = temp.pKiri;
                }
                // sisip dt di subtree pKanan
                else {
                    prev = temp;
                    temp = temp.pKanan;
                }
            }
            // buat node baru
            temp = new Node(dt, 1, null, null, prev);

            if (dt < prev.data)
                prev.pKiri = temp; // sisip di pKiri
            else
                prev.pKanan = temp; // sisip di pKanan

            // mulai dari node yang disisipkan dan
            // bergerak menuju root
            while (temp != null) {
                temp = seimbang(temp);
                temp = temp.pInduk;
            }
            // penyisipan berhasil
            return true;
        }
    }

    // 10.10

    // cari node yang berisi dt, mengembalikan null jika tidak ada
    private Node cariNode(int dt) {
        Node temp = root;

        while (temp != null) {
            if (dt == temp.data)
                return temp;
            else if (dt < temp.data)
                temp = temp.pKiri;
            else
                temp = temp.pKanan;
        }
        return null;
    }

    // hapus dt dari tree, returns true jika berhasil,
    // false jika dt tidak ditemukan
    // tree diseimbangkan kembali menggunakan algoritma AVL
    public boolean hapusDt(int dt) {
        Node n = cariNode(dt);
        if (n == null)
            return false;

        // kondisi 3: node mempunyai 2 anak
        // data diganti dengan successor (node terkecil di subtree kanan),
        // lalu yang dihapus adalah node successor
        // (successor pasti punya paling banyak 1 anak)
        if (n.pKiri != null && n.pKanan != null) {
            Node s = n.pKanan;
            while (s.pKiri != null)
                s = s.pKiri;
            n.data = s.data;
            n = s;
        }

        // kondisi 1: node daun -> child = null
        // kondisi 2: node punya satu anak -> child = anak tersebut
        Node child = (n.pKiri != null) ? n.pKiri : n.pKanan;
        Node parent = n.pInduk;

        // sambungkan anak ke induk node yang dihapus
        if (child != null)
            child.pInduk = parent;

        if (parent == null)
            root = child;
        else if (parent.pKiri == n)
            parent.pKiri = child;
        else
            parent.pKanan = child;

        // seimbangkan tree mulai dari induk node yang dihapus menuju root
        Node temp = parent;
        while (temp != null) {
            temp = seimbang(temp);
            temp = temp.pInduk;
        }
        return true;
    }

    public int tinggi() {
        return tinggi(root);
    }

    private int tinggi(Node node) {
        if (node == null)
            return 0;
        else
            return node.tinggi;
    }

    // hitung node-node dari tree
    public int jumlahNode() {
        return jumlahNode(root);
    }

    public void inOrderTraversal() {
        inOrder(root);
    }

    private void inOrder(Node r) {
        if (r == null)
            return;
        inOrder(r.pKiri);
        System.out.printf("-%d", r.data);
        inOrder(r.pKanan);
    }

    public void preOrderTraversal() {
        preOrder(root);
    }

    private void preOrder(Node r) {
        if (r == null)
            return;
        System.out.printf("-%d", r.data);
        preOrder(r.pKiri);
        preOrder(r.pKanan);
    }

    // hitung node-node dari tree
    private int jumlahNode(Node node) {
        if (node == null)
            return 0;
        else
            return 1 + jumlahNode(node.pKiri)
                    + jumlahNode(node.pKanan);
    }

    // 10.9

    private Node seimbang(Node n) {
        if (Math.abs(tinggi(n.pKiri) - tinggi(n.pKanan)) <= 1) {
            hitungTinggi(n);
            return n;
        } else if (tinggi(n.pKiri) - tinggi(n.pKanan) >= 2 &&
                tinggi(n.pKiri.pKiri) >= tinggi(n.pKiri.pKanan)) {
            return putarKanan(n);
        } else if (tinggi(n.pKanan) - tinggi(n.pKiri) >= 2 &&
                tinggi(n.pKanan.pKanan) >= tinggi(n.pKanan.pKiri)) {
            return putarKiri(n);
        } else if (tinggi(n.pKiri) - tinggi(n.pKanan) >= 2 &&
                tinggi(n.pKiri.pKanan) >= tinggi(n.pKiri.pKiri)) {
            return putarKiriKanan(n);
        } else {
            return putarKananKiri(n);
        }
    }

    private Node putarKiri(Node n) {
        Node parent = n.pInduk;
        Node pKanan = n.pKanan;

        n.pKanan = pKanan.pKiri;
        if (n.pKanan != null)
            n.pKanan.pInduk = n;

        pKanan.pKiri = n;
        n.pInduk = pKanan;

        pKanan.pInduk = parent;
        if (parent == null)
            root = pKanan;
        else if (parent.pKanan == n)
            parent.pKanan = pKanan;
        else
            parent.pKiri = pKanan;

        hitungTinggi(n);
        hitungTinggi(pKanan);
        return pKanan;
    }

    private Node putarKanan(Node n) {
        Node parent = n.pInduk;
        Node pKiri = n.pKiri;

        n.pKiri = pKiri.pKanan;
        if (n.pKiri != null)
            n.pKiri.pInduk = n;

        pKiri.pKanan = n;
        n.pInduk = pKiri;

        pKiri.pInduk = parent;
        if (parent == null)
            root = pKiri;
        else if (parent.pKiri == n)
            parent.pKiri = pKiri;
        else
            parent.pKanan = pKiri;

        hitungTinggi(n);
        hitungTinggi(pKiri);
        return pKiri;
    }

    private Node putarKiriKanan(Node n) {
        putarKiri(n.pKiri);
        return putarKanan(n);
    }

    private Node putarKananKiri(Node n) {
        putarKanan(n.pKanan);
        return putarKiri(n);
    }

    private void hitungTinggi(Node node) {
        node.tinggi = Math.max(tinggi(node.pKiri), tinggi(node.pKanan)) + 1;
    }

    private void cetak(String judul) {
        System.out.println(judul);
        System.out.print("  inOrder   : ");
        inOrderTraversal();
        System.out.println();
        System.out.print("  preOrder  : ");
        preOrderTraversal();
        System.out.println();
        System.out.println("  tinggi    : " + tinggi());
        System.out.println("  jumlahNode: " + jumlahNode());
        System.out.println();
    }

    public static void main(String[] args) {
        // Kasus 1: kiri-kiri -> putarKanan
        AVLT t1 = new AVLT();
        t1.sisipDt(30);
        t1.sisipDt(20);
        t1.sisipDt(10);
        t1.cetak("Kasus 1 (putarKanan): sisip 30, 20, 10");

        // Kasus 2: kanan-kanan -> putarKiri
        AVLT t2 = new AVLT();
        t2.sisipDt(10);
        t2.sisipDt(20);
        t2.sisipDt(30);
        t2.cetak("Kasus 2 (putarKiri): sisip 10, 20, 30");

        // Kasus 3: kiri-kanan -> putarKiriKanan
        AVLT t3 = new AVLT();
        t3.sisipDt(30);
        t3.sisipDt(10);
        t3.sisipDt(20);
        t3.cetak("Kasus 3 (putarKiriKanan): sisip 30, 10, 20");

        // Kasus 4: kanan-kiri -> putarKananKiri
        AVLT t4 = new AVLT();
        t4.sisipDt(10);
        t4.sisipDt(30);
        t4.sisipDt(20);
        t4.cetak("Kasus 4 (putarKananKiri): sisip 10, 30, 20");

        // Data Program Latihan Praktikum 10.1, berturut-turut
        AVLT t = new AVLT();
        int[] data = { 3, 4, 6, 5, 15, 10, 20, 17, 25 };
        for (int d : data) {
            t.sisipDt(d);
            System.out.print("sisip " + d + " -> ");
            t.inOrderTraversal();
            System.out.println();
        }
        System.out.println();
        t.cetak("Hasil");

        // ===== 10.10: pengujian hapus =====
        System.out.println("Hapus: ");
        int[] hapus = { 25, 20, 6, 3, 4, 5 };
        for (int d : hapus) {
            t.hapusDt(d);
            t.cetak("hapus " + d);
        }
        System.out.println("hapus 99 (tidak ada): " + t.hapusDt(99));
    }
}