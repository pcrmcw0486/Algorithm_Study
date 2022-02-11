/*
    대각선 기준으로 행과 열의 합은 다음과 같음 4*4일떄
    0 1 2 3 4  (왼쪽 대각선  / 방향)
    1 2 3 4 5
    2 3 4 5 6
    3 4 5 6 7
    4 5 6 7 8

    행과 열의 차는 다음과 같음
    0   1  2  3  4 오른쪽대각선(\ 방향)
    -1  0  1  2  3
    -2 -1  0  1  2
    -3 -2 -1  0  1
    -4 -3 -2 -1  0

    을 이용하여 행 또는 열 기준일 때 한 가지의 경우는 조건을 제외해도
    됨으로 3가지 배열을 사용한다.
    왼쪽대각, 오른쪽 대각, 행 또는 열
    대각 체크 배열의 초기화는 대각선 개수 만큼이 되겟다.

*/
package SolveByCategory.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663_3 {
    static boolean[] diagonal1;
    static boolean[] diagonal2;
    static boolean[] col;
    static int N;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        diagonal1 = new boolean[2 * N + 1];
        diagonal2 = new boolean[2 * N + 1];
        col = new boolean[N];
        solution(0);
        System.out.println(result);
    }

    public static void solution(int count) {
        if (count == N)
            result++;
        for (int i = 0; i < N; i++) {
            // 3가지 조건 중 하나라도 true이면 들어갈 수 없음.
            if (col[i] || diagonal1[count + i] || diagonal2[i - count + N])
                continue;
            col[i] = true;
            diagonal1[count + i] = true;
            diagonal2[i - count + N] = true;
            solution(count + 1);
            col[i] = false;
            diagonal1[count + i] = false;
            diagonal2[i - count + N] = false;
        }
    }
}
