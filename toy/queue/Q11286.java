package toy.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Q11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> positive = new PriorityQueue<>();
        PriorityQueue<Integer> negative = new PriorityQueue<>((o1, o2) -> o2 - o1);

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number < 0) {
                negative.offer(number);
            } else if (number > 0) {
                positive.offer(number);
            } else {
                if (!negative.isEmpty() && !positive.isEmpty()) {
                    int ng = negative.peek() * -1;
                    int po = positive.peek();
                    if (ng == po || ng < po) {
                        sb.append(negative.poll()).append("\n");
                    } else {
                        sb.append(positive.poll()).append("\n");
                    }
                } else if (negative.isEmpty() && positive.isEmpty()) {
                    sb.append(0).append("\n");
                } else if (negative.isEmpty()) {
                    sb.append(positive.poll()).append("\n");
                } else if (positive.isEmpty()) {
                    sb.append(negative.poll()).append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
