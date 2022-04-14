package DayByDay._0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14950
 * @문제이름 : 정복자
 * @난이도 : Gold III
 * @date : 2022-04-14 오후 1:48
 * @author : pcrmcw0486
 * @문제이해
 * 서강 나라는 N개의 도시와 M개의 (양방향 )도로
 * 도시는 1부터 N번까지.
 * 처음 점거 도시 1번.
 * B정복하고싶다면, B와 도로로 연결된 도시 중 적어도 하나는 정복해야함.
 *조건을 만족하는 A선택시, B정복과정에서 A와 B를 연결하는 도로의 비용이 소모됨.
 * 한번 도시가 정복되면 모든 도시는 경계하기 때문에 모든 도로의 비용이 t만큼 증가한다.
 * 한번 정복한 도시는 다시 정복하지 않는다.
 * 모든 도시를 정복하는데 사용되는 최소 비용을 구하시오.
 * 1->B라면 1-B연결된 도로 비용이 소모된다.
 * 이후 t만큼 모든 도로의 비용이 증가한다.
 * 한번에 한번의 도시만 정복이 가능하다.
 * N-1개의 city를 정복하는데 0 t 2*t 3*t 4*t .. (N-1)t
 * 최소 스패닝 트리로 연결하고 이후 t만큼 모두 더해주면된다.
 * t는 어차피 상수라 크게 신경쓰지 않아도 된다.
 * @알고리즘

 * @접근방법

*/
public class Q14950 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for(int i =0;i<N+1;i++) parent[i] =i;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        ArrayList<Edge>[] graph = new ArrayList[N + 1];
//        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<Edge>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            pq.add(new Edge(u, v, value));
        }
        int totCost =0;
        int cnt =0;
        while (!pq.isEmpty()) {
            if(cnt == N-1) break;
            Edge curEdge = pq.poll();
            if (union(curEdge.u, curEdge.v)) {
                totCost += curEdge.value;
                cnt++;
            }
        }
        System.out.println(totCost + ((N-2)*(N-1)/2 * t));


    }

    public static int find(int x) {
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if( x== y) return false;
        parent[x] = y;
        return true;
    }

    public static class Edge implements Comparable<Edge>{
        int u, v, value;

        public Edge(int u, int v, int value) {
            this.u = u;
            this.v = v;
            this.value = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
}
