package example.ch06.sec06;

import java.util.Scanner;

public class BoardcoverExample {
    public static final int[][][] coverType = {
        { {0, 0}, {1, 0}, {0, 1} }, //블록1
        { {0, 0}, {0, 1}, {1, 1} }, //블록2
        { {0, 0}, {1, 0}, {1, 1} }, //블록3
        { {0, 0}, {1, 0}, {1, -1} } //블록4
    };
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[][] board = new int[a][b];

        for (int i = 0; i < a; i++) {
            String line = scanner.next();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') board[i][j] = 1;
                else board[i][j] = 0;
            }
        }
        System.out.println(cover(board));
    }

    public static boolean set(int[][] board, int a, int b, int type, int delta) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            final int na = a + coverType[type][i][0]; //블록이 차지하는 위치들
            final int nb = b + coverType[type][i][1];
            if (na < 0 || na >= board.length || nb < 0 || nb >= board[0].length) ok = false; //보드 밖 범위면 블록을 놓을 수 없음
            else if ((board[na][nb] += delta) > 1) ok = false; //이미 채워져 있어 블록을 놓을 수 없음
        }
        return ok;
    }

    public static int cover(int[][] board) {
        int a = -1, b = -1;
        label: for (int i = 0; i < board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    a = i;
                    b = j;
                    break label;
                }
            }
        }

        if (a == -1) return 1; //모든 칸을 다 채운 경우
        int ret = 0;
        for (int type = 0; type < 4; ++type) {
            if (set(board, a, b, type, 1)) ret += cover(board); //채우기
            set(board, a, b, type, -1); //치우기
        }
        return ret;
    }
}
