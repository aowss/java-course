package course.ch17.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class ProducerConsumerTest {

    @Test
    void produceAndConsumeSingleItem() throws InterruptedException {
        var buffer = new ProducerConsumer<String>(5);
        buffer.produce("hello");
        assertEquals(1, buffer.size());
        assertEquals("hello", buffer.consume());
        assertEquals(0, buffer.size());
    }

    @Test
    void fifoOrder() throws InterruptedException {
        var buffer = new ProducerConsumer<Integer>(10);
        buffer.produce(1);
        buffer.produce(2);
        buffer.produce(3);
        assertEquals(1, buffer.consume());
        assertEquals(2, buffer.consume());
        assertEquals(3, buffer.consume());
    }

    @Test
    void producerBlocksWhenFull() throws InterruptedException {
        var buffer = new ProducerConsumer<Integer>(2);
        buffer.produce(1);
        buffer.produce(2);

        CountDownLatch started = new CountDownLatch(1);
        Thread producer = new Thread(() -> {
            try {
                started.countDown();
                buffer.produce(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        started.await();
        Thread.sleep(100);

        assertEquals(2, buffer.size());

        buffer.consume();
        producer.join(5000);
        assertEquals(2, buffer.size());
    }

    @Test
    void consumerBlocksWhenEmpty() throws InterruptedException {
        var buffer = new ProducerConsumer<String>(5);
        List<String> results = new ArrayList<>();

        CountDownLatch started = new CountDownLatch(1);
        Thread consumer = new Thread(() -> {
            try {
                started.countDown();
                results.add(buffer.consume());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        started.await();
        Thread.sleep(100);

        assertTrue(results.isEmpty());

        buffer.produce("delayed");
        consumer.join(5000);
        assertEquals(List.of("delayed"), results);
    }

    @Test
    void concurrentProducersAndConsumers() throws InterruptedException {
        int itemCount = 1000;
        var buffer = new ProducerConsumer<Integer>(10);
        List<Integer> consumed = java.util.Collections.synchronizedList(new ArrayList<>());

        Thread producer = new Thread(() -> {
            for (int i = 0; i < itemCount; i++) {
                try { buffer.produce(i); } catch (InterruptedException e) { break; }
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < itemCount; i++) {
                try { consumed.add(buffer.consume()); } catch (InterruptedException e) { break; }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        assertEquals(itemCount, consumed.size());
    }

    @Test
    void constructorRejectsNonPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ProducerConsumer<>(0));
        assertThrows(IllegalArgumentException.class, () -> new ProducerConsumer<>(-1));
    }
}
