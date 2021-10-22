package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q11052
이름 및 난이도 : 카드 구매하기 Silver I
문제이해 
-----------------
카드는 카드팩 형태로
카드( 전설, 레드, 오렌지, 퍼플, 블루, 청록, 그린, 그레이)
카드는 1~N까지 개수에 따라 판매하는 카드팩 N가지 존재
카드 개수가 적은 팩이더라도 비싸면 높은 등급의 카드가 많을 것이라고 믿음
 > 돈을 최대한 많이 지불해서 카드 N개를 구매하려고함
 카드가 i개 포함된 카드팩의 가격은 Pi
 ex) 순서대로 1(1) 5(2) 6(3) 7(4) 일 때 카드를 4개 갖고자할 때 지불할 금액의 최댓값.
  2카드 두개 10원.
   3(1) 5(2) 15(3) 16(4) 
 dp[i]  i개 살 때 최댓값이라고 하자.
 dp[i] = Math.max(card[i], )
 N번 동안 N/2번 봐야하는데..
 되긴 되겟지만..
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;
public class Q11052 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] data = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            data[i] = Integer.parseInt(st.nextToken());
        }
        //dp[0] = data[0];
        for(int i = 1;i<N+1;i++){
            dp[i] = data[i];
            for(int j = 1;j<i+1;j++){
                dp[i] = Math.max(dp[i],dp[j] + dp[i-j]);
            }
        }
        System.out.println(dp[N]);
    }
}
