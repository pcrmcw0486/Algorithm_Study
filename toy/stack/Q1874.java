/*단계별로 풀어보기 - 스택
스택수열(silver3)
 */
package toy.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q1874 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> seq = new Stack<>();
        int N = Integer.parseInt(br.readLine());
        int data;
        int progress = 0;
        for (int i = 0; i < N; i++) {
            data = Integer.parseInt(br.readLine());
            while (progress < data) {
                seq.push(++progress);
                sb.append("+").append("\n");
            }
            if (progress == data) {
                sb.append("-").append("\n");
                seq.pop();
            }
            if (progress > data) {
                if (seq.isEmpty() || seq.peek() != data) {
                    sb.replace(0, sb.length(), "NO");
                    break;
                } else {
                    sb.append("-").append("\n");
                    seq.pop();
                }
            }
        }
        System.out.print(sb.toString());

    }
}
