package programmers.kakao.test;

import java.util.stream.*;
import java.util.*;

public class Solution2 {
    static StringBuilder sb;

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        boolean success = true;
        sb = new StringBuilder();
        // for (int i = 0; i < 1000000; i++) {

        // for (int j = 3; j < 11; j++) {
        // sb = new StringBuilder();
        // try {

        // solution.solution(i, j);
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // System.out.println(i + " " + j);
        // success = false;
        // break;
        // }
        // }
        // if (success == false)
        // break;
        // }
        sb = new StringBuilder();
        int a = solution.solution(55768, 3);
        System.out.println(a);
    }

    public int solution(int n, int k) {
        int answer = 0;
        divide(n, k);
        String result = sb.toString();
        result = result.replaceAll("0+", "0");
        long[] checkList = Arrays.stream(result.split("0")).mapToLong(Long::parseLong).toArray();

        for (int i = 0; i < checkList.length; i++) {
            if (checkPrime(checkList[i])) {
                answer++;
            }
        }
        return answer;
    }

    public boolean checkPrime(long a) {
        if (a == 1)
            return false;
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0)
                return false;
        }
        return true;
    }

    public void divide(int n, int k) {
        if (n < k) {
            sb.append(n);
            return;
        }
        int cur = n % k;
        divide(n / k, k);
        sb.append(cur);
    }
}
