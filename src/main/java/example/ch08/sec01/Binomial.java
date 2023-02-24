package example.ch08.sec01;

public class Binomial {
    static int[][] cache;
    public static void main(String[] args) {
        cache = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                cache[i][j] = -1;
            }
        }
        System.out.println(bino(10, 5));
    }

    static int bino(int n, int r) {
        if (r == 0 || n == r) return 1;
        if (cache[n][r] != -1) {
            return cache[n][r];
        }
        cache[n][r] = bino(n-1, r-1) + bino(n-1, r);
        return cache[n][r];
    }
}
