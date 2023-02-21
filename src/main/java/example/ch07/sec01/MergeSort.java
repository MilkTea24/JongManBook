package example.ch07.sec01;

import java.util.Scanner;

public class MergeSort {
    static int[] arr;
    static int n;
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        mergeSort(0, n-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }


    }
    public static void mergeSort(int start, int end) { //start에서 end까지 정렬해주는 함수
        if (start < end) { //원소가 1개인지 아닌지 검사
            int mid = (start + end) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end); //start에서 mid까지와 mid+1에서 end까지 합쳐주는 함수
        }
    }

    public static void merge(int start, int mid, int end) {
        int[] tmp = new int[n];
        int ind1 = start;  int ind2 = mid + 1;  int indtmp = 0;
        while (ind1 <= mid && ind2 <= end) {
            System.out.println(arr[ind1] + " " + arr[ind2]);
            if (arr[ind1] < arr[ind2]) {
                tmp[indtmp++] = arr[ind1++];
            }
            else tmp[indtmp++] = arr[ind2++];
        }
        int temp = mid - ind1;
        for (int i = 0; i <= temp; i++) {
            tmp[indtmp++] = arr[ind1++];
        } //두 배열 중 원소가 남은 배열 마저 채우기
        temp = end - ind2;
        for (int i = 0; i <= temp; i++) {
            tmp[indtmp++] = arr[ind2++];
        } //두 배열 중 원소가 남은 배열 마저 채우기

        int j = 0;
        for (int i = start; i <= end; i++) {
            arr[i] = tmp[j++]; //정렬된 부분 배열을 원 배열에 붙여넣기
        }
    }
}
