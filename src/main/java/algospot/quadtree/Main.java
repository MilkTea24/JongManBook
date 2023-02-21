package algospot.quadtree;

import java.util.Scanner;

public class Main {
    static int maxDepth = 0;
    static int sizeOfImage;
    static Boolean[][] image;
    static Boolean[][] flipImage;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            oneCase();
        }
    }

    public static void oneCase() {
        String s = scanner.nextLine();
        if (s.length() == 1) {
            System.out.println(s);
            return;
        }
        findPiece(s);
        sizeOfImage = 1 << maxDepth;
        image = new Boolean[sizeOfImage][sizeOfImage];
        flipImage = new Boolean[sizeOfImage][sizeOfImage];
        for (int i = 0; i < sizeOfImage; i++) {
            for (int j = 0; j < sizeOfImage; j++) {
                image[i][j] = false;
            }
        }
        drawImage(s);

        for(int i = 0; i < sizeOfImage; i++) {
            for (int j = 0; j < sizeOfImage; j++) {
                flipImage[sizeOfImage-i-1][j] = image[i][j];
            }
        }
        String ret = compressImage(1,0,0);
        System.out.println(ret);
    }

    public static int findPiece(String s) {
        int ind = 0;
        return findPiece(s, 1, 1);
    }

    public static int findPiece(String s, int start, int depth) {
        int flag = 0;
        int ind = start;
        if (depth > maxDepth) maxDepth = depth;
        while (flag <= 3) {
            if (s.charAt(ind) == 'x') {
                ind = findPiece(s, ind + 1, ++depth);
                depth--;
                flag++;
            }
            else {
                flag++;
                ind++;
            }
        }
        return ind;
    }

    public static void drawImage(String s) {
        int ind = 0;
        if (s == "w") image[0][0] = false;
        else if (s == "b") image[0][0] = true;
        else drawImage(s, 1, 1, 0, 0);
    }

    public static int drawImage(String s, int start, int depth, int startX, int startY) {
        int flag = 0;
        int ind = start;

        int pieceLen = sizeOfImage >> depth;
        int pieceX = 0, pieceY = 0;

        while (flag <= 3) {
            switch (flag) {
                case 0 :
                    pieceX = startX;
                    pieceY = startY;
                    break;
                case 1 :
                    pieceX = startX + pieceLen;
                    pieceY = startY;
                    break;
                case 2 :
                    pieceX = startX;
                    pieceY = startY + pieceLen;
                    break;
                case 3 :
                    pieceX = startX + pieceLen;
                    pieceY = startY + pieceLen;
                    break;
            }
            if (s.charAt(ind) == 'x') {
                ind = drawImage(s, ind+1, ++depth, pieceX, pieceY);
                depth--;
            }
            else if (s.charAt(ind) == 'b') {
                for (int i = pieceX; i < pieceX + pieceLen; i++) {
                    for (int j = pieceY; j < pieceY + pieceLen; j++) {
                        image[j][i] = true; //i, j 바꿔서 함, for문에 pieceX, pieceY를 넣어버림
                    }
                }
                ind++;
            }
            else ind++;
            flag++;
        }
        return ind++;
    }

    public static String compressImage(int depth, int startX, int startY) {
        int flag = 0;
        String ret = "";
        int pieceLen = sizeOfImage >> depth;
        if (pieceLen < 1) {
            if (flipImage[startY][startX] == true) return "b";
            else return "w";
        }

        int pieceX = 0;
        int pieceY = 0;

        while (flag <= 3) {
            switch (flag) {
                case 0 :
                    pieceX = startX;
                    pieceY = startY;
                    break;
                case 1 :
                    pieceX = startX + pieceLen;
                    pieceY = startY;
                    break;
                case 2 :
                    pieceX = startX;
                    pieceY = startY + pieceLen;
                    break;
                case 3 :
                    pieceX = startX + pieceLen;
                    pieceY = startY + pieceLen;
                    break;
            }
            ret += compressImage(++depth, pieceX, pieceY);
            depth--;
            flag++;
        }
        if (ret.equals("bbbb")) return "b";         
        else if (ret.equals("wwww")) return "w";
        else {
            return "x" + ret;
        }
    }

}
