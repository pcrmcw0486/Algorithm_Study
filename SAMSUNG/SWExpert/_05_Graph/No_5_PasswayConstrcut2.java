package SAMSUNG.SWExpert._05_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
모든 도시를 잇는 고속도로. 최소비용으로 모든 도시.
어떤 도시는 직접 도로를 이을 수 없다.
이을 수 있는 도로의 목록.
* */
public class No_5_PasswayConstrcut2 {
    static int N, M;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tt = 1;tt<=T;tt++){
            sb.append('#').append(tt).append(' ');
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            pq = new PriorityQueue<Edge>();
            parent = new int[N+1];
            for(int i =0;i<N+1;i++) parent[i] = i;

            for(int i =0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pq.add(new Edge(s,e,c));
            }
            int ans = 0;
            int cnt = 0;
            while (!pq.isEmpty()) {
                if(cnt == N-1) break;
                Edge cur = pq.poll();
                if(union(cur.x,cur.y)){
                    ans += cur.dist;
                    cnt++;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static int find(int n){
        if(n == parent[n]) return n;
        return parent[n] = find(parent[n]);
    }
    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if( x== y) return false;
        parent[y] = x;
        return true;
    }
    public static class Edge implements Comparable<Edge>{
        int x, y;
        int dist;
        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
