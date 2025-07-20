package org.example;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueTeesTest {
    QueueTees<Cutie> queue;

    @BeforeEach
    public void setUp() {
        queue = new QueueTees<>();
        queue.enqueue(new Kitty());
        queue.enqueue(new PygmyMarmoset());
        queue.enqueue(new Puppy());
    }

    @Test
    public void testEnqueueSize() {
        assertEquals(3, queue.size());

        queue.enqueue(new Puppy());
        assertEquals(4, queue.size());

        for (int i = 0; i < 6; i++) {
            queue.enqueue(new Puppy()); 
        }
        assertTrue(queue.isFull());
    }

    @Test
    public void testDequeue() {
        Cutie first = queue.dequeue();
        assertInstanceOf(Kitty.class, first);

        Cutie second = queue.dequeue();
        assertInstanceOf(PygmyMarmoset.class, second);

        Cutie third = queue.dequeue();
        assertInstanceOf(Puppy.class, third);

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPeek() {
        Cutie peeked = queue.peek();
        assertInstanceOf(Kitty.class, peeked);
        assertEquals("A little litty with big, sad eyes", peeked.description());
        assertEquals(12, peeked.cutenessRating());

        // Dequeue to check if the peeked item is removed
        queue.dequeue();
        Cutie nextPeek = queue.peek();
        assertInstanceOf(PygmyMarmoset.class, nextPeek);
    }

    @Test
    public void testIsEmpty() {
        assertFalse(queue.isEmpty());
        queue.dequeue(); // Remove all items
        queue.dequeue();
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsFull() {
        // The queue starts with a capacity of 10, so it should not be full initially
        assertFalse(queue.isFull());

        // Fill the queue to its capacity
        for (int i = 0; i < 10; i++) {
            queue.enqueue(new Puppy()); // Assuming Puppy is a Cutie
        }
        assertTrue(queue.isFull());
    }

    @Test
    public void testClear() {
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        queue.clear(); // Ensure the queue is empty
        Cutie item = queue.dequeue();
        assertNull(item);
    }

    @Test
    public void testPeekFromEmptyQueue() {
        queue.clear(); // Ensure the queue is empty
        Cutie item = queue.peek();
        assertNull(item);
    }

    @Test
    public void testEnqueueWhenFull() {
        // Fill the queue to its capacity
        for (int i = 0; i < 10; i++) {
            queue.enqueue(new Puppy());
        }
        // Attempt to enqueue one more item
        queue.enqueue(new Puppy()); // This should print a message and not add the item
        assertEquals(10, queue.size()); // Size should still be 10
    }

    @Test
    public void testIntegerType() {
        QueueTees<Integer> intQueue = new QueueTees<>();
        intQueue.enqueue(1);
        intQueue.enqueue(2);
        intQueue.enqueue(3);

        assertEquals(3, intQueue.size());
        assertEquals(Integer.valueOf(1), intQueue.dequeue());
        assertEquals(Integer.valueOf(2), intQueue.dequeue());
        assertEquals(Integer.valueOf(3), intQueue.dequeue());
        assertTrue(intQueue.isEmpty());
    }

    @Test
    public void testStringType() {
        QueueTees<String> stringQueue = new QueueTees<>();
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");

        assertEquals(2, stringQueue.size());
        assertEquals("Hello", stringQueue.dequeue());
        assertEquals("World", stringQueue.dequeue());
        assertTrue(stringQueue.isEmpty());
    }
}
