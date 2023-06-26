package com.solvd.laba.utils;

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList<T> {
    Node<T> head;
    private int length = 0;

    public LinkedList() {
        this.head = null;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
    }

    public void remove(T removeNode) {
        Node<T> prev = new Node<>(null);
        prev.next = head;
        Node<T> next = head.next;
        Node<T> currentNode = head;
        boolean exists = false;

        if (head.data == removeNode) {
            head = head.next;
            exists = true;
        }
        while (currentNode.next != null) {
            if (String.valueOf(currentNode.data).equals(String.valueOf(removeNode))) {
                prev.next = next;
                exists = true;
                break;
            }
            prev = currentNode;
            currentNode = currentNode.next;
            next = currentNode.next;
        }

        if (!exists && String.valueOf(currentNode.data).equals(String.valueOf(removeNode))) {
            prev.next = null;
            exists = true;
        }

        if (exists) {
            length--;
        } else {
            System.out.println("The given value is not available");
        }
    }

    public void clear() {
        head = null;
        length = 0;
    }

    public boolean empty() {
        return head == null;
    }

    public int length() {
        return this.length;
    }

    public void display() {
        Node<T> current = head;

        if (head == null) {
            System.out.println("Empty");
            return;
        }

        while (current != null) {
            System.out.print(current.data + "\n");
            current = current.next;
        }        
    }
}
