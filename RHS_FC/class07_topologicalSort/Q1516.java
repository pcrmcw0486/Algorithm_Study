package RHS_FC.class07_topologicalSort;

/*
게임 개발 Gold III
모든 건물을 짓는데 걸리는 최소의 시간을 이용하여 근사한다.
여러개의 건물을 동시에 지을 수 있다.
 */
import java.io.*;
import java.util.*;

public class Q1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 1];
        int[] inDegree = new int[N + 1];
        int[] answer = new int[N + 1];
        ArrayList<Integer>[] order = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++)
            order[i] = new ArrayList<Integer>();
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            while (st.hasMoreTokens()) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1)
                    break;
                order[v].add(i);
                inDegree[i]++;
            }
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                answer[i] = time[i];
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nxt : order[cur]) {
                answer[nxt] = Math.max(answer[nxt], answer[cur] + time[nxt]);
                inDegree[nxt]--;
                if (inDegree[nxt] == 0) {
                    queue.add(nxt);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++)
            sb.append(answer[i]).append('\n');
        System.out.print(sb);
    }

}
