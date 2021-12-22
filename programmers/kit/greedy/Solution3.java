package programmers.kit.greedy;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        String number = "141111";
        int k = 3;
        System.out.println(solution(number, k));
    }

    public static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < number.length(); i++) {
            list.add(number.charAt(i) - '0');
        }
        int startIndex = 0;
        boolean goNext = true;
        while (k > 0 && startIndex < list.size()) {
            goNext = true;

            int base = list.get(startIndex);
            for (int i = 1; i < k + 1 && startIndex + i < list.size(); i++) {
                if (base < list.get(startIndex + i)) {
                    for (int j = 0; j < i; j++) {
                        list.remove(startIndex);
                    }
                    k -= i;
                    goNext = false;
                    break;
                }
            }
            // 자신보다 큰게 없다.
            if (goNext) {
                startIndex++;
            }

            System.out.println("startIndex: " + startIndex + "K " + k);
            for (int s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < k; i++) {
            list.remove(list.size() - 1);
        }
        for (int s : list) {
            sb.append(s);
        }

        return sb.toString();
    }
}
