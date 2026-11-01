package Modul8;

import java.util.Scanner;

public class QueueArr {
    char[] arr = new char[15];
    int rear = 0;

    public void enqueue(char item) {
        if (rear == arr.length) {
            System.out.println("Queue Penuh");
        } else {
            for (int i = rear; i > 0; i--) {
                arr[i] = arr[i - 1];
            }

            arr[0] = item;
            rear++;
        }
    }

    public void dequeue() {
        if (rear == 0) {
            System.out.println("\nQeue Kosong");
        } else {
            rear--;
        }
    }

    public void printAll() {
        for (int i = 0; i < rear; i++) {
            System.out.println("Index: " + i
                    + ", Value: " + arr[i]);
        }
    }

    public static void main(String[] args) {
        QueueArr n = new QueueArr();
        Scanner sc = new Scanner(System.in);
        int input = 1;
        char item;
        System.out.println("\n1.Enqueue\n2.Dequeue\n3.Print\n4.Exit\n");

        while (input != 0) {

            input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.print("Enqueue: ");
                    item = sc.next().charAt(0);
                    n.enqueue(item);
                    break;
                case 2:
                    System.out.println("Dequeue: " + n.arr[n.rear - 1]);
                    n.dequeue();
                    break;
                case 3:
                    n.printAll();
                    break;
                default:
                    System.out.print("\nProgram Selesai");
                    break;
            }
        }

    }
}
