import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayQueueTest {

    @Test
    public void testEnqueueDequeue() {

        ArrayQueue queue = new ArrayQueue(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, (int) queue.dequeue());
        assertEquals(2, (int) queue.dequeue());
        assertEquals(3, (int) queue.dequeue());

    }

    @Test
    public void testEmpty() {
        ArrayQueue queue = new ArrayQueue(5);

        assertTrue(queue.isEmpty());

        queue.enqueue(1);

        assertFalse(queue.isEmpty());

    }

    @Test
    public void testSize() {
        ArrayQueue queue = new ArrayQueue(5);

        assertEquals(0, queue.size());

        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size());

        queue.dequeue();

        assertEquals(1, queue.size());


    }

}