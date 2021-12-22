package RHS_FC.class09_dp;
/*
2021.10.21
문제번호 : Q1003
이름 및 난이도 : 피보나치 함수 Silver III
문제이해 
-----------------
n == 0 0 
n== 1 1
return f(n-1) + f(n-2)와 같은 함수가 있다고 할 때
f(3) -> f(2) + f(1) -> f(1) f(0) f(1) 이라서 0  >> 1번 1 >> 2번
이라고 할 때 N 호출 시 0과 1이 몇번 출력되는지 구하는 프로그램을 작성하자
dp[i][0] = 0 호출 횟수 dp[i][1] 1호출 횟수라고 하자.
x 호출시 x-1과 x-2가 호출되므로 
dp[x][0] = dp[x-1][0] + dp[x-2][0]
dp[x][1] = dp[x-1][1] + dp[x-2][1]
dp[0][0] = 1 dp[1][1] = 1
dp[2][0] = dp[1][0] + dp[0][0] = 1
dp[2][1] = dp[1][1] + dp[0][1] = 1
dp[3][0] = dp[2][0] dp[1][0] = 1
dp[3][1] = dp[2][1] dp[1][1] = 2///
접근 방법 : Dynamic programming
제한 조건 : 
O(N)
*/
import java.io.*;
import java.util.*;
public class Q1003 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];
        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;
        for(int i =2;i<dp.length;i++) {
            dp[i][0] += dp[i-1][0] + dp[i-2][0];
            dp[i][1] += dp[i-1][1] + dp[i-2][1];
        }
        for(int i =0;i<T;i++){
            int x = Integer.parseInt(br.readLine());
            sb.append(dp[x][0]).append(' ').append(dp[x][1]).append('\n');
        }
        System.out.print(sb);        
    }
}
