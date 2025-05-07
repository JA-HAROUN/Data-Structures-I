import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListQueueTest {

    @Test
    public void testEnqueueDequeue() {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());

    }

    @Test
    public void testEmpty() {

        LinkedListQueue queue = new LinkedListQueue();

        queue.enqueue(1);

        assertFalse(queue.isEmpty());

        int item = (int) queue.dequeue();

        assertTrue(queue.isEmpty());

    }

    @Test
    public void testSize() {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
    }

}