Task: Develop a data structure for inter-thread communication.
-------------------------------------------------------------

Instructions to Run :
---------------------
mvn clean install<br />
java -jar target/CodingTask-0.0.1-SNAPSHOT.jar<br />
Part I<br />

Write a fixed-size FIFO queue (e.g. max 10 elements). The queue should implement the com.yieldlab.util.BoundedBlockingQueue
 interface. For this task, you are not allowed to use any classes from the java.util.concurrent package.

Your implementation should support multi-threaded access and, at a minimum, display the following behavior:

- If the queue is empty, a consumer thread (get()) should block until a producer thread (put()) adds an element to 
the queue.

- If the queue is full (e.g. reached its maximum capacity), a producer thread (put()) should block until a consumer thread
 (get()) removes an element from the queue.


Part II

In addition, write jUnit tests that verify that the queue implements these specifications.

