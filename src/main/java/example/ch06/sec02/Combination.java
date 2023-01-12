package example.ch06.sec02;

import java.util.Iterator;
import java.util.LinkedList;

public class Combination {
    public static void main(String[] args) {
        pick(6, new LinkedList<Integer>(), 4); //6C4
    }

    public static void pick(int n, LinkedList<Integer> picked, int toPick) {
        if (toPick == 0) {
            printpicked(picked);
            return;
        } //더이상 고를 게 없으면 출력

        int smallest = picked.isEmpty() ? 0 : picked.getLast() + 1;

        for (int i = smallest; i < n; i++) {
            picked.addLast(i);
            pick(n, picked, toPick - 1);
            picked.removeLast();
        }
    }

    public static void printpicked(LinkedList<Integer> picked) {
        Iterator<Integer> pickedIterator = picked.iterator();
        while (pickedIterator.hasNext()) {
            int pickedOne = pickedIterator.next();
            System.out.print(pickedOne + " ");
        }
        System.out.println();
    }
}
