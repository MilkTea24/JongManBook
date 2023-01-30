package example.ch07.sec03;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class NormalMultiply {
    static Scanner scanner = new Scanner(System.in);
    static void normalize(LinkedList<Integer> num) {
        for (int i = 0; i+1 < num.size(); i++) {
            if (num.get(i) < 0) { //자릿수가 음수인 경우 내림 처리
                int borrow = (-num.get(i) + 9) / 10;
                num.set(i+1, num.get(i+1) - borrow);
                num.set(i, num.get(i) + borrow * 10);
            }
            else { //자릿수가 양수인 경우 올림 처리
                num.set(i+1, num.get(i+1) + num.get(i) / 10); //초과된 숫자는 다음 자릿수로 넘김
                num.set(i, num.get(i) % 10);
            }
        }
        while (num.size() > 1 && num.getLast() == 0) num.removeLast();
    }

    static LinkedList<Integer> multiply(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> c = new LinkedList<>();
        for (int i = 0; i< a.size() + b.size() + 1; i++) {
            c.add(0);
        }

        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                int result = c.get(i+j) + a.get(i) * b.get(j);
                c.set(i + j, result);
            }
        }
        normalize(c);
        return c;
    }

    public static void main(String[] args) {
        int a, b;
        a = scanner.nextInt();
        b = scanner.nextInt();
        LinkedList<Integer> firstNum = new LinkedList<>();
        LinkedList<Integer> secondNum = new LinkedList<>();
        int[] temp = new int[a];
        for (int i = 0; i < a; i++) {
            temp[a-1-i] = scanner.nextInt();
        }
        for (int i = 0; i < a; i++) {
            firstNum.add(temp[i]);
        }
        temp = new int[b];
        for (int i = 0; i < b; i++) {
            temp[b-1-i] = scanner.nextInt();
        }
        for (int i = 0; i < b; i++) {
            secondNum.add(temp[i]);
        }
        LinkedList<Integer> result = multiply(firstNum, secondNum);

        Iterator<Integer> iter = result.descendingIterator();
        while (iter.hasNext()) {
            Integer tempInt = iter.next();
            iter.remove();
            System.out.print(tempInt);
        }
    }
}
