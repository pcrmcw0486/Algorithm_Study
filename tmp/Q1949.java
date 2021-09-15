package tmp;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Q1949 {
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N][2];
        tree = new ArrayList[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dp[i][0] = 0;
            dp[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            tree[u].add(v);
            tree[v].add(u);
        }

        dfs(0, -1);
        int answer = Math.max(dp[0][0], dp[0][1]);
        System.out.println(answer);
    }

    private static void dfs(int cur, int parent) {
        for (int a : tree[cur]) {
            if (a != parent) {
                dfs(a, cur);
                dp[cur][1] += dp[a][0];
                // size ==0 이면 어차피 selected로 되네.
                if (tree[a].size() == 0) {
                    dp[cur][0] += dp[a][1];
                } else {
                    dp[cur][0] += Math.max(dp[a][0], dp[a][1]);
                }
            }

        }
    }
}
