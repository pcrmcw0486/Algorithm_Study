package solvedac.level5.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q1509
 * @문제이름 : 팰린드롬 분할
 * @난이도 : Gold I
 * @date : 2022-02-27 오후 2:55
 * @문제이해 어떤 문자열을 팰린드롬으로 분할하려고 한다.
 * ABACABA 를 분할하면 ABACABA {A, BACAB, A}, {ABA, C, ABA}, {A,B,A,C,A,B,A}
 * 분할 개수의 최소값을 찾으시오.
 * 주어지는 문자열을 모두 대문자이고, 최대 길이는 2500이다.
 * @알고리즘 dp
 * @접근방법
 * dp[x] 를 x까지의 팰린드롬 분할 최소 횟수라고 하자.
 * 어떠한 x+1을 포함한 최장 팰린드롬을 이루는 idx i라고 한다면
 * dp[i-1] + 1 과 현재 값 +1을 비교하여 더 작은 값이 된다.
 */
public class Q1509 {
    static char[] data;
    static int[] dp;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine().toCharArray();
        dp = new int[data.length ];
        check = new boolean[data.length][data.length];
        dp[0] =1;
        for (int i = 0; i < data.length; i++) {
            for (int j = i; j < data.length;j++) {
                checkPalindrome(i, j);
            }
        }
        for (int i = 1; i < data.length; i++) {
            dp[i] = dp[i-1] +1;
            for (int j = 0; j <= i;j++) {
                if (check[j][i]) {
                    if (j == 0) {
                        dp[i] = 1;
                        break;
                    }else{
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        System.out.println(dp[dp.length-1]);
    }

    //중복으로 확인하는 실수를 저질렀다. 또
    public static boolean checkPalindrome(int start, int end) {
        if(start>=end) return true;
        if(check[start][end]) return true;
        if(data[start] != data[end]) {
            check[start][end] = false;
        }
        else if (data[start] == data[end]) {
            check[start][end] = checkPalindrome(start + 1, end - 1);
        }
        return check[start][end];
    }
}
