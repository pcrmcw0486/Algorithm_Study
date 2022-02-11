/*
기본적인 SolveByCategory.backtracking 문제
오름차순으로 수열을 생성하며 중복은 없다.
visit과 같은 체크 배열을 처음에는 사용하였으나
이미 선택한 숫자 이후의 숫자들을 선택하기 때문에
필요가 없다고 생각한다. for문 자체에서 걸러지기 때문에
 */
package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15650 {

    public static int N;
    public static int M;
    public static int[] numbers;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        solution(0, 0);
        System.out.print(sb.toString());
    }

    public static void solution(int start, int count) {
        if (count == M) {
            for (int n : numbers) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }
        if (N - start < M - count)
            return;
        for (int i = start + 1; i < N + 1; i++) {
            numbers[count] = i;
            solution(i, count + 1);
        }

    }
}
