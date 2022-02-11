/*
*** 플로이드 Gold IV ***
*** 접근 방법 ***
- 최단 경로 문제
- 전체 정점에서 전체 경로를 구하는 문제 

*** 활용 알고리즘 및 활용 방법 ***
- Floyd warshall 기본문제  
*** 문제의 조건 ***
- n(2=< <=100) 버스 m(간선, 1<= <=100000)
- 시작도시와 도착도시를 연결하는 노선은 하나가 아닐 수 있다.?
- 갈 수 없다면 0을 출력한다.
- INF의 설정 너무 크면 더하다가 overflow가 나서 음수가 되어 간선으로 채택되기
때문에 적당히 큰 수를 설정해 주어야하는데 이때
간선이 최대로 클 수 있는 건 간선 최대길이 100,000 * 최대 간선수(V-1) 보다 커야한다.
100000 * 100 = 11000000로 정한다.
*/
package SolveByCategory.graph.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11404 {
    public static final int INF = 11000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            graph[u][v] = Math.min(graph[u][v], weight);
        }

        for (int k = 0; k < V; k++) {
            // K : 거쳐가는 정점
            for (int i = 0; i < V; i++) {
                // i : start
                if (graph[i][k] == INF)
                    continue;
                for (int j = 0; j < V; j++) {
                    // j : end
                    // SolveByCategory.graph[i][j] = Math.min(SolveByCategory.graph[i][j], SolveByCategory.graph[i][k] + SolveByCategory.graph[k][j]);
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(graph[i][j] != INF ? graph[i][j] : 0).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
