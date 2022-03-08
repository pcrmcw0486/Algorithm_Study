package solvedac.level5.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_1647
 * @문제이름 : 도시 분할 계획
 * @난이도 : Gold IV
 * @date : 2022-03-01 오전 5:44
 * @author : pcrmcw0486
 * @문제이해
 * N개의 마을과 집을 연결하는 M개의 기로 되어 있다. 길은 양방향이고 유지비가 있다.(Undirected Weight graph)
 * 마을을 분리하려고 하는데 각 마을은 마을 안의 집들이 모두 연결되도록 해야한다.
 * 마을에는 집이 하나 이상 있어야 한다.
 * 분리된 두 마을은 필요가 없으므로 없앨 수 있다. 분리된 마을 안에서도 임의의 두 집에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다.
 * 길 유지비 합을 최소로 하고 싶다.
 *
 * @알고리즘
 * 최소 스패닝 트리 구하기.
 * @접근방법
 * * (임의의 두집에 경로가 항상 존재한다) >> 모든 노드들이 연결되어 있다
 *  * + 유지비 합을 최소로하고 싶다 -> 최소의 유지비로 모든 노드들이 연결되도록 하고 싶다.-> 최소 스패닝 트리.
 *  * 전체 마을에 대해 최소 스패닝 트리를 구하고 그 중 가장 유지비가 많이 드는 간선을 제거하게 되면 조건을 만족하도록 분할 할 수 있다.
 *  * Kruskal 알고리즘을 활용하면서 최소 스패닝 트리를 구한다. 동시에 kruskal알고리즘은 그리디하기 때문에 마지막에 연결되는 선분을 자르게 된다면
 *  * 그 간선이 자르게 되는 간선이다.
 *  * 즉, 마을 전체를 최소로 연결할 수 있도록 만든다. 가장 긴 간선을 잘라 스패닝 트리를 유지한다.

*/
public class Review_1647 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) parent[i] = i;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, w));
        }
        int ans =0;
        int cnt =N-2;
        while (!pq.isEmpty()) {
            if(cnt ==0) break;
            Edge cur = pq.poll();
            if (union(cur.from, cur.to)) {
                ans += cur.cost;
                cnt--;
            }
        }
        System.out.println(ans);
    }
    static class Edge implements Comparable<Edge>{
        int from,to,cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static int find(int x){
        if(parent[x] ==x) return x;
        return parent[x] = find(parent[x]);
    }
    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if( a==b) return false;
        parent[a] = b;
        return true;
    }
}
