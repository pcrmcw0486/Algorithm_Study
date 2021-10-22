package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q2193
이름 및 난이도 : 이친수 Silver III 
문제이해 
-----------------
0과 1로만 이루어진 이진수
이친수는 0으로 시작하지 않는다. 
1이 두번 연속 나타나지 않는다.
N이 주어질 때 N자리 이친수 개수를 구하는 프로그램;
d[N] = N자리 까지의 이친수 개수?
ㄴㄴ 이친수의 조건을 생각해보면
xxxxxxx0
xxxxxxx1 로 끝나게됨.
xxxxxxx0 이후에는 1 또는 0이 올 수 있지만
xxxxxxx1 이후에는 0밖에 오지 못함.
구분하지 않으면 어려움.
점화식을 세우면
0으로 끝난다 : dp[i][0] = dp[i][1] + dp[i][0]
1으로 끝난다 : dp[i][1] = dp[i][0]
00
01
10
기저조건
dp[i][0] = 0;
dp[i][1] = 1;
접근 방법 :
제한 조건 : 

i = 1 (0)1
i = 2  10
i = 3  1 00 1 01
i = 4 1000 1010 1001  > 10 + { (10(i==2)) 00, 01(i==3))
i = 5 10 + {100, 101(i==3), 000, 010, 001(i==4)}
dp[i] = d[i-2] + d[i-1]

0으로 끝날때 1로 끝날때로도 가능
dp[1][0] = 0; dp[1][1] = 1;
dp[2][0] = 1; dp[2][1] = 0;
dp[3][0] = dp[3-1][0] + dp[i-1][1]; // 0
dp[3][1] = dp[3-2][0] + dp[i-2][1]; // 1
dp[4][0] = dp[3][0] + dp[3][1]; // 10 + >> 01
dp[4][1] = dp[2][0] + dp[2][1]; // 10 + >>10 00 
dp[5][0] = dp[4][0] + dp[4][1]; // 10 + >> 000 010 001
dp[5][1] = dp[3][0] + dp[3][1]; // 10 + >> 100 101 
*/

import java.io.*;

public class Q2193 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1)
            System.out.println(1);
        else if (N == 2)
            System.out.println(1);
        else {
            long prev0 = 1;
            long prev1 = 0;
            for (int i = 3; i < N + 1; i++) {
                long cur0 = prev0 + prev1;
                long cur1 = prev0;
                prev0 = cur0;
                prev1 = cur1;
            }

            System.out.println(prev0 + prev1);
        }
    }
}
