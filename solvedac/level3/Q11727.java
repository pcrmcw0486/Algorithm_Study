package solvedac.level3;
/*
2021.11.05
문제번호 : Q11727
이름 및 난이도 : 2*N 타일링 2
문제이해 
-----------------
SolveByCategory.dp
2*N 직사각형을 1*2,2*1 2*2 타일로 채운다.
SolveByCategory.dp[i] i까지 채울 수 있는 최대 개수.
d[i]의 상태 > SolveByCategory.dp[i-1] + 1// SolveByCategory.dp[i-1] +1 한칸늘기전 상태에서 2*1
                            SolveByCategory.dp[i-2] 두칸 늘기 전에는 방법이 두가지.
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q11727 {
    public static void main(String[] args) throws IOException {
        int[] dp = new int[1001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3; // 2*1(2개써서 하나) , 1*2(2개써서 하나) 2*2하나.
        for (int i = 3; i < 1001; i++) {
            dp[i] = (2 * dp[i - 2] + dp[i - 1]) % 10007;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(dp[N]);
    }
}
