package com.yieldlab.task;

import com.yieldlab.impl.FifoQueue;
import com.yieldlab.util.ConsumerThread;
import com.yieldlab.util.ProducerThread;

public class Task {

	public static void main(String[] args) throws InterruptedException {
		FifoQueue fifoQueue = new FifoQueue(2);

		Thread producerThread = new Thread(new ProducerThread(fifoQueue, 2));
		Thread consumerThread = new Thread(new ConsumerThread(fifoQueue, 3));

		producerThread.start();
		consumerThread.start();

		consumerThread.interrupt();
	}
}