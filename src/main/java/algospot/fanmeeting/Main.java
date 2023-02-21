package algospot.fanmeeting;

import java.util.LinkedList;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int cases = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cases; i++) {
            oneCase();
        }
    }

    static void oneCase() {
        String members = scanner.nextLine();
        String fans = scanner.nextLine();

        LinkedList<Integer> maleMembersIndex = new LinkedList<>();
        TreeSet<Integer> maleFansIndex = new TreeSet<>();
        int answerSize = fans.length() - members.length();
        boolean[] notAnswerArray = new boolean[answerSize];

        for (int i = 0; i < fans.length(); i++) {
            if (i < members.length() && members.charAt(i) == 'M') maleMembersIndex.add(i);
            if (fans.charAt(i) == 'M') maleFansIndex.add(i);
        }

        for (int i = 0; i < maleMembersIndex.size(); i++) {
            int startNum = maleMembersIndex.get(i);
            NavigableSet<Integer> subset = maleFansIndex.subSet(startNum, true, startNum + answerSize, true);
            for (int j : subset) {
                notAnswerArray[j - startNum] = true;
            }
        }
        int answer = 0;
        for (int i = 0; i < notAnswerArray.length; i++) {
            if (!notAnswerArray[i]) answer++;
        }
        System.out.println(answer + 1);
    }
}