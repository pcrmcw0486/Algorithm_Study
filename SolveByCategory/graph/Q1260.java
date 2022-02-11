/* Q1260
DFS와 BFS
자바에서 dfs와 bfs를 연습한다.
*/

package SolveByCategory.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1260 {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 인접 행렬의 이용
        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        for (int i = 0; i < N + 1; i++) {
            Collections.sort(graph.get(i));
        }

        visit = new boolean[N + 1];
        dfs(V);
        System.out.println();
        visit = new boolean[N + 1];
        bfs(V);
    }

    static void bfs(int V) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(V);
        while (!q.isEmpty()) {
            int temp = q.poll();
            visit[temp] = true;
            System.out.print(temp + " ");
            for (int i : graph.get(temp)) {
                if (visit[i] == false) {
                    visit[i] = true;
                    q.add(i);
                }
            }
        }
    }

    static void dfs(int V) {
        if (visit[V] == true)
            return;
        else {
            visit[V] = true;
            System.out.print(V + " ");
            for (int i : graph.get(V)) {
                if (visit[i] != true)
                    dfs(i);
            }
        }
    }
}
