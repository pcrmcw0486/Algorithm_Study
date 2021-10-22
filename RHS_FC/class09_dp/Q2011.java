package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q2011
이름 및 난이도 : 암호코드 Silver I
문제이해 
-----------------
대화 암호화 숫자화
ex) BEAN -> 2/ 5/1/14 > 25114
    25114 > BEAAD/ YAAD/ YAN / YKD/ BEKD / BEAN 
    몇개가 나올 수 있는가.
접근 방법 : 
 일단 앞자리가 1 또는 2일 때 앞으로 한번 확인할 수 있고
 0의 경우 10/ 20 말고는 안됨. (304050XX) 0은 한자리로는 안된다.
큰 흐름으로 볼때
D[i][0] : 현재  한자리로 가능하다.
D[i][1] : i, i-1로 연결하여 두자리로 가능하다.
- 점화식
 D[i][0] = D[i-1][0] + D[i-1][1]; (조건 한자리가 0이면 안됨)
 D[i][1] = if(조건 : 두자리가 26 안인가.) D[i-2][0] + D[i-2][1]
 if(D[i-1]이 3이상 또는 0이면 패스)

제한 조건 : 2초 N<=5000 
암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.
나누어 출력.
*/

import java.io.*;

public class Q2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] data = new int[s.length() + 1];
        int[][] dp = new int[data.length][2];
        for (int i = 1; i < data.length; i++)
            data[i] = s.charAt(i - 1) - '0';
        if (data[1] == 0) {
            System.out.println(0);
            return;
        }
        dp[1][0] = 1;
        dp[1][1] = 0;

        if (dp.length >= 3) {
            if (data[2] != 0) {
                dp[2][0] = 1;
            }
            // D[2][1] 두자리가 가능한가. 앞자리는 1 또는 2여야함.
            if (data[1] != 0 && 10 * data[1] + data[2] < 27) {
                dp[2][1] = 1;
            }
        }
        for (int i = 3; i < data.length; i++) {
            // D[i][0] 확인 한자리가 가능한가.
            if (data[i] != 0) {
                dp[i][0] += (dp[i - 1][0] + dp[i - 1][1]) % 1000000;
            }
            // D[i][1] 두자리가 가능한가. 앞자리는 1 또는 2여야함.
            if (data[i - 1] != 0 && 10 * data[i - 1] + data[i] < 27) {
                dp[i][1] = (dp[i - 2][0] + dp[i - 2][1]) % 1000000;
            }
            if (dp[i][0] == 0 && dp[i][1] == 0)
                break;
        }
        System.out.println((dp[dp.length - 1][0] + dp[dp.length - 1][1]) % 1000000);
    }

}
