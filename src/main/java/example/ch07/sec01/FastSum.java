package example.ch07.sec01;

public class FastSum {
    public static void main(String[] args){
        long start = System.nanoTime();
        long fastSumValue = fastSum(10000);
        long fastSumTime = System.nanoTime() - start;

        long sumValue = 0;
        long start2 = System.nanoTime();
        for (long i = 0l; i <= 10000; i++) {
            sumValue += i;
        }
        long sumTime = System.nanoTime() - start2;

        System.out.println("Fast sum : " + fastSumValue + ", " + fastSumTime + "ns");
        System.out.println("sum : " + sumValue + ", " + sumTime + "ns");
    }

    static long fastSum(int n) {
        if (n == 1) return 1;
        if (n % 2 != 0) return fastSum(n-1) + n;
        return n*n/4 + 2 * fastSum(n/2);
    }
}
