package algospot.boggle;

import java.util.Scanner;

public class Boggle {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cases; i++) {
            boggle();
        }
    }

    static void boggle() {
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String inputLine = scanner.nextLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = inputLine.charAt(j);
            }
        }
        int cases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < cases; i++) {
            String testString = scanner.nextLine();
            String result = isExistAtBoard(testString, board);
            System.out.println(testString + " " + result);
        }
    }

    static String isExistAtBoard(String target, char[][] board) {
        int result = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (target.charAt(0) == board[i][j]) {
                    result = DFS(target, board, i, j, 1);
                    if (result == 1) return "YES";
                }
            }
        }

        return "NO";
    }

    static int DFS(String target, char[][] board, int i, int j, int index){
        if (target.length() == index) return 1;
        int result = 0;
        if (i >= 1 && board[i-1][j] == target.charAt(index)) result = DFS(target, board, i-1, j, index + 1);
        if (i >= 1 && j >= 1 && board[i-1][j-1] == target.charAt(index)) result = DFS(target, board, i-1, j-1, index + 1);
        if (j >= 1 && board[i][j-1] == target.charAt(index)) result = DFS(target, board, i, j-1, index + 1);
        if (i <= 3 && j >= 1 && board[i+1][j-1] == target.charAt(index)) result = DFS(target, board, i+1, j-1, index + 1);
        if (i <= 3 && board[i+1][j] == target.charAt(index)) result = DFS(target, board, i+1, j, index + 1);
        if (i <= 3 && j <= 3 && board[i+1][j+1] == target.charAt(index)) result = DFS(target, board, i+1, j+1, index + 1);
        if (j <= 3 && board[i][j+1] == target.charAt(index)) result = DFS(target, board, i, j+1, index + 1);
        if (i >= 1 && j <= 3 && board[i-1][j+1] == target.charAt(index)) result = DFS(target, board, i-1, j+1, index + 1);
        return result;
    }
}
