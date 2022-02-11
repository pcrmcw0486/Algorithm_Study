package RHS_FC.class07_topologicalSort;

/*
ACM Craft Gold III
거꾸로 생각해서 접근한다.
 */
import java.io.*;
import java.util.*;

public class Q1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] data = new int[N + 1];
            int[] inDegree = new int[N + 1];
            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            ArrayList<Integer>[] inList = new ArrayList[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                data[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<Integer>();
                inList[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                // SolveByCategory.graph
                graph[u].add(v);
                inList[v].add(u);
                inDegree[v]++;
            }
            int target = Integer.parseInt(br.readLine());

            // topological sort
            ArrayList<Integer> order = new ArrayList<Integer>();
            Deque<Integer> queue = new LinkedList<Integer>();
            for (int i = 1; i < inDegree.length; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int nxt : graph[cur]) {
                    inDegree[nxt]--;
                    if (inDegree[nxt] == 0) {
                        queue.add(nxt);
                    }
                }
                order.add(cur);
            }
            // 각 순서마다 계산
            for (int cur : order) {
                int max = 0;
                for (int in : inList[cur]) {
                    max = Math.max(max, data[in]);
                }
                data[cur] += max;
                if (cur == target)
                    break;
            }

            sb.append(data[target]).append('\n');
        }
        System.out.print(sb);
    }
}
