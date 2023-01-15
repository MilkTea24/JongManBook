package algospot.picnic;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static int[][] friend;
    public static Scanner scanner = new Scanner(System.in);
    public static int result = 0;
    public static void main(String[] args) {
        int caseNumber = Integer.parseInt(scanner.next());
        for (int i = 0; i < caseNumber; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        result = 0;
        int studentNumber = Integer.parseInt(scanner.next());
        int mateNumber = Integer.parseInt(scanner.next());
        friend = new int[studentNumber][studentNumber];

        for (int i = 0; i < mateNumber; i++) {
            int first = Integer.parseInt(scanner.next());
            int second = Integer.parseInt(scanner.next());
            friend[second][first] = 1;
            friend[first][second] = 1;
        }


        LinkedList<Integer> remainStudent = new LinkedList<>();
        for (int i = 0; i < studentNumber; i++) {
            remainStudent.add(i);
        }
        System.out.println(makeMate(new LinkedList<>(), remainStudent, studentNumber));
    }

    public static int makeMate(LinkedList<Integer> mateList, LinkedList<Integer> remainStudent, int studentNumber) {
        int ret = 0;
        if (remainStudent.size() == 0) {
            System.out.println("mateList: " + mateList);
            return 1;
        }


        int wantMate = remainStudent.getFirst();
        for (int i = 0; i < studentNumber; i++) {
            if (friend[wantMate][i] == 1 && remainStudent.contains(i)) {
                mateList.add(wantMate);
                mateList.add(i);
                remainStudent.remove((Integer)wantMate);
                remainStudent.remove((Integer)i);
                ret += makeMate(mateList, remainStudent, studentNumber);
                mateList.remove((Integer)wantMate);
                mateList.remove((Integer)i);
                remainStudent.add(wantMate);
                remainStudent.add(i);

            }
        }

        return ret;
    }
}
