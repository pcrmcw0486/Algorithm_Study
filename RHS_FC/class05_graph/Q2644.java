package RHS_FC.class05_graph;

import java.io.*;
import java.util.*;

public class Q2644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        int[] visited = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] target = new int[2];
        target[0] = Integer.parseInt(st.nextToken());
        target[1] = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(target[0]);
        visited[target[0]] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == target[1]) {
                break;
            }
            for (int nxt : graph.get(cur)) {
                if (visited[nxt] == 0) {
                    visited[nxt] = visited[cur] + 1;
                    queue.add(nxt);
                }
            }
        }
        System.out.println(visited[target[1]] == 0 ? -1 : visited[target[1]]);
    }
}
