package org.example;

public class QueueTees <T>{
    private T[] arr;
    private int front, rear;
    private int capacity = 10; // Default size of the queue

    public QueueTees() {
        arr = (T[]) new Object[this.capacity];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return (rear == capacity - 1);
    }

    public boolean isEmpty() {
        return (front == -1 || front > rear);
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + item);
            return;
        }
        if (front == -1) {
            front = 0; // Initialize front on first enqueue
        }
        arr[++rear] = item;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }
        T item = arr[front];
        front++;
        if (front > rear) { // Reset queue if it becomes empty
            front = -1;
            rear = -1;
        }
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot peek.");
            return null;
        }
        return arr[front];
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear - front + 1;
    }

    public void clear() {
        front = -1;
        rear = -1;
    }
}
