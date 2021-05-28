/*
https://www.acmicpc.net/problem/1005
ACM Craft Gold III
*** 접근 방법 문제 조건 ***

//Test Case > 건물 N, 규칙개수 K
 > 건물 건설시간 D (공백구분)
 > 건설 순서(edge) X를 지은 다음 Y를 짓는 것이 가능하다는 의미
 건설해야하는 건물의 번호 W
 1부터 시작
***자료구조 알고리즘 스킬 ***
dp, 위상정렬
 */
package tree.topology;

import java.util.*;
import java.util.stream.Stream;
import java.io.*;

public class Q1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            // ============= Data Input ============== //
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            // 그래프 및 degree개수, cost값 저장 배열 생성
            boolean[][] graph = new boolean[N][N];
            int[] dp = new int[N];
            int[] inDegree = new int[N];
            int[] cost = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken()) - 1;
                int w = Integer.parseInt(st.nextToken()) - 1;
                graph[v][w] = true;
                inDegree[w]++;
            }
            int W = Integer.parseInt(br.readLine()) - 1;

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            while (!queue.isEmpty()) {
                int now = queue.poll();
                dp[now] += cost[now];
                if (now == W) {
                    sb.append(dp[now]).append("\n");
                    break;
                }
                for (int i = 0; i < N; i++) {
                    if (graph[now][i]) {
                        inDegree[i]--;
                        dp[i] = Math.max(dp[i], dp[now]);
                        if (inDegree[i] == 0) {
                            queue.offer(i);
                        }
                    }
                }
            }
        }
        System.out.print(sb.toString());
    }
}
