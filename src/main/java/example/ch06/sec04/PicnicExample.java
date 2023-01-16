package example.ch06.sec04;

import java.util.LinkedList;
import java.util.Scanner;

public class PicnicExample {
    public static int n;
    public static boolean[][] areFriends;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int caseNumber = Integer.parseInt(scanner.next());
        for (int i = 0; i < caseNumber; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        n = Integer.parseInt(scanner.next());
        int mateNumber = Integer.parseInt(scanner.next());
        areFriends = new boolean[n][n];

        for (int i = 0; i < mateNumber; i++) {
            int first = Integer.parseInt(scanner.next());
            int second = Integer.parseInt(scanner.next());
            areFriends[second][first] = true;
            areFriends[first][second] = true;
        }
        System.out.println(countPairings(new boolean[n]));
    }

    public static int countPairings(boolean taken[]) {
        int firstFree = -1; //남은 학생들 중 가장 번호가 빠른 학생
        for (int i = 0; i < n; ++i) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }
        if (firstFree == -1) return 1; //모든 학생이 짝을 찾음
        int ret = 0;
        for (int pairWith = firstFree+1; pairWith < n; ++pairWith) {
            if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = taken[pairWith] = true; //예시 답안에서는 bool 배열의 인덱스에 true, false 표시하는 것으로 해결
                ret += countPairings(taken);
                taken[firstFree] = taken[pairWith] = false;
            }
        }
        return ret;
    }

    /*
    public static int countPairings(boolean taken[]) {
        boolean finished = true;
        for (int i = 0; i < n; ++i) if(!taken[i]) finished = false; //모든 학생이 짝을 찾았으면 종료
        if (finished) return 1;
        int ret = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                if (!taken[i] && !taken[j] && areFriends[i][j]) {
                    taken[i] = taken[j] = true;
                    ret += countPairings(taken);
                    taken[i] = taken[j] = false;
                }
        }

        return ret;
    }
    */
}
