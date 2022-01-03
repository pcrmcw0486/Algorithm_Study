package solvedac.level4.gold3;
/*
2021.12.30
문제번호 : Q11444
이름 및 난이도 : 피보나치 수 6
문제이해 
-----------------
기본적인 피보나치 수 제한 조건에 유의하여 작성한다.

접근 방법 :
 n이 너무 커서 linear하게 해가는 방법은 불가능하다고 판단하고
 제곱수가 필요하다고 생각했다.
 제곱 관계식을 도출하기 위해서 점화식을 지지고 볶아봤지만
 찾지 못해 검색을 통해 행렬을 사용하는 방법을 깨달음.
 행렬을 통해 만들어낸 점화식을 이용한다.
 >> 선형대수가 왜필요한지. 행렬이 반복계산에 왜 유용한지 등을
 알게 되었음.

  | 1 1  | ^(n-1)  | 

제한 조건 : 
 n은 10^18 >> long 자료형.
 n 번째 피보나치수를 1_000_000_007로 나눈 나머지.(int형 가능)
*/

import java.io.*;

public class Q11444 {
    static long[][] F;
    final static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        F = new long[][] { { 1, 1 }, { 1, 0 } };
        if (N == 0)
            System.out.println(0);
        else if (N == 1)
            System.out.println(1);
        else {
            long[][] ans;
            ans = solve(N);
            System.out.println((ans[1][0]) % MOD);
        }
    }

    public static long[][] solve(long N) {
        long[][] A = new long[2][2];
        if (N == 1)
            return F;
        A = solve(N / 2);
        A = multiMat(A, A);
        if (N % 2 == 0)
            return A;
        else
            return multiMat(A, F);
    }

    public static long[][] multiMat(long[][] A, long[][] B) {
        long[][] ret = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    ret[i][j] += (A[i][k] * B[k][j]) % MOD;
                }
                ret[i][j] %= MOD;
            }
        }
        return ret;
    }
}
