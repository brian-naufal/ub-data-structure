public class queue_adt {


    int max = 10;
    char[] arr = new char[max];
    int rear = 0;
    char temp;

    void enque(char item) {
        if (rear == max) {
            System.out.println("penuh");
        }
        else {
                temp = item;
                for (int i = rear; i > 0; i--) {
                    arr[i] = arr[i-1];
                }
                arr[0] = temp;
                rear++;
        }
    }

    void deque() {
        if (rear == 0) {
            System.out.println("Kosong");
        }
        else {
            temp = arr[rear - 1];
            for (int i = rear - 1; i >= 1; i--) {
                arr[i] = arr[i - 1];
            }
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear--;
            System.out.println("Pop: "+ temp);
        }
    }

    void printd() {
        for (int i = 0; i < rear; i++) {
            System.out.println(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        queue_adt n = new queue_adt();
        n.enque('a');
        n.enque('b');
        n.enque('c');
        n.enque('d');
        n.enque('e');
        n.enque('f');

        n.printd();
        System.out.println();
        n.deque();
        n.printd();
        System.out.println();
        n.deque();
        n.printd();
        n.deque();
        n.printd();
        n.deque();
        n.printd();
    }
}
