package example.ch08.sec03;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class WildCardMemoized {
    static Scanner scanner = new Scanner(System.in);
    static int[][] cache = new int[101][101]; //-1이면 아직 답이 계산되지 않음, 0이면 false, 1이면 true
    static String W, S;
    static int ret;
    public static void main(String[] args) {

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        LinkedList<String> strings = new LinkedList<>();

        W = scanner.nextLine();
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCases; i++) {
            S = scanner.nextLine();
            for (int j = 0; j < W.length(); j++) {
                for (int k = 0; k < S.length(); k++) {
                    cache[j][k] = -1;
                }
            }
            if (matchesWithWildCard(0, 0)) {
                strings.add(S);
            }
        }
        Collections.sort(strings);

        for (String s : strings) {
            System.out.println(s);
        }

    }

    static boolean matchesWithWildCard(int w, int s) {
        ret = cache[w][s];
        if (ret != -1) return (ret == 1);
        while (s < S.length() && w < W.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            ++w;
            ++s;
        }
        if (w == W.length()) {
            ret = (s == S.length()) ? 1 : 0;
            return (s == S.length());
        }
        if (W.charAt(w) == '*') {
            for (int skip = 0; skip + s <= S.length(); ++skip) {
                if (matchesWithWildCard(w+1, s + skip)) {
                    ret = 1;
                    return true;
                }
            }
        }

        return false;
    }
}
