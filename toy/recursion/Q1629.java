/*단계별로 풀어보기 - D&C
곱셈(Silver 1) */
package toy.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1629 {
    static int A;
    static int B;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arg = br.readLine().split(" ");
        A = Integer.parseInt(arg[0]);
        B = Integer.parseInt(arg[1]);
        C = Integer.parseInt(arg[2]);
        System.out.println(String.valueOf(solution(B)));
    }

    public static int solution(int N) {
        if (N == 0)
            return 1;
        long tmp = solution(N / 2);
        long result = (tmp * tmp) % C;
        if (N % 2 == 1)
            result = (result * A) % C;
        return (int) result;
    }
}
