package com.yieldlab.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import com.yieldlab.util.ConsumerThread;
import com.yieldlab.util.ProducerThread;

/**
 * @author PoojaShankar
 * 
 * Code Coverage : 92.1% (Eclemma)
 */
public class FifoQueueTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds max per method tested

	@Test
	public void testPutQueue() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(3);
		fifoQueue.put("1");
		fifoQueue.put("2");
		assertTrue(fifoQueue.getQueueSize() == 2);
	}

	@Test
	public void testGetQueue() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(3);
		fifoQueue.put("1");
		fifoQueue.put("2");
		fifoQueue.get();
		assertTrue(fifoQueue.getQueueSize() == 1);
	}

	@Test
	public void testFifoFunctionality() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(5);

		fifoQueue.put("1");
		fifoQueue.put("2");
		fifoQueue.put("3");

		assertTrue(fifoQueue.get().equals("1"));
		assertTrue(fifoQueue.get().equals("2"));

		fifoQueue.put("4");
		assertTrue(fifoQueue.get().equals("3"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testZeroQueueSize() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(0);
	}

	@Test
	public void testQueueFull() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(1);
		Thread producerThread = new Thread(new ProducerThread(fifoQueue, 2));
		producerThread.start();

		boolean enteredWaiting = false;
		while (!producerThread.getState().toString().equals("TERMINATED")) {
			if (checkIfThreadIsWaiting(producerThread)) {
				producerThread.interrupt();
				enteredWaiting = true;
				break;
			}
		}

		if (enteredWaiting) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	public boolean checkIfThreadIsWaiting(Thread t) {
		if (t.getState().toString().equals("WAITING")) {
			return true;
		}

		return false;
	}

	@Test
	public void testQueueEmpty() throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(2);
		fifoQueue.put("1");
		fifoQueue.put("2");
		Thread consumerThread = new Thread(new ConsumerThread(fifoQueue, 3));
		consumerThread.start();

		boolean enteredWaiting = false;
		while (!consumerThread.getState().toString().equals("TERMINATED")) {
			if (checkIfThreadIsWaiting(consumerThread)) {
				consumerThread.interrupt();
				enteredWaiting = true;
				break;
			}
		}

		if (enteredWaiting) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
