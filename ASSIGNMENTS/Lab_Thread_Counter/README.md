Lab: Thread Counter — Static vs Non-Static Variables

Course: Software Engineering and Project Management
Problem ID: SEPM-001
Student: Afif Zaman
Programming Language: Java

1. Objective

The purpose of this experiment is to compare a shared static counter with non-static instance counters in a multithreaded Java program. The experiment demonstrates how unsynchronized access to shared mutable data can cause lost updates.

2. Implementation

The program is implemented in AfifZaman_Thread.java as a single Java class.

Two experiments are performed:

Thread-safe mode: Uses AtomicLong for the shared static counter.
Unsafe mode: Uses an ordinary, unsynchronized static long counter.

Each thread has its own task object and non-static counter. The program waits for all threads to finish before calculating the final totals.

3. How to Compile and Run

Compile the program from the source directory:

javac AfifZaman_Thread.java


Run the thread-safe experiment:

java AfifZaman_Thread 10 50000 true


Run the unsafe experiment:

java AfifZaman_Thread 10 50000 false


The arguments represent the number of threads, increments per thread, and experiment mode, respectively.

4. Test Cases
Test	Threads	Increments per Thread	Expected Count
TC1	1	1,000	1,000
TC2	2	10,000	20,000
TC3	5	10,000	50,000
TC4	10	50,000	500,000
TC5	20	50,000	1,000,000
TC6	50	50,000	2,500,000
TC7	100	50,000	5,000,000

The unsafe experiment is repeated at least five times for every thread count. Actual output files are stored in the outputs/ directory.

5. Results and Analysis

The thread-safe experiment should produce the expected static count and non-static total, giving an absolute difference of zero and a percentage difference of 0%.

The unsafe experiment may produce a static count below the expected total because concurrent updates can overwrite one another. The actual measured values, absolute differences, percentages, and five-run averages must be recorded in the result tables and results.csv.

Analysis Questions

1. What is the difference between a static and a non-static variable?

A static variable belongs to the class and is shared among instances of that class. A non-static variable belongs to an individual object, so different objects can hold different values.

2. Why do all threads share the static counter?

All task objects access the same class-level static field. Therefore, they update the same shared counter.

3. Why does each thread have its own non-static counter?

Each thread uses its own task object, which has a separate instance field. Its increments do not interfere with the instance counters of other task objects.

4. Why is join() required?

The join() method allows the main thread to wait for worker threads to finish before reading final totals. Without waiting, the results could be collected before all increments have completed.

5. Why can the unsafe static count be lower than expected?

The expression counter++ is not an atomic operation. Multiple threads may read the same old value and write back competing updates, causing lost increments.

6. Does increasing the number of threads always increase the percentage difference?

No. More threads can increase contention, but the percentage difference does not necessarily increase monotonically. Scheduling, timing, processor resources, and runtime conditions affect the observed result.

7. Why can repeated runs differ?

Thread scheduling and the timing of competing read-modify-write operations can change between runs, so the number of lost updates may vary.

8. What changes when AtomicLong is used?

AtomicLong provides atomic updates to the shared counter. Its increment operations avoid the lost-update problem that occurs with an ordinary unsynchronized long.

9. How could all threads share one instance counter as well as the static counter?

The program could give every task a reference to the same shared counter object. Because the instance field would then be accessed by multiple threads, it would need appropriate synchronization or an atomic type to ensure correct counting.

6. Observation

The thread-safe experiment provides a control case in which the shared static total should equal the sum of the non-static totals. The unsafe experiment demonstrates that shared mutable data can lose updates when multiple threads modify it without synchronization.

The actual percentage differences and their averages should be used to compare thread counts. A larger thread count may increase contention, but observed differences can vary and are not guaranteed to rise steadily.

7. Conclusion

The experiment demonstrates that static describes ownership, not thread safety. A static variable is shared across instances, but concurrent access to a mutable static variable still requires atomic operations or synchronization.

Using AtomicLong protects the shared counter from lost updates. Using an ordinary unsynchronized long can produce incorrect totals when multiple threads increment it concurrently. Non-static counters remain independent when each thread owns a separate task object.

The final conclusions are based on the actual program outputs recorded in this repository.
