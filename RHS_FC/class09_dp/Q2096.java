package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q2096
이름 및 난이도 : 내려가기 Gold IV
문제이해 
-----------------
N줄에 0이상 9이하의 숫자가 세개씩 적혀있다.
첫줄에서 시작해서 마지막줄에서 끝나게 된다.
처음에 적혀있는 세 개의 숫자 중에서 하나를 골라 시작한다.
다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있ㄷ.
바로 아래의 수로 넘어가거나, 아래의 수와 붙어있는 수로만 이동한다.
얻을 수 있는 최대 최소 점수를 구하는 프로그램
접근 방법 :
제한 조건 :
Sliding window로 푸는 좋은 방법이 있따. 
*/

import java.io.*;
import java.util.*;
public class Q2096 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N+1][3][2];
        
        for(int i =1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); //0
            int b = Integer.parseInt(st.nextToken()); //1 
            int c = Integer.parseInt(st.nextToken()); // 2
            dp[i][0][0] = a + Math.max(dp[i-1][0][0], dp[i-1][1][0]);
            dp[i][0][1] = a + Math.min(dp[i-1][0][1], dp[i-1][1][1]);
            dp[i][1][0] = b + Math.max(dp[i-1][0][0], Math.max(dp[i-1][1][0], dp[i-1][2][0]));
            dp[i][1][1] = b + Math.min(dp[i-1][0][1], Math.min(dp[i-1][1][1], dp[i-1][2][1]));
            dp[i][2][0] = c + Math.max(dp[i-1][1][0], dp[i-1][2][0]);
            dp[i][2][1] = c + Math.min(dp[i-1][1][1], dp[i-1][2][1] );
        }
        int max= Math.max(dp[N][0][0], Math.max(dp[N][1][0], dp[N][2][0]));
        int min= Math.min(dp[N][0][1], Math.min(dp[N][1][1], dp[N][2][1]));
        System.out.println(max + " " + min );

    }
}
