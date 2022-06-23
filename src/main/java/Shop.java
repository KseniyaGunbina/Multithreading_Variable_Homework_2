import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

public class Shop implements Callable<Integer> {
    private int[] allSum = new int[100];
    private String name;
    public static final int MIN = 0;
    public static final int MAX = 300;

    public Shop(String name) {
        for (int i = 0; i < allSum.length; i++) {
            allSum[i] = MIN + new Random().nextInt(MAX - MIN + 1);
        }
        this.name = name;
    }

    @Override
    public Integer call(){
        Integer sum = Arrays.stream(allSum).sum();
        System.out.println("Выручка магазина " + name + " составила " + sum + " руб.");
        return sum;
    }
}
