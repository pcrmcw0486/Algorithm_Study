package practiceTest.kakaent;

import java.util.StringTokenizer;

import java.util.*;

public class test3 {
    public static void main(String[] args) {
        long a = Long.parseLong("000001101011100010111100010101100111", 2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(4);
        list.add(3);
        list.add(1);
        Collections.sort(list);
        for (int b : list)
            System.out.println(b);

        System.out.println(a);
    }
}
