package algospot.wildcard;

import java.util.*;

public class Main {
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
        if (wildCard.equals(testString)) return true;
        boolean result = false;
        int i = 0;
        int wildCardIndex = i;
        try {
            while (wildCard.charAt(i) == testString.charAt(i)) {
                i++;
            }
            if (wildCard.charAt(i) == '?') {
                result = matchesWithWildCard(wildCard.substring(i + 1), testString.substring(i + 1));
            } else if (wildCard.charAt(i) == '*') {
                int testStringIndex = i;
                while (i + 1 < wildCard.length() && wildCard.charAt(i + 1) == '*') i++;
                for (int j = testStringIndex; j < testString.length(); j++) {
                    if (wildCard.charAt(i + 1) == testString.charAt(j)) {
                        result = matchesWithWildCard(wildCard.substring(i + 1), testString.substring(j));
                        if (result) break;
                    }
                }
            }
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            if (i < wildCard.length() && wildCard.charAt(i) == '*') return true;
            return false;
        }
    }
}
