package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q10844
이름 및 난이도 : 쉬운 계단수 Silver I
문제이해 
-----------------
인접한 모든 자리 차이가 1이면 계단수
시작 숫자 k 길이 len에 대하여
SolveByCategory.dp[k][len] = SolveByCategory.dp[k-1][len-1] + SolveByCategory.dp[k+1][len-1] 이라면
각각의 SolveByCategory.dp[k][len]이 k로 시작하는 길이 len인 계단 수의 개수 이기 때문에 보장됨.
SolveByCategory.dp[len][숫자]
접근 방법 :
제한 조건 : 
1<= N <= 100
*/

import java.io.*;
import java.util.*;
public class Q10844 {
    public static void main(String[] args) throws IOException{
        int[][] dp = new int[101][10];
        Arrays.fill(dp[1], 1);
        for(int i =2;i<101;i++){
            for(int j = 1;j<9;j++){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%1000000000;
            }
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum =0;
        for(int i =1;i<10;i++){
            sum = (sum + dp[N][i])%1000000000;
        }
        System.out.println(sum);
    }
}
