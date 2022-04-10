package DayByDay._0408;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KakaoBlind2020No6 {
    public static void main(String[] args) {
        int n = 9;
        int[][] path = new int[][]{{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}};
        int[][] order = new int[][]{{4, 1}, {8, 7}, {6, 5}};
        boolean ret = solve(n, path, order);
        System.out.println(ret);
    }

    static ArrayList<Integer>[] graph;
    static int[] inDegree;
    static boolean[] isVisited;

    private static boolean solve(int n, int[][] path, int[][] order) {
        inDegree = new int[n];
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();

        for (int[] subPath : path) {
            graph[subPath[0]].add(subPath[1]);
            graph[subPath[1]].add(subPath[0]);
        }
        isVisited = new boolean[n];
        dfs(0, -1);
        for (int[] subOrder : order) {
            graph[subOrder[0]].add(subOrder[1]);
            inDegree[subOrder[1]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            System.out.println(curNode);
            cnt++;
            for (int nxt : graph[curNode]) {
                inDegree[nxt]--;
                if (inDegree[nxt] == 0) {
                    queue.add(nxt);
                }
            }
        }
        return cnt == n;
    }

    private static void dfs(int cur, int parent) {
        if(parent != -1)
            inDegree[cur]++;
        for(int nxt : graph[cur]){
            if(!isVisited[nxt] && nxt != parent){
                dfs(nxt, cur);
            }
        }
    }

}
