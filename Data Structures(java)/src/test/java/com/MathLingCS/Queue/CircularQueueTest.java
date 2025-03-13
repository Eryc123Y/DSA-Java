package com.MathLingCS.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.MathLingCS.Array.DynamicArray;
import com.MathLingCS.LinkedList.SingleLinkedList;
import com.MathLingCS.Set.ArraySet;

class CircularQueueTest {

    private CircularQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new CircularQueue<>();
    }

    @Test
    void testConstructor() {
        assertNotNull(queue);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testConstructorWithInitialCapacity() {
        CircularQueue<Integer> queueWithCapacity = new CircularQueue<>(10);
        assertNotNull(queueWithCapacity);
        assertTrue(queueWithCapacity.isEmpty());
        assertEquals(0, queueWithCapacity.size());
    }

    @Test
    void testCopyConstructor() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        CircularQueue<Integer> copiedQueue = new CircularQueue<>(queue);
        assertEquals(queue.size(), copiedQueue.size());
        assertEquals(queue.peek(), copiedQueue.peek());
        assertEquals(queue.toString(), copiedQueue.toString());
    }

    @Test
    void testEnqueue() {
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertEquals(1, queue.peek());

        queue.enqueue(2);
        assertEquals(2, queue.size());
        assertEquals(1, queue.peek());
    }

    @Test
    void testEnqueueFullQueue() {
        CircularQueue<Integer> smallQueue = new CircularQueue<>(2);
        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        assertThrows(IllegalStateException.class, () -> smallQueue.enqueue(3));
    }

    @Test
    void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals(2, queue.peek());

        assertEquals(2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeueEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

    @Test
    void testPeek() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    void testPeekEmptyQueue() {
        assertThrows(IllegalStateException.class, () -> queue.peek());
    }

    @Test
    void testSize() {
        assertEquals(0, queue.size());

        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsFull() {
        CircularQueue<Integer> smallQueue = new CircularQueue<>(2);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(1);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(2);
        assertTrue(smallQueue.isFull());

        assertThrows(IllegalStateException.class, () -> smallQueue.enqueue(3));
    }

    @Test
    void testClear() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }







    @Test
    void testToString() {
        assertEquals("[]", queue.toString());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("[1,2,3]", queue.toString());
    }

    @Test
    void testIterator() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        int sum = 0;
        for (int num : queue) {
            sum += num;
        }
        assertEquals(6, sum);
    }

    @Test
    void testEnqueueDequeueMultiple() {
        queue = new CircularQueue<>(1000);
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        assertEquals(1000, queue.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }
}