/**
 *** 운동 Gold IV ***
 *** 접근 방향 ***
- 사이클이 어디 존재하는지는 모르겠으나 전체 탐색을 해야함 => 전체 source > 전체 destination 문제
= Floyd-Warshall 사용
- 이후 cycle 탐색
 > cycle은 D[s,v]에 대하여 D[s,v] + D[v,s]가  s->v->s 로 cycle을 이룬다면 최소임. 만약 둘 중 하나라도 끊어져
 있다면 cycle이 되지 못함.
 *** 알고리즘 및 자료구조 ***
Floyd Warshall
 *** 문제 조건 및 이해 ***
- V,E 일방 통행 도로(Directed Graph)
- 운동 후 다시 시작점으로 돌아온다. >> Cycle 찾기
  Cycle의 길이 최소., 중복 간선 X
- 정점이 어디든 cycle을 찾는데 집중한다.
 */
package SolveByCategory.graph.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1956 {
    public static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] dist = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], INF);
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            dist[u][v] = weight;
        }

        for (int k = 0; k < V; k++) {
            // K : 거쳐가는 정점
            for (int i = 0; i < V; i++) {
                // i : start
                if (dist[i][k] == INF || i == k)
                    continue;
                for (int j = 0; j < V; j++) {
                    // j : end
                    // dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int result = INF;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                result = Math.min(result, dist[i][j] + dist[j][i]);
            }
        }
        System.out.println(result != INF ? result : -1);
    }
}
