package algospot.fence;

import java.util.Scanner;

public class Main {
    static int[] fence;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        int n = scanner.nextInt();
        fence = new int[n];
        for (int i = 0; i < n; i++) {
            int temp = scanner.nextInt();
            fence[i] = temp;
        }

        //long size = maxSize(0, n-1);
        long size = maxSize2(n);
        System.out.println(size);
    }

    static long maxSize2(int n) {
        long ret = 0;
        for (int i = 0; i < n; i++) {
            int leftIter = i;
            int rightIter = i;
            while (leftIter > 0 && fence[leftIter - 1] >= fence[i]) {
                leftIter--;
            }
            while (rightIter < n-1 && fence[rightIter + 1] >= fence[i]) {
                rightIter++;
            }
            long leftAndRightSize = (rightIter - leftIter + 1) * fence[i];
            ret = Long.max(ret, leftAndRightSize);
        }
        return ret;
    }

    static long maxSize(int start, int end) {
        if (start == end) return fence[start];
        int half = (start + end) / 2;

        long leftSize = maxSize(start, half);
        long rightSize = maxSize(half+1, end);

        long maxSizeOfBoth = Long.max(leftSize, rightSize);

        int leftIter = half;
        int rightIter = half;
        while (leftIter > start && fence[leftIter + 1] >= fence[half]) {
            leftIter--;
        }
        while (rightIter < end && fence[rightIter + 1] >= fence[half]) {
            rightIter++;
        }
        long leftAndRightSize = (rightIter - leftIter + 1) * fence[half];
        long ret = Long.max(maxSizeOfBoth, leftAndRightSize);
        return ret;
        /*
        int leftIter = half + 1;
        int rightIter = half;

        int minHeight = fence[half];
        long maxSize = minHeight;
        int leftMinHeight = minHeight;
        while (leftIter > start) {
            leftIter--;
            leftMinHeight = Integer.min(fence[leftIter], leftMinHeight);
            minHeight = leftMinHeight;
            rightIter = half;
            while (rightIter < end) {
                rightIter++;
                if (minHeight < fence[rightIter]) continue;
                maxSize = Long.max((rightIter - leftIter) * minHeight, maxSize);
                minHeight = fence[rightIter];
            }
            maxSize = Long.max((rightIter - leftIter + 1) * minHeight, maxSize);
        }
        long ret = Long.max(maxSizeOfBoth, maxSize);
        return ret;
         */
    }
}
