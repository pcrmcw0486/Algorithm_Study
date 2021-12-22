package RHS_FC.class05_graph;

/*
연결 요소의 개수 Silver II
방향없는 그래프가 주어졌을 때, 연결 요소의 개수를 구하는 프로그램을 작성하시오
정점 N (1000) 간선 M
같은 간선은 한 번만 주어짐.
 */
import java.io.*;
import java.util.*;

public class Q11724 {
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visit = new boolean[N + 1];
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            if (!visit[i]) {
                dfs(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int X) {
        if (visit[X])
            return;
        visit[X] = true;
        for (int next : graph.get(X)) {
            if (!visit[next]) {
                dfs(next);
            }
        }
    }
}
