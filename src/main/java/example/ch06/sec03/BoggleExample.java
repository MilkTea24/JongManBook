package example.ch06.sec03;

import java.util.Scanner;

public class BoggleExample {
    static final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
    static final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};
    static char[][] board = new char[5][5];
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            oneCase();
        }
    }

    static boolean hasWord(int y, int x, String word) {
        if(!inRange(y, x)) return false; //범위 안인지 아닌지는 기저사례에서 처리 -> 내가 작성한 코드보다 간결한 코드
        if(board[y][x] != word.charAt(0)) return false;
        if(word.length() == 1) return true;
        for (int direction = 0; direction < 8; ++direction) {
            int nextY = y + dy[direction], nextX = x + dx[direction];
            if(hasWord(nextY, nextX, word.substring(1)))
                return true;
        }
        return false;
    }

    static boolean inRange(int y, int x) {
        if (y >= 0 && y < 5 && x >= 0 && x < 5) return true;
        else return false;
    }

    static void oneCase() throws Exception {
        for (int i = 0; i < 5; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int words = Integer.parseInt(scanner.nextLine());
        for (int n = 0; n < words; n++) {
            boolean result = false;
            String word = scanner.nextLine();
            Label: for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (hasWord(i, j, word)) {
                        System.out.println(word + " YES");
                        result = true;
                        break Label;
                    }
                }
            }

            if (!result) System.out.println(word + " NO");
        }
    }


}
