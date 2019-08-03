package com.yieldlab.util;

import com.yieldlab.impl.FifoQueue;

/**
 * Utility Class to perform Consumer get() operations on the FIFO Queue.
 * 
 * @author PoojaShankar
 *
 */
public class ConsumerThread implements Runnable {

	private FifoQueue fifoQueue;
	private int noOfConsumedItems;

	public ConsumerThread(FifoQueue fifoQueue, int noOfItems) {
		this.fifoQueue = fifoQueue;
		this.noOfConsumedItems = noOfItems;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.noOfConsumedItems; i++) {
			try {
				Object item = fifoQueue.get();
				System.out.println("Consumed : " + item);
			} catch (InterruptedException e) {
			}
		}
	}
}