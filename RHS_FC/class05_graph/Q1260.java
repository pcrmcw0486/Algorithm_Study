package RHS_FC.class05_graph;

/*
DFS와 BFS Silver II
DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력.
정점번호가 작은 것을 먼저 방문.
N 1000
M 10_000
어떤 두 정점 사이에 여러개의 간선이 있을 수 있다.
양방향(undirected SolveByCategory.graph)
처리할게
 1. 중복 간선(무시 할건지, 무시 가능하긴함) > visit으로 check 하면 됨.
 2. 작은 간선 번호부터 how? (sort 때리면 되긴 하죠?) NlogN이긴 한데
 */
import java.util.*;
import java.io.*;

public class Q1260 {
    static int N;
    static int M;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> adj;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 1];
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        for (int i = 1; i < N + 1; i++) {
            Collections.sort(adj.get(i));
        }
        dfs(S);
        visit = new boolean[N + 1];
        sb.append('\n');
        bfs(S);
        System.out.println(sb);
    }

    public static void dfs(int X) {
        visit[X] = true;
        sb.append(X).append(' ');
        for (int next : adj.get(X)) {
            if (!visit[next]) {
                dfs(next);
            }
        }
    }

    public static void bfs(int X) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        visit[X] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(' ');
            for (int next : adj.get(now)) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }

    }

}
