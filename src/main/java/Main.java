import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // 1 вариант решения
//        List<Callable<Integer>> callList = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            callList.add(new Shop("Магазин " + i));
//        }
//
//        List<Future<Integer>> futureList = threadPool.invokeAll(callList);
//        long result = 0;
//        for (Future<Integer> integer : futureList) {
//            result += integer.get();
//        }
//        System.out.println("Выручка из трех магазинов: " + result + " руб.");
//        threadPool.shutdown();

        // 2 вариант решения
        LongAdder stat = new LongAdder();
        IntStream.range(0,100)
                .forEach(i -> threadPool.submit(() -> stat.add(i)));
        IntStream.range(101, 200)
                .forEach(i -> threadPool.submit(() -> stat.add(i)));
        IntStream.range(201, 300)
                .forEach(i -> threadPool.submit(() -> stat.add(i)));
        threadPool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("\n Результат: " + stat.sum());
        threadPool.shutdown();
    }

}

