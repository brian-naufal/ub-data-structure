import java.util.Scanner;

class Node {
    int data;
    int tinggi;
    Node pKiri;
    Node pKanan;
    Node pInduk;

    public Node(int dt, int tg, Node pKi, Node pKa, Node pI) {
        this.data = dt;
        this.tinggi = tg;
        this.pKiri = pKi;
        this.pKanan = pKa;
        this.pInduk = pI;
    }
}

class AVLT {
    Node root;

    public AVLT() {
        root = null;
    }

    // ============================================================
    // METHOD PENDUKUNG (SUDAH LENGKAP)
    // ============================================================

    int tinggi(Node node) {
        if (node == null) return 0;
        return node.tinggi;
    }

    int tinggi() {
        return tinggi(root);
    }

    void perbaruiTinggi(Node node) {
        node.tinggi = Math.max(tinggi(node.pKiri), tinggi(node.pKanan)) + 1;
    }

    void inOrder(Node node, StringBuilder sb) {
        if (node == null) return;
        inOrder(node.pKiri, sb);
        sb.append(node.data).append(" ");
        inOrder(node.pKanan, sb);
    }

    void preOrder(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.data).append(" ");
        preOrder(node.pKiri, sb);
        preOrder(node.pKanan, sb);
    }

    // ============================================================
    // METHODSSS KERJAIN SMANGATT
    // ============================================================

    // TODO: Cari dt dari root menggunakan perulangan. Kembalikan true jika ditemukan dan false jika tidak.
    boolean cariDt(int dt) {
        // here 
        Node n = root;
        if (n == null) {
            return false;
        }
        
        
        while (n != null) {
            if (dt == n.data) {
                return true;
            } else if (dt > n.data) {
                n = n.pKanan;
            } else {
                n = n.pKiri;
            }           
        }
        return false;
    }

    // TODO: Lakukan rotasi kanan pada node. Perbarui pointer, root, dan tinggi node.
    Node putarKanan(Node node) {
        // here
        if (node == null) {
            return null;
        }
        if (node.pKiri == null) {
            return null;
        }
        
        Node left = node.pKiri; 
        Node mid = left.pKanan;
        Node up = node.pInduk;
        
        if (up == null) {
            root = left;
        } else if (up.pKiri == node) {
            up.pKiri = left;
        } else {
            up.pKanan = left;
        }
        
        Node top = null;
        top = left;
        top.pKanan = node;
        node.pInduk = top;
        top.pInduk = up;
        
        node.pKiri = mid;
        if (mid != null) {
            mid.pInduk = node;
        }
        
        perbaruiTinggi(node);
        perbaruiTinggi(top);
        
        return top;
    }

    // TODO: Lakukan rotasi kiri pada node. Perbarui pointer, root, dan tinggi node.
    Node putarKiri(Node node) {
        // here
        if (node == null) {
            return null;
        }
        if (node.pKanan == null) {
            return null;
        }
        
        Node right = node.pKanan;
        Node mid = right.pKiri;
        Node up = node.pInduk;
        
        if (up == null) {
            root = right;
        } else if (up.pKanan == node) {
            up.pKanan = right; 
        } else {
            up.pKiri = right;
        }
        
        Node top = null;
        top = right;
        top.pKiri = node;
        node.pInduk = top;
        top.pInduk = up;
        
        node.pKanan = mid;
        if (mid != null) {
            mid.pInduk = node;
        }
        
        perbaruiTinggi(node);
        perbaruiTinggi(top);
        return top;
    }

    // TODO: Lakukan rotasi kiri-kanan dengan memanggil putarKiri pada anak kiri, lalu putarKanan pada node.
    Node putarKiriKanan(Node node) {
        // here
        node.pKiri = putarKiri(node.pKiri);
        return putarKanan(node);
    }

    // TODO: Lakukan rotasi kanan-kiri dengan memanggil putarKanan pada anak kanan, lalu putarKiri pada node.
    Node putarKananKiri(Node node) {
        // here
        node.pKanan = putarKanan(node.pKanan);
        return putarKiri(node);
    }   

    // TODO: Perbarui tinggi node, periksa keseimbangannya, lalu lakukan rotasi yang sesuai jika diperlukan.
    Node seimbangkan(Node node) {
        // here
        if (node == null) {
            return null;
        }
        
        perbaruiTinggi(node);
        int selisih = tinggi(node.pKiri) - tinggi(node.pKanan);
        
        if (selisih > 1) {
            if (tinggi(node.pKiri.pKiri) >= tinggi(node.pKiri.pKanan)) {
                return putarKanan(node);
            } else {
                return putarKiriKanan(node);
            }
        }
        if (selisih < -1) {
            if (tinggi(node.pKanan.pKanan) >= tinggi(node.pKanan.pKiri)) {
                return putarKiri(node);
            } else {
                return putarKananKiri(node);
            }
        }
        return node;
    }

    // TODO: Sisipkan dt sesuai aturan BST. Tolak data duplikat dan seimbangkan pohon hingga root.
    boolean sisipDt(int dt) {
        // here
        if (root == null) {
            root = new Node(dt, 1, null, null, null);
            return true;
        }
        
        Node n = root;
        Node induk = null;
        while (n != null) {
            induk = n;
            if (dt == n.data) {
                return false;
            } else if (dt < n.data) {
                n = n.pKiri;
            } else {
                n = n.pKanan;
            }
        }
        
        Node baru = new Node(dt, 1, null, null, induk);
        if (dt > induk.data) {
            induk.pKanan = baru;
        } else {
            induk.pKiri = baru;
        }
        
        n = induk;
        while (n != null) {
            Node up = n.pInduk;
            seimbangkan(n);
            n = up;
        }
        
        return true;
    }

    // TODO: Hitung jumlah node secara rekursif. Node null bernilai 0.
    int jumlahNode(Node node) {
        // here
        if (node == null) {
            return 0;
        }

        return 1 + jumlahNode(node.pKiri) + jumlahNode(node.pKanan);
    }

    // TODO: Hapus dt dengan menangani kondisi daun, satu anak, atau dua anak. Gunakan inorder successor untuk dua anak, perbarui pointer, lalu seimbangkan pohon hingga root.
    boolean hapusDt(int dt) {
        // here
        Node n = root;
        while (n != null && n.data != dt) {
            if (dt < n.data) {
                n = n.pKiri;
            } else {
                n = n.pKanan;
            }
        }
        if (n == null) {
            return false;
        }
        
        Node mulai;
        
        if (n.pKiri == null || n.pKanan == null) {
            Node anak = (n.pKiri != null) ? n.pKiri : n.pKanan;
            Node up = n.pInduk;
            
            if (up == null) {
                root = anak;
            } else if (up.pKiri == n) {
                up.pKiri = anak;
            } else {
                up.pKanan = anak;
            }
            if (anak != null) {
                anak.pInduk = up;
            }
            mulai = up;
        } else {
            Node suc = n.pKanan;
            while (suc.pKiri != null) {
                suc = suc.pKiri;
            }
            n.data = suc.data;
            
            Node anak = suc.pKanan;
            Node up = suc.pInduk;
            if (up.pKiri == suc) {
                up.pKiri = anak;
            } else {
                up.pKanan = anak;
            }
            if (anak != null) {
                anak.pInduk = up;
            }
            mulai = up;
        }
        
        while (mulai != null) {
            Node up = mulai.pInduk;
            seimbangkan(mulai);
            mulai = up;
        }
        
        return true;
    }
}

// =============================================================
// MAIN PROGRAM
// udh lengkap yh
// =============================================================
public class Main {
    static void cetakTraversal(StringBuilder sb) {
        String hasil = sb.toString().trim();
        System.out.println(hasil.isEmpty() ? "KOSONG" : hasil);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = Integer.parseInt(sc.nextLine().trim());
        AVLT tree = new AVLT();

        for (int i = 0; i < q; i++) {
            String[] parts = sc.nextLine().trim().split("\\s+");
            switch (parts[0]) {
                case "SISIP": {
                    int dt = Integer.parseInt(parts[1]);
                    System.out.println(tree.sisipDt(dt) ? "BERHASIL" : "DUPLIKAT");
                    break;
                }
                case "CARI": {
                    int dt = Integer.parseInt(parts[1]);
                    System.out.println(tree.cariDt(dt) ? "ADA" : "TIDAK ADA");
                    break;
                }
                case "HAPUS": {
                    int dt = Integer.parseInt(parts[1]);
                    System.out.println(tree.hapusDt(dt) ? "BERHASIL" : "TIDAK ADA");
                    break;
                }
                case "INORDER": {
                    StringBuilder sb = new StringBuilder();
                    tree.inOrder(tree.root, sb);
                    cetakTraversal(sb);
                    break;
                }
                case "PREORDER": {
                    StringBuilder sb = new StringBuilder();
                    tree.preOrder(tree.root, sb);
                    cetakTraversal(sb);
                    break;
                }
                case "TINGGI": {
                    System.out.println(tree.tinggi());
                    break;
                }
                case "JUMLAH": {
                    System.out.println(tree.jumlahNode(tree.root));
                    break;
                }
            }
        }
        sc.close();
    }
}