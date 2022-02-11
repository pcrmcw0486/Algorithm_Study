/*
*** 숨바꼭질 4 Gold V ***
*** 접근 방법 ***
- BFS를 활용하여 갈 수 있는 방향을 탐색하면서 동생을 찾는다.
- 찾았을 경우 지금까지 온 길을 돌아가야함.
- 돌아간다? > Stack 또는 기록 
- visit의 check를 돌아갈 방향으로 check하고 길이는 Queue에 같이 전달한다.
- 도착하면 result stack에 쌓아서 처음 위치로 돌아왔을 때 pop하면서 출력한다.
*** 활용 알고리즘 및 활용 방법 ***
- BFS, 
*** 문제의 조건 ***
- 움직이는 방향 3방향.
- 어떻게 이동하는 지 공백으로 구분해 출력
*/
package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        solution(N, K);
        br.close();
    }

    public static void solution(int N, int K) {
        Queue<int[]> q = new LinkedList<>();
        Stack<Integer> route = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int[] visit = new int[100001];
        if (K <= N) {
            sb.append(N - K).append("\n");
            for (int i = N; i >= K; i--)
                sb.append(i).append(" ");
            System.out.println(sb.toString());
            return;
        }
        q.offer(new int[] { N, 0 });
        while (!q.isEmpty()) {
            int[] item = q.poll();
            int now = item[0];
            int time = item[1];

            if (now == K) {
                sb.append(time).append("\n");
                // find route
                route.push(now);
                while (now != N) {
                    if (visit[now] == 1)
                        now = now + 1;
                    else if (visit[now] == 2)
                        now = now - 1;
                    else
                        now = now / 2;
                    route.push(now);
                }
                // print
                while (!route.isEmpty())
                    sb.append(route.pop()).append(" ");
                System.out.println(sb.toString());
            }

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0)
                    next = now - 1;
                else if (i == 1)
                    next = now + 1;
                else
                    next = now * 2;

                if (next < 0 || next > 100000)
                    continue;
                if (visit[next] == 0) {
                    visit[next] = i + 1;
                    q.offer(new int[] { next, time + 1 });
                }
            }
        }
    }
}
