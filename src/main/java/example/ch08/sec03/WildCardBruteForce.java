package example.ch08.sec03;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class WildCardBruteForce {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            oneCase();
        }






    }

    static void oneCase() {
        LinkedList<String> strings = new LinkedList<>();

        String wildCard = scanner.nextLine();
        int testCases = Integer.parseInt(scanner.nextLine());


        for (int i = 0; i < testCases; i++) {
            String testString = scanner.nextLine();
            if (matchesWithWildCard(wildCard, testString)) {
                strings.add(testString);
            }
        }
        Collections.sort(strings);

        for (String s : strings) {
            System.out.println(s);
        }

    }

    static boolean matchesWithWildCard(String wildCard, String testString) {
        int pos = 0;
        while (pos < wildCard.length() && pos < testString.length() && (wildCard.charAt(pos) == '?' || wildCard.charAt(pos) == testString.charAt(pos))) ++pos;
        if (pos == wildCard.length()) return (pos == testString.length());
        if (wildCard.charAt(pos) == '*') {
            for (int skip = 0; pos + skip <= testString.length(); ++skip) {
                if (matchesWithWildCard(wildCard.substring(pos + 1), testString.substring(pos + skip))) return true;
            }
        }
        return false;
    }
}
