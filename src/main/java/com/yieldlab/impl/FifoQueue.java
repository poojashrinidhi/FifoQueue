package com.yieldlab.impl;

import com.yieldlab.util.BoundedBlockingQueue;

/**
 * First-In-First-Out FIFO Implementation of the BoundedBlockingQueue interface.
 * 
 * @author PoojaShankar
 */
public class FifoQueue implements BoundedBlockingQueue {

	private final static Integer DEFAULT_MAX_SIZE = 10000;	// Default Max size if user does not provide size

	private Integer maxSize; 								// Maximum fixed size input by user
	private Integer tailPosition = 0; 						// Points to the position for insertion
	private Integer headPosition = 0; 						// Points to the position of retrieval
	private Integer queueSize = 0; 							// Number of elements at a given time
	private Object[] fifoQueue; 							// Data Storage array for FifoQueue elements

	public FifoQueue() {
		this(DEFAULT_MAX_SIZE);
	}

	public FifoQueue(Integer size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		this.maxSize = size;
		this.fifoQueue = new Object[size];
	}
	
	public Object[] getFifoQueue() {
		return fifoQueue;
	}

	public Integer getQueueSize() {
		return queueSize;
	}

	public boolean isQueueFull() {
		if (this.queueSize == this.maxSize) {
			return true;
		}
		return false;
	}

	public boolean isQueueEmpty() {
		if (this.queueSize == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Adds an element to the queue. Calling Thread will wait if the queue is full
	 * until the space is available i.e. Until the get() is called.
	 * 
	 * @param element Element to add to the queue.
	 * @throws InterruptedException
	 */
	public synchronized void put(Object element) throws InterruptedException {
		while (isQueueFull()) {
			System.out.println("Queue is full");
			wait();
		}
		this.fifoQueue[tailPosition] = element;
		this.tailPosition = (this.tailPosition + 1) % maxSize;
		this.queueSize++;
		notify();
	}

	/**
	 * Removes an element from the queue. Calling Thread will wait if the queue is
	 * empty until an element is inserted i.e. Until any put(Object element) is
	 * called.
	 * 
	 * @return The element that is on the queue for the longest time.
	 * @throws InterruptedException
	 */
	public synchronized Object get() throws InterruptedException {
		while (isQueueEmpty()) {
			System.out.println("Queue is Empty");
			wait();
		}
		Object headElement = this.fifoQueue[headPosition];
		this.headPosition = (this.headPosition + 1) % maxSize;
		this.queueSize--;
		notify();
		return headElement;
	}
}