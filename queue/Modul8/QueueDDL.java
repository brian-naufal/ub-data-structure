package Modul8;

import java.util.Scanner;

class Node {
    char data;
    Node prev;
    Node next;

    Node(char data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class QueueDDL {

    int count = 0;
    Node head = null;
    Node tail = null;

    public void enqueue(char item) {
        Node temp = new Node(item);

        if (head == null) {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }

        count++;
    }

    public void dequeue() {

        if (tail == null) {
            System.out.println("Queue Kosong");
        } else {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }

            count--;
        }
    }

    public void printAll() {

        Node temp = head;
        int i = 0;

        while (temp != null) {
            System.out.print(temp.data + " ");

            temp = temp.next;
            i++;
        }
    }

    public static void main(String[] args) {
        QueueDDL n = new QueueDDL();
        Scanner sc = new Scanner(System.in);
        int input = 1;
        System.out.println("1.Enqueue\n2.Dequeue\n3.Print\n4.Exit\n");

        while (input != 0) {
            input = sc.nextInt();

            switch (input) {
                case 1:
                    System.out.print("Enqueue: ");
                    char item = sc.next().charAt(0);
                    n.enqueue(item);
                    break;
                case 2:
                    System.out.println("Dequeue: " + n.tail.data);
                    n.dequeue();
                    break;
                case 3:
                    n.printAll();
                    break;
                default:
                    input = 0;
                    System.out.print("\nProgram Selesai");
                    break;
            }
        }
    }
}
