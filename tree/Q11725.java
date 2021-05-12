/*
https://www.acmicpc.net/problem/11725
List<Integer>[] adj = new ArrayList[N+1];
for(int i = 1;i<=N;i++)
    adj[i] = new ArrayList<>();
adj[n1].add(n2);
 */
package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            tree.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        queue.add(1);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visit[cur] = true;
            for (int next : tree.get(cur)) {
                if (!visit[next]) {
                    visit[next] = true;
                    result[next] = cur;
                    queue.add(next);
                }
            }
        }

        for (int i = 2; i < result.length; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb.toString());

    }
}
