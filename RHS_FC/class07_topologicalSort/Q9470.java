package RHS_FC.class07_topologicalSort;

/*
Strahler 순서 Gold III
하천계의 방향 그래프 강> 간선 노드 > 시작, 분기점, 바다와만나는곳
강의 근원인 노드의 순서는 1이다.
나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때
들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i, 
2개 이상이면 순서는 i +1이다. 바다와 만나는 노드는 항상 1개이다.
inDegree 값이 2이상이면(들어오는 값이 2이상이면) strahler값 증가.

T 1000
M 1000
M은 항상 바다와 만나는 노드.(answer 값)
 */
import java.io.*;
import java.util.*;

public class Q9470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            int[] inDegree = new int[M + 1];
            int[][] Strahler = new int[M + 1][2];
            ArrayList<Integer>[] graph = new ArrayList[M + 1];
            for (int i = 0; i < M + 1; i++)
                graph[i] = new ArrayList<Integer>();
            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                inDegree[v]++;
            }

            // 제일 앞에 "정렬될 수 있는 " 정점 추가
            Deque<Integer> queue = new LinkedList<Integer>();
            for (int i = 1; i < M + 1; i++) {
                if (inDegree[i] == 0)
                    queue.add(i);
            }
            // 정열될 수 있는 정점이 있다면?
            // 1. 정렬 결과에 추가
            // 2. 현재 정점과 연결된 정점들에 대해 다음과 같이 수행
            // 2-1. 현재 정점의 strHaler값과 같다면 개수를 증가 (strHaler[0], [1])
            // 2-2. 이전의 값보다 현재 정점의 strHaler값이 크다면 변경
            // 3. 정점과 연결된 간선 제거
            // 3-1 제거 시 (즉 마지막 연결되었던 정점)에
            // 현재까지의 strHaler정보를 토대로 2이상일 경우 값 증가.
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int nxt : graph[cur]) {
                    inDegree[nxt]--;
                    if (Strahler[nxt][0] == Strahler[cur][0])
                        Strahler[nxt][1]++;
                    else if (Strahler[nxt][0] < Strahler[cur][0]) {
                        Strahler[nxt][0] = Strahler[cur][0];
                        Strahler[nxt][1] = 1;
                    }
                    if (inDegree[nxt] == 0) {
                        queue.add(nxt);
                        if (Strahler[nxt][1] > 1) {
                            Strahler[nxt][0]++;
                        }
                    }
                }
            }
            // System.out.println("=======");
            // int idx = 0;
            // for (int[] a : Strahler) {
            // System.out.println(idx + " " + a[0]);
            // idx++;
            // }
            sb.append(K).append(' ').append(Strahler[M][0] + 1).append('\n');
        }
        System.out.print(sb);
    }
}
