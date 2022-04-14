package programmers.kakao.Blind2022;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author : pcrmcw0486
 * @문제번호 : Solution
 * @문제이름 :
 * @난이도 :
 * @date : 2022-04-08 오후 5:29
 * @문제이해
 * 26분
 * @알고리즘
 * @접근방법 양의 정수 n이 주어질 때 이 숫자를 k진수로 바꿀 때
 * 변환된 수 안에 아래 조건에 맞는 소수가 몇개인가
 * 0P0처럼 소수 양쪽에 0이 있는 경우
 * P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
 * P처럼 소수 양쪽에 아무것도 없는 경우
 * P는 각 자릿수에 0을 포함하지 않는 소수
 */
public class Solution {
    public static void main(String[] args) {
        int n = 3;
        int k = 6;
        //int solution = solution(n, k);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= 100000; i++) {
//            for (int j = 3; j <= 10; j++) {
//                solution(i, j);
//            }
//        }
        System.out.println(solution(437674,3));
//        System.out.println("solution = " + solution);
    }

    public static int solution(int n, int k) {
        int ans = 0;
        String changedNotation = changeNotation(n, k);
        System.out.println(changedNotation);
        String[] partition = changedNotation.split("0+");
        HashSet<Long> isPrime = new HashSet<>();
        for (String value : partition) {
            long partitionedValue = Long.parseLong(value);
            if (isPrime.contains(partitionedValue)) {
                ans++;
            } else {
                if (checkPrime(partitionedValue)) {
                    isPrime.add(partitionedValue);
                    ans++;
                }
            }
        }
        return ans;
    }

    private static boolean checkPrime(long partitionedValue) {
        for (long i = 2; i <= Math.sqrt(partitionedValue); i++) {
            if(partitionedValue%i == 0)
                return false;
        }
        return true;
    }

    private static boolean[] initPrime(int MAX_SIZE) {
        boolean[] isPrime = new boolean[MAX_SIZE + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= Math.sqrt(MAX_SIZE); i++) {
            if (isPrime[i]) {
                for (int j = i + i; j <= MAX_SIZE; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static String changeNotation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % k);
            n = n / k;
        }
        return sb.reverse().toString();
    }
}
