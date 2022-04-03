package DayByDay._0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @문제번호 : Q5972
 * @문제이름 : 택배 배송
 * @난이도 : Gold V
 * @date : 2022-04-03 오후 4:15
 * @author : pcrmcw0486
 * @문제이해
 * 만나는 소들에게 여물을 주자. 최소한의 소를 만나 지나가고 싶다.
 * N개의 헛간과 소들의 길  M
 * 각 길은 Ci 마리 소가 있다.
 * 헛간 1에서 출발하여 N에 도착하고 싶다(1~N)
 * @알고리즘

 * @접근방법

*/
public class Q5972 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        System.out.println(solveByDijkstra(N, M, br));
    }

    static int INF = 987654321;
    static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int solveByDijkstra(int numOfFarm, int numOfEdge, BufferedReader br) throws IOException {
        List<Edge>[] graph = initGraph(numOfFarm,numOfEdge,br);
        int[] minimumCost = new int[numOfFarm];
        boolean[] isVisited = new boolean[numOfFarm];
        Arrays.fill(minimumCost, INF);
        minimumCost[0] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(0, 0));
        isVisited[0] = true;
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if(minimumCost[cur.to] < cur.cost) continue;
            for (Edge nxtEdge : graph[cur.to]) {
                if(!isVisited[nxtEdge.to] && minimumCost[nxtEdge.to] > minimumCost[cur.to] + nxtEdge.cost){
                    minimumCost[nxtEdge.to] = minimumCost[cur.to] + nxtEdge.cost;
                    queue.add(new Edge(nxtEdge.to,  minimumCost[nxtEdge.to]));
                }
            }
            isVisited[cur.to] = true;
        }

        return minimumCost[numOfFarm-1];
    }

    private static List<Edge>[] initGraph(int numOfFarm, int numOfEdge, BufferedReader br) throws IOException {
        List<Edge>[] graph = new ArrayList[numOfFarm + 1];
        StringTokenizer st;
        for (int i = 0; i < numOfFarm; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < numOfEdge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, d));
            graph[v].add(new Edge(u, d));
        }
        return graph;
    }

    static class Edge implements Comparable<Edge>{
        int to, cost;

        public Edge(int to, int value) {
            this.to = to;
            this.cost = value;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
