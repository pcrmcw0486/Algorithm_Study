package solvedac.level5.gold5;
/*
2022.01.04
문제번호 : Q12852
이름 및 난이도 : 1로 만들기 2 Silver I
문제이해 
-----------------
정수 X에 사용할 수 있는 연산은 다음과 같이 3가지.
 1. X가 3으로 나누어떨어지면 3으로 나눔.
 2. X가 2로 나누어 떨어지면 2로 나눔.
 3. 1을 뺀다.
정수 N이 주어질 때 1로 만드는 연산의 최소값.
+ 만드는 방법을 공백으로 구분하여 순서대로 출력.

접근 방법 :
  - "최소" "여러가지 방법" 상태공간 1~10^6
  - bfs 
  - 방향성이 한 방향.
  - topdown이 훨씬빠르네.
제한 조건 : 
주어지는 N은 int형 범위 자연수.
*/

import java.io.*;
import java.util.*;

public class Q12852 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[1] = -1;
        int step = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        boolean success = false;
        while (true) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == N) {
                    success = true;
                    break;
                }
                if (2 * cur < N + 1 && dp[2 * cur] == 0) {
                    dp[2 * cur] = cur;
                    queue.add(2 * cur);
                }
                if (3 * cur < N + 1 && dp[3 * cur] == 0) {
                    dp[3 * cur] = cur;
                    queue.add(3 * cur);
                }
                if (cur + 1 < N + 1 && dp[cur + 1] == 0) {
                    dp[cur + 1] = cur;
                    queue.add(cur + 1);
                }
            }
            if (success)
                break;
            step++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(step).append('\n');
        int cur = N;
        while (cur != -1) {
            sb.append(cur).append(' ');
            cur = dp[cur];
        }

        // for (int j = 1; j < N + 1; j++) {
        // System.out.print(SolveByCategory.dp[j] + " ");
        // }
        // System.out.println();

        System.out.println(sb.toString());
    }
}
