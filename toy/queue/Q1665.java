package toy.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q1665 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> min_heap = new PriorityQueue<>((o1, o2) -> o1 - o2);
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int item = Integer.parseInt(br.readLine());
            if (min_heap.size() == max_heap.size())
                max_heap.offer(item);
            else
                min_heap.offer(item);

            if (!min_heap.isEmpty() && !max_heap.isEmpty()) {
                if (min_heap.peek() < max_heap.peek()) {
                    int tmp = min_heap.poll();
                    min_heap.offer(max_heap.poll());
                    max_heap.offer(tmp);
                }
            }
            sb.append((max_heap.peek())).append("\n");
        }
        System.out.println(sb.toString());

    }
}
