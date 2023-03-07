package algospot.trianglepath;

import java.util.Scanner;

public class Main {
    static int[][] maxSum = new int[100][100];
    static int[][] triangle;
    static int n;
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        n = scanner.nextInt();
        triangle = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=i; j++) {
                maxSum[i][j] = 0;
                triangle[i][j] = scanner.nextInt();
            }
        }

        int maxSum = 0;
        for (int j = 0; j < n; j++) {
            int tempMaxSum = maxSum(n-1, j);
            if (tempMaxSum > maxSum) maxSum = tempMaxSum;
        }

        System.out.println(maxSum);
    }

    static int maxSum(int i, int j) {
        if (i < 0 || i < j || i >= n || j >= n || j < 0) return 0;
        if (maxSum[i][j] != 0) return maxSum[i][j];

        maxSum[i][j] = Math.max(maxSum(i - 1, j - 1), maxSum(i - 1, j)) + triangle[i][j];
        return maxSum[i][j];
    }
}
