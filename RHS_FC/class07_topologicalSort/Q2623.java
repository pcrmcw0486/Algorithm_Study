package RHS_FC.class07_topologicalSort;

/*
음악 순서 Gold II
위상정렬 + 언제 위상정렬이 되지 않는지.
사이클이 생기는 경우 되지 않을 것임.
사이클 check는 어떻게 하는가.
indegree가 1이상 있을 때. 즉 queue에 총 N번 안들어 온다면.
N~1000
M<100
 */
import java.io.*;
import java.util.*;

public class Q2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N + 1];
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph[i] = new ArrayList<Integer>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size - 1; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                inDegree[b]++;
                a = b;
            }
        }

        int cnt = N;
        Deque<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append('\n');
            cnt--;
            for (int nxt : graph[cur]) {
                inDegree[nxt]--;
                if (inDegree[nxt] == 0)
                    queue.add(nxt);
            }
        }
        if (cnt == 0)
            System.out.print(sb);
        else
            System.out.println("0");
    }
}
