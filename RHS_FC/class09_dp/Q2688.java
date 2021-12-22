package RHS_FC.class09_dp;
/*
2021.10.22
문제번호 : Q2688
이름 및 난이도 : 줄어들지 않아 Gold V
문제이해 
-----------------
어떤 숫자가 줄어들지 않는다는 것은 그 숫자의 각 자리 수보다 그 왼쪽 자리수가 작거나 같을 때
data[i] >= data[i-1] 일때 줄어들지 않는다.
자리수가 input으로 들어옴.
접근 방법 :
제한 조건 :  
 Test case <=1000
  1<= n <= 64
*/

import java.io.*;
import java.util.*;

public class Q2688 {
    public static void main(String[] args) throws IOException {
        long[][] dp = new long[67][10];
        Arrays.fill(dp[1], 1);
        long sum = 10;
        long nxtSum = 0;
        for (int i = 2; i < 67; i++) {
            nxtSum = 0;
            for (int j = 0; j < 10; j++) {
                dp[i][j] = sum;
                nxtSum += sum;
                sum -= dp[i - 1][j];
            }
            sum = nxtSum;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int target = Integer.parseInt(br.readLine());
            sb.append(dp[target + 1][0]).append('\n');
        }
        System.out.print(sb);
    }
}
