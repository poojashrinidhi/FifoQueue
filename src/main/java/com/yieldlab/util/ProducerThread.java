package com.yieldlab.util;

import com.yieldlab.impl.FifoQueue;

/**
 * Utility Class to perform Producer put(object) operations on the FIFO Queue.
 * 
 * @author PoojaShankar
 *
 */
public class ProducerThread implements Runnable {

	private FifoQueue fifoQueue;
	private int noOfProducedItems;

	public ProducerThread(FifoQueue fifoQueue, int noOfItems) {
		this.fifoQueue = fifoQueue;
		this.noOfProducedItems = noOfItems;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.noOfProducedItems; i++) {
			try {
				fifoQueue.put(i);
				System.out.println("Produced : " + i);
			} catch (InterruptedException e) {
			}
		}
	}
}