package algospot.boardcover;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.next());
        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        int a = Integer.parseInt(scanner.next());
        int b = Integer.parseInt(scanner.next());

        boolean[][] board = new boolean[a][b];
        for (int i = 0; i < a; i++) {
            String line = scanner.next();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '#') board[i][j] = true;
                else board[i][j] = false;
            }
        }
        System.out.println(canCover(board, a, b));
    }

    public static int canCover(boolean[][] board, int a, int b) {
        int result = 0;
        boolean allCovered = true;
        int x = 0; int y = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (board[i][j] == false) {
                    allCovered = false;
                    x = i;  y = j;
                    break;
                }
            }
        }

        if (allCovered) return 1;

        if ((x+1) < a && (y+1) < b) {
            if ((!board[x][y+1]) && (!board[x+1][y+1])) {
                board[x][y] = true; board[x][y+1] = true;   board[x+1][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y+1] = false;  board[x+1][y+1] = false;
            }
            if (!board[x+1][y] && !board[x+1][y+1]) {
                board[x][y] = true; board[x+1][y] = true;   board[x+1][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x+1][y] = false;  board[x+1][y+1] = false;
            }
            if (!board[x+1][y] && !board[x][y+1]) {
                board[x][y] = true; board[x+1][y] = true;   board[x][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x+1][y] = false;   board[x][y+1] = false;
            }
        }
        if ((x+1) < a && y > 0) {
            if (!board[x+1][y] && !board[x+1][y-1]) {
                board[x][y] = true; board[x+1][y] = true; board[x+1][y-1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x+1][y] = false; board[x+1][y-1] = false;
            }
            if (!board[x][y-1] && !board[x+1][y-1]) {
                board[x][y] = true; board[x][y-1] = true; board[x+1][y-1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y-1] = false; board[x+1][y-1] = false;
            }
            if (!board[x][y-1] && !board[x+1][y]) {
                board[x][y] = true; board[x][y-1] = true; board[x+1][y] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y-1] = false; board[x+1][y] = false;
            }
        }
        if (x > 0 && (y+1) < b) {
            if (!board[x-1][y] && !board[x][y+1]) {
                board[x][y] = true; board[x-1][y] = true; board[x][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x-1][y] = false; board[x][y+1] = false;
            }
            if (!board[x][y+1] && !board[x-1][y+1]) {
                board[x][y] = true; board[x][y+1] = true;   board[x-1][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y+1] = false;   board[x-1][y+1] = false;
            }
            if (!board[x-1][y] && !board[x-1][y+1]) {
                board[x][y] = true; board[x-1][y] = true; board[x-1][y+1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x-1][y] = false; board[x-1][y+1] = false;
            }
        }
        if (x > 0 && y > 0) {
            if (!board[x][y-1] && !board[x-1][y]) {
                board[x][y] = true; board[x][y-1] = true;   board[x-1][y] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y-1] = false;   board[x-1][y] = false;
            }
            if (!board[x-1][y] && !board[x-1][y-1]) {
                board[x][y] = true; board[x-1][y] = true;   board[x-1][y-1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x-1][y] = false;   board[x-1][y-1] = false;
            }
            if (!board[x][y-1] && !board[x-1][y-1]) {
                board[x][y] = true; board[x][y-1] = true;   board[x-1][y-1] = true;
                result += canCover(board, a, b);
                board[x][y] = false;    board[x][y-1] = false;   board[x-1][y-1] = false;
            }
        }

        return result;
    }
}
