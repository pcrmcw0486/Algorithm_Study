package DayByDay._0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[10001];
            char[] command = new char[10001];
            int[] pre = new int[10001];
            Queue<Integer> queue = new LinkedList<Integer>();
            pre[A] = -1;
            visited[A] = true;
            command[A] = ' ';
            queue.add(A);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == B)
                    break;
                int nxtD = (cur * 2) % 10000;
                int nxtS = (cur - 1) == -1 ? 9999 : cur - 1;
                int nxtL = (cur % 1000) * 10 + cur / 1000;
                int nxtR = (cur % 10) * 1000 + cur / 10;
                if (!visited[nxtD]) {
                    pre[nxtD] = cur;
                    command[nxtD] = 'D';
                    visited[nxtD] = true;
                    queue.add(nxtD);
                }
                if (!visited[nxtS]) {
                    pre[nxtS] = cur;
                    command[nxtS] = 'S';
                    visited[nxtS] = true;
                    queue.add(nxtS);
                }
                if (!visited[nxtL]) {
                    pre[nxtL] = cur;
                    command[nxtL] = 'L';
                    visited[nxtL] = true;
                    queue.add(nxtL);
                }
                if (!visited[nxtR]) {
                    pre[nxtR] = cur;
                    command[nxtR] = 'R';
                    visited[nxtR] = true;
                    queue.add(nxtR);
                }
            }
            int tmp = B;
            StringBuilder sbb = new StringBuilder();
            while (tmp != A) {
                sbb.append(command[tmp]);
                tmp = pre[tmp];
            }
            sb.append(sbb.reverse().toString()).append('\n');
        }
        System.out.print(sb.toString());
    }
}
