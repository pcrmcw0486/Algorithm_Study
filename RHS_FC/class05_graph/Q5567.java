package RHS_FC.class05_graph;

/*
결혼식 Silver II
 */
import java.io.*;
import java.util.*;

public class Q5567 {
    static ArrayList<ArrayList<Integer>> graph;
    static int cnt = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Integer>>();
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph.add(new ArrayList<Integer>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        visited[1] = true;
        int limit = 2;
        while (!queue.isEmpty() && limit >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int nxt : graph.get(cur)) {
                    if (!visited[nxt]) {
                        visited[nxt] = true;
                        queue.add(nxt);
                    }
                }
            }
            limit--;
            cnt += size;
        }

        System.out.println(cnt - 1);

        cnt = 0;
        visited = new boolean[N + 1];
        dfs(1, 0);
        for (int i = 2; i < N + 1; i++) {
            if (visited[i])
                cnt++;
        }
        System.out.println(cnt);
    }

    public static void dfs(int index, int depth) {
        if (depth == 2)
            return;
        for (int nxt : graph.get(index)) {
            visited[nxt] = true;
            dfs(nxt, depth + 1);
        }
    }

}
