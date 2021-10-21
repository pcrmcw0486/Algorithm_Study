package RHS_FC.class09_dp;
/*
2021.10.21
문제번호 : Q15991
이름 및 난이도 : 1,2,3 더하기 6
문제이해 
-----------------
1, 2, 3의 합으로 나타내는 방법이 있을 때 합은 대칭을 이루어야한..다?
dp[i]가 대칭일 때
>> dp[0] = 1;
>> 1 dp[i-2] 1 i>2
>> 2 dp[i-4] 2 i>4
>> 3 dp[i-6] 3 도 대칭이 보장된다. dp[x]가 대칭이므로. if(i>6)
dp[1] = 1
dp[2] = 1

2 >  1 1  2 
3 > 1 1 1 3
4 > 2 2 // 1 1 1 1 // 1 2 1
5> 2 1 2 // 1 1 1 1 1

접근 방법 :
제한 조건 : 
 N~100_000
 방법의수 %1000000009
 3개 더하면 30억 넘어가니까 int형 안됨.
*/

import java.io.*;

public class Q15991 {
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        long[] dp = new long[100001];
        dp[0] = 1; dp[1] = 1;
        dp[2] = 2; dp[3] = 2;
        dp[4] = dp[2] + dp[0];
        dp[5] = dp[1] + dp[3];
        for(int i = 6;i<100001;i++){
            dp[i] = (dp[i-2] + dp[i-4] + dp[i-6])%1000000009;
        }
         
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i =0;i<N;i++){
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }
        System.out.print(sb);
        


    }
}
