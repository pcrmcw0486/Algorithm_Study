package programmers.kakao.year2020;

import java.io.*;
import java.util.*;

public class exploreCave {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] path = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            path[i][0] = v;
            path[i][1] = w;
        }
        int K = Integer.parseInt(br.readLine());
        int[][] order = new int[K][2];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            order[i][0] = v;
            order[i][1] = w;
        }

        System.out.println(solution(path, order));
    }

    static int[] visited;
    static Map<Integer, Integer> preCond;
    static int N;
    static boolean[][] graph;

    public static boolean solution(int[][] path, int[][] order) {
        boolean ans = true;

        graph = new boolean[N][N];
        for (int[] pair : path) {
            int w = pair[0];
            int v = pair[1];
            graph[w][v] = true;
            graph[v][w] = true;
        }
        preCond = new HashMap<>();
        for (int[] pair : order) {
            // 4 1 (1->4 연결하고, 1,4)
            int w = pair[0];
            int v = pair[1];
            graph[v][w] = true;
            preCond.put(v, w);
        }
        // 0 : unvisit // 1 : waiting // 2 : visit
        visited = new int[N];
        visited[0] = 2;
        return dfs(0);
    }

    public static boolean dfs(int now) {
        // visit option 정하기
        // option 에 따라 must정해서 만들기.
        int must = -1;
        if (preCond.containsKey(now)) {
            visited[now] = 1;
            must = preCond.get(now);
            if (!dfs(must))
                return false;
            visited[now] = 2;
        } else {
            visited[now] = 2;
        }
        for (int i = 0; i < N; i++) {
            if (graph[now][i] && i != must) {
                if (visited[i] == 0) {
                    if (!dfs(i)) {
                        return false;
                    }
                } else if (visited[i] == 1)
                    return false;
            }
        }
        visited[now] = 2;
        return true;

    }
}
