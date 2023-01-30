package example.ch07.sec03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import static example.ch07.sec03.NormalMultiply.multiply;
import static example.ch07.sec03.NormalMultiply.normalize;

public class KaratsubaMultiply {
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\만재\\휴학학기\\Algorithm\\JAVA\\JongManBook\\src\\main\\resources\\karatsuba.txt"));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        LinkedList<Integer> firstNum = new LinkedList<>();
        LinkedList<Integer> secondNum = new LinkedList<>();
        int[] temp = new int[a];
        for (int i = 0; i < a; i++) {
            temp[a-1-i] = reader.read() - '0';
        }
        for (int i = 0; i < a; i++) {
            firstNum.add(temp[i]);
        }
        temp = new int[b];
        reader.read();
        reader.read();
        for (int i = 0; i < b; i++) {
            temp[b-1-i] = reader.read() - '0';
        }
        for (int i = 0; i < b; i++) {
            secondNum.add(temp[i]);
        }
        LinkedList<Integer> result = karatsuba(firstNum, secondNum);

        Iterator<Integer> iter = result.descendingIterator();
        String s = "";
        int ind = (3 - result.size() % 3) % 3;
        while (iter.hasNext()) {
            if (ind == 3) {
                ind = 0;
                System.out.print(",");
                s += ",";
            } else {
                Integer tempInt = iter.next();
                iter.remove();
                System.out.print(tempInt);
                s += tempInt;
                ind++;
            }
        }
    }

    static LinkedList<Integer> karatsuba(LinkedList<Integer> a, LinkedList<Integer> b) {
        int an = a.size(), bn = b.size();
        if (an < bn) return karatsuba(b, a);
        if (an == 0 || bn == 0) return new LinkedList<>();
        if (an <= 30) return multiply(a, b);
        int half = an / 2;

        int minValue;
        if (half > b.size()) minValue = b.size();
        else minValue = half;
        LinkedList<Integer> a0 = new LinkedList<>(a.subList(0, half));
        LinkedList<Integer> a1 = new LinkedList<>(a.subList(half, a.size()));
        LinkedList<Integer> b0 = new LinkedList<>(b.subList(0, minValue));
        LinkedList<Integer> b1 = new LinkedList<>(b.subList(minValue, b.size()));

        LinkedList<Integer> z2 = karatsuba(a1, b1);
        LinkedList<Integer> z0 = karatsuba(a0, b0);
        addTo(a0, a1, 0);   addTo(b0, b1, 0);
        LinkedList<Integer> z1 = karatsuba(a0, b0);
        subFrom(z1, z0);
        subFrom(z1, z2);
        LinkedList<Integer> ret = new LinkedList<>();
        addTo(ret, z0, 0);
        addTo(ret, z1, half);
        addTo(ret, z2, half + half);
        return ret;
    }

    static void addTo(LinkedList<Integer> a, LinkedList<Integer> b, int k) {
        for (int i = 0; i < k; i++) {
            b.addFirst(0);
        }

        int ind = 0;
        while (ind != a.size() && ind != b.size()) {
            a.set(ind, a.get(ind) + b.get(ind));
            ind++;
        }

        while (ind != b.size()) {
            a.addLast(b.get(ind));
            ind++;
        }
        normalize(a);
    }

    static void subFrom(LinkedList<Integer> a, LinkedList<Integer> b) {
        int ind = 0;
        while (ind != b.size()) {
            a.set(ind, a.get(ind) - b.get(ind));
            ind++;
        }
        normalize(a);
    }
}
