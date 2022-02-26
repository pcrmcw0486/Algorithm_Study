package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_9527
 * @문제이름 : 1의 개수 세기
 * @난이도 : Gold II
 * @date : 2022-02-26 오후 1:18
 * @author : pcrmcw0486
 * @문제이해
 * A<=X<+B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때 1의 개수 합을 구하는 프로그램
 *  f(x) = x 를 이진수를 표현했을 때 1의 개수
 * 규칙이 존재한다.
 * @알고리즘
* dp bitmasking?
 * @접근방법
 * 1의 개수
*/
public class Review_9527 {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        init();
        long A = Long.parseLong(st.nextToken());
       long B = Long.parseLong(st.nextToken());
      long ans = count(B) - count(A - 1);

        System.out.println(ans);
    }
    public static void init(){
        //dp[x] = 2*dp[x-1] + 2^(x-1)
        //주어지는 범위 10^16 ==> log2로 취하면 약 56자리.
        dp = new long[56];
        dp[1] = 1;
        for (int i = 2; i< dp.length; i++) {
            dp[i] = 2 * dp[i - 1] + (long)Math.pow(2, i - 1);
        }
    }
    public static long count(long num){
        int offset = 55;
        long ans =0;
        while(num>0){
            if ((num & (1L << (offset))) != 0) {
                ans += dp[offset] + (num^(1L <<(offset))) + 1 ;
                num ^=(1L <<(offset));
            }
            offset--;
        }
        return ans;
    }
}
