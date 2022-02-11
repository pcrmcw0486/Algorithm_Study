/*
https://www.acmicpc.net/problem/3665
 최종 순위 Gold I
  *** 접근 방법 문제 조건 ***
 작년에 비해서 상대적인 순위가 바뀐 팀의 목록. (작년은 순위를 발표)
 작년에 팀 13이 팀 6 보다 순위가 높았는데 6이 13보다 순위가 높다면 (6,13)

 작년순위와 상대적인 순위가 바뀐 모든팀의 목록
 올해 순위를 만드시오.
 만들 수 없는 경우도 있고, 일관성이 없는 잘못된 정보일 수도 있다.
 확실한 순위를 찾을 수 없다면 ?   >> 나올 수 있는 경우의 수가 여러가지
 데이터에 일관성이 없어 순위를 정할 수 없다면 "IMPOSSIBLE" >> 그냥 잘못된 케이스.
 1등 부터 차례대로 발표한다.
 자, 작년 팀 13이 2등 팀6이 5등인데
  이번 년도 팀 6이 팀 13보다 순위가 높다면. 이 순위를 어떻게 써먹는가.
   이 말은 일단 작년 기준 순서가 정해져있기 때문에 0 -> 0 -> 0 -> 와 같은 순이였을 것.
  *** 자료구조 알고리즘 스킬 ***

 */
package SolveByCategory.tree.topology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Q3665 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            // index가 순위
            int[] teamRank = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean[][] graph = new boolean[N + 1][N + 1];
            int[] inDegree = new int[N + 1];
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    graph[teamRank[i]][teamRank[j]] = true;
                    inDegree[teamRank[j]]++;
                }
            }
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                if (graph[v][w]) {
                    graph[v][w] = false;
                    graph[w][v] = true;
                    inDegree[v]++;
                    inDegree[w]--;
                } else {
                    graph[w][v] = false;
                    graph[v][w] = true;
                    inDegree[v]--;
                    inDegree[w]++;
                }
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i < N + 1; i++) {
                if (inDegree[i] == 0)
                    queue.offer(i);
            }
            StringBuilder record = new StringBuilder();
            int cnt = 0;
            for (cnt = 0; cnt < N; cnt++) {
                if (queue.isEmpty()) {
                    sb.append("IMPOSSIBLE").append("\n");
                    break;
                } else if (queue.size() > 1) {
                    sb.append("?").append("\n");
                    break;
                } else {
                    int now = queue.poll();
                    record.append(now).append(" ");
                    for (int j = 1; j < N + 1; j++) {
                        if (graph[now][j]) {
                            inDegree[j]--;
                            if (inDegree[j] == 0) {
                                queue.offer(j);
                            }
                        }
                    }
                }
            }
            if (cnt == N) {
                sb.append(record).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
