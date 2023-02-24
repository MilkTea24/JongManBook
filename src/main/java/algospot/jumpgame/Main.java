package algospot.jumpgame;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int[][] board;
    static boolean[][] visited;
    static int len;
    public static void main(String[] args){
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        len = scanner.nextInt();
        board = new int[len][len];
        visited = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int temp = scanner.nextInt();
                board[i][j] = temp;
            }
        }
        if (jump(board[0][0], 0, 0)) System.out.println("YES");
        else System.out.println("NO");
    }

    static boolean jump(int n, int x, int y) {
        if (x >= len || y >= len) return false;
        visited[y][x] = true;
        boolean result = false;
        if (board[y][x] == 0 || board[y][x] == 0) return true;
        if (y + n < len && visited[y + n][x] == false) result = jump(board[y + n][x], x, y + n) || result;
        if (x + n < len && visited[y][x + n] == false) result = jump(board[y][x + n], x + n, y) || result;
        return result;
    }
}
