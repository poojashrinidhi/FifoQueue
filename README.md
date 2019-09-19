Task: Develop a data structure for inter-thread communication.
-------------------------------------------------------------

Instructions to Run :
---------------------
mvn clean install<br />
java -jar target/CodingTask-0.0.1-SNAPSHOT.jar<br />

Implementation :
----------------

- If the queue is empty, a consumer thread (get()) will block until a producer thread (put()) adds an element to 
the queue.

- If the queue is full (e.g. reached its maximum capacity), a producer thread (put()) will block until a consumer thread
 (get()) removes an element from the queue.
 
 - Junits
