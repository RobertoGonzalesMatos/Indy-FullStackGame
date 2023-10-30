package indy;

import java.util.LinkedList;

public class Queue {
    private final LinkedList <Integer> myQueue;
    /**
     * Constructor for the Queue class
     */
    public Queue() {
        this.myQueue = new LinkedList<>();
    }
    /**
     * Method that adds elements to the queue
     */
    public void enqueue(int key) {
        this.myQueue.addLast(key);
    }
    /**
     * Method that removes elements from the queue
     */
    public int dequeue() {
        return this.myQueue.remove(0);
    }
    /**
     * Method that checks if an element is inside the queue
     */
    public boolean contains(int keyNumber) {
        return this.myQueue.contains(keyNumber);
    }
    /**
     * Method that clears the queue
     */
    public void clear() {
        this.myQueue.clear();
    }
    /**
     * Gets the element 0 of the Queue
     */
    public int getIndex0() {
        return this.myQueue.get(0);
    }
}
