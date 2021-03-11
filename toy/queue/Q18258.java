/*단계별로 풀어보기 - 큐2
큐 구현 Dequeue사용
*/
package toy.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q18258 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> dq = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    int input = Integer.parseInt(st.nextToken());
                    dq.offer(input);
                    break;
                case "pop":
                    if (dq.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(dq.poll()).append("\n");
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty":
                    sb.append((dq.isEmpty()) ? 1 : 0).append("\n");
                    break;
                case "front":
                    if (dq.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(dq.getFirst()).append("\n");
                    break;
                case "back":
                    if (dq.isEmpty())
                        sb.append(-1).append("\n");
                    else
                        sb.append(dq.getLast()).append("\n");
                    break;
            }
        }
        System.out.print(sb.toString());
    }
}
