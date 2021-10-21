package RHS_FC.class09_dp;

/*
11726
2*n 타일링 Silver III
2*n 크기의 직사각형을 1*2, 2*1 타일로 채우는 방법의 수를 구하시오
n<=1000

2*1 = 1
2*2 = (1*2) 2개  + (2*1) 2개 1+1
2*3 = (2*1) * (2*2) 방법 (2*2) *(2*1)방법 모양은 다르니까
짝 홀 밖에 없네
2*4 면? (2*3) * 1 // (2*2) (2*2) 
라고 생각했으나
| |, = 2가지 모양으로 채워질때 dp[i-2]상태에서 ||의 모양은 dp[i-1] 모양과 동일함.
*2가 아니라 +1

이제는 계산 문제
채우는 방법의 수를 10,007로 나눈 나머지
쉽게 1의 자리만 한다 치면 10으로 나눈 나머지라는 뜻이고
1의 자리로 나눈 값들에서 더하고 나눠도 1의 자리는 보장이됨.
(a%b) + (c%b) = (a+c)%b 분배법칙 적용 .
if X > 10007 
dp[i] : 2*i 까지 채웠을 때 방법의 수 
dp[i] = dp[i-1] + dp[i-2] (i>3)
 */
import java.io.*;

public class Q11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[1001]; // n이 1000까지
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < N + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
        System.out.println(dp[N]);
    }
}
