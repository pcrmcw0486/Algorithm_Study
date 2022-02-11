/*
 단계별로 풀어보기 - 그리디 알고리즘
 3.10
 동전 0 (Silver 2)
 https://www.acmicpc.net/problem/11047

 문제접근 : 주어지는 동전의 가치 A에 대해
 i>=2 인경우 Ai는 Ai-1의 배수이다.
 즉 Ai-1 * N = Ai이므로
 가장 큰 수부 터 나눠 줄여가더라도 문제가 되지 않는다.
 */

package SolveByCategory.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        int target = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(br.readLine());
        int result = 0;
        int index = N - 1;
        while (target > 0) {
            if (target >= data[index]) {
                result += target / data[index];
                target = target % data[index];
            }
            index--;
        }
        System.out.println(result);
    }

}
