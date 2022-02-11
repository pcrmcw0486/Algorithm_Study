/*
*** 특정한 최단 경로 Gold IV ***
*** 접근 방법 ***
- 최단 경로 문제
- 1 ->a a -> b  b -> N 의 최단 경로 
   VS  1->b b->a a->N의  최단경로 중 가장 짧은 것이 된다.
- Dijkstra 알고리즘이 인접리스트 활용시 O(N log N)이므로 3번 돌려서 dist를 구한 뒤
 단순 계산을 한다.
*** 활용 알고리즘 및 활용 방법 ***
-  dijkstra?
- graph는 인접리스트로 구현
- dist는 총 3*V 배열
- dijkstra는 함수로 만들어서 배열 반환 시킨다.
*** 문제의 조건 ***
- 방향성이 없는 그래프
- 1번 -> N번 정점으로 최단 거리 이동
- 한번 이동했던 정점과 한번 이동했던 간선 다시 이용 가능.(but 최단 경로)
- 주어진 두 정점을 반드시 거치면서 최단경로로 이동.
- 1 > ( a > b >) > N
*/
package SolveByCategory.graph.ShortestPath;

import java.io.*;
import java.util.*;

class NodeQ1753 implements Comparable<NodeQ1753> {
    int end, weight;

    public NodeQ1753(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(NodeQ1753 o) {
        return weight - o.weight;
    }
}

public class Q1753 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int INF = 100_000_000;
    static int v, e, k;
    static List<NodeQ1753>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        list = new ArrayList[v + 1];
        dist = new int[v + 1];

        Arrays.fill(dist, INF);

        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }
        // 리스트에 그래프 정보를 초기화
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // start에서 end로 가는 weight 가중치
            list[start].add(new NodeQ1753(end, weight));
        }

        StringBuilder sb = new StringBuilder();
        // 다익스트라 알고리즘
        dijkstra(k);
        // 출력 부분
        for (int i = 1; i <= v; i++) {
            if (dist[i] == INF)
                sb.append("INF\n");
            else
                sb.append(dist[i] + "\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<NodeQ1753> queue = new PriorityQueue<>();
        boolean[] check = new boolean[v + 1];
        queue.add(new NodeQ1753(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            NodeQ1753 curNode = queue.poll();
            int cur = curNode.end;

            if (check[cur] == true)
                continue;
            check[cur] = true;

            for (NodeQ1753 node : list[cur]) {
                if (dist[node.end] > dist[cur] + node.weight) {
                    dist[node.end] = dist[cur] + node.weight;
                    queue.add(new NodeQ1753(node.end, dist[node.end]));
                }
            }
        }
    }
}