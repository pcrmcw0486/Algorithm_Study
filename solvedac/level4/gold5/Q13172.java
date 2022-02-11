package solvedac.level4.gold5;
/*
2021.12.16
문제번호 : Q13172
이름 및 난이도 : 시그마 Gold V
문제이해 
-----------------
삼면체 주사위. 나올 확률은 모두 1/3(1,2,4) 기댓값 7/3
확장하여 N면체 주사위 기댓값?
2.3333333 -> 기약분수 분모분자 모두 출력했다.
M개의 주사위 i번째 주사위 Ni면체 주사위이고, 모든 면에 적힌수를 더한 값이 Si
E(X + Y) = E(X) + E(Y)
모든 분수를 통분한다고 생각해보자. 매우크다. 
분수를 모듈러상에서 하나의 정수로 가지고 있는 방법을 채택.
a/b 라면 a* b^-1 mod(X) X는 소수.
b^-1  * b = 1(mod(X))

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q13172 {
    static int N, S;
    final static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());
        long ans = 0;
        for (int dice = 0; dice < M; dice++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            int gcd = GCD(S, N);
            S /= gcd;
            N /= gcd;
            if (S % N == 0)
                ans += S / N;
            else {
                long inverseB = findInverse(MOD - 2);
                ans += (S * inverseB) % MOD;
            }
        }
        ans %= MOD;
        System.out.println(ans);
    }

    public static long findInverse(int n) {
        if (n == 1)
            return N;
        long ret = findInverse(n / 2) % MOD;
        ret = (ret * ret) % MOD;
        if (n % 2 == 0) {
            return ret;
        } else
            return (ret * N) % MOD;
    }

    public static int GCD(int A, int B) {
        int r = 1;
        while (B != 0) {
            r = A % B;
            A = B;
            B = r;
        }
        return A;
    }
}
