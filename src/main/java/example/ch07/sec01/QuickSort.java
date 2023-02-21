package example.ch07.sec01;

import java.util.Scanner;

public class QuickSort {
    static int n;
    static int[] arr;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        QuickSort(0, n-1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    static void QuickSort(int start, int end){
        if (start < end) {
            int pivotNumber = arr[start];
            int[] left = new int[n];
            int[] right = new int[n];
            int j = start;
            int k = start;

            for (int i = start; i <= end; i++) {
                if (pivotNumber > arr[i]) left[j++] = arr[i];
                else right[k++] = arr[i];
            }

            for (int i = start; i < j; i++) {
                arr[i] = left[i];
            }
            k = start;
            for (int i = j; i <= end; i++) {
                arr[i] = right[k++];
            }
            QuickSort(start, j-1);
            QuickSort(j+1, end);
        }
    }
}
