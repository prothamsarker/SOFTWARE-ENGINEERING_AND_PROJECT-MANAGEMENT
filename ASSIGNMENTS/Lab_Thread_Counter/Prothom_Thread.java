import java.util.concurrent.atomic.AtomicLong;

public class Prothom_Thread implements Runnable {

    private static final AtomicLong safeCounter = new AtomicLong(0);
    private static long unsafeCounter = 0;

    private final long increments;
    private final boolean threadSafe;
    private long instanceCounter = 0;

    public Prothom_Thread(long increments, boolean threadSafe) {
        this.increments = increments;
        this.threadSafe = threadSafe;
    }

    @Override
    public void run() {
        for (long i = 0; i < increments; i++) {
            if (threadSafe) {
                safeCounter.incrementAndGet();
            } else {
                unsafeCounter++;
            }

            instanceCounter++;
        }
    }

    public long getInstanceCounter() {
        return instanceCounter;
    }

    public static void main(String[] args)
            throws InterruptedException {

        if (args.length != 3) {
            System.out.println(
                    "Usage: java Prothom_Thread <threads> " +
                            "<increments> <true|false>");
            return;
        }

        int numberOfThreads = Integer.parseInt(args[0]);
        long increments = Long.parseLong(args[1]);
        boolean threadSafe;

        if (args[2].equalsIgnoreCase("true")) {
            threadSafe = true;
        } else if (args[2].equalsIgnoreCase("false")) {
            threadSafe = false;
        } else {
            System.out.println("Mode must be true or false.");
            return;
        }

        if (numberOfThreads <= 0 || increments < 0) {
            System.out.println("Invalid input values.");
            return;
        }

        safeCounter.set(0);
        unsafeCounter = 0;

        Prothom_Thread[] tasks =
                new Prothom_Thread[numberOfThreads];
        Thread[] threads = new Thread[numberOfThreads];

        long expectedCount =
                (long) numberOfThreads * increments;

        for (int i = 0; i < numberOfThreads; i++) {
            tasks[i] =
                    new Prothom_Thread(increments, threadSafe);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        long staticCount = threadSafe
                ? safeCounter.get()
                : unsafeCounter;

        long nonStaticTotal = 0;

        for (Prothom_Thread task : tasks) {
            nonStaticTotal += task.getInstanceCounter();
        }

        long difference =
                Math.abs(staticCount - nonStaticTotal);

        String percentage;

        if (nonStaticTotal == 0) {
            percentage = staticCount == 0
                    ? "0.00%"
                    : "Undefined";
        } else {
            double percent =
                    100.0 * difference / nonStaticTotal;

            percentage = String.format("%.2f%%", percent);
        }

        System.out.println("Threads: " + numberOfThreads);
        System.out.println("Increments per thread: " + increments);
        System.out.println("Mode: " +
                (threadSafe ? "Thread-safe" : "Unsafe"));
        System.out.println("Expected count: " + expectedCount);
        System.out.println("Static count: " + staticCount);
        System.out.println("Non-static total: " + nonStaticTotal);
        System.out.println("Absolute difference: " + difference);
        System.out.println("Percentage difference: " + percentage);
    }
}
