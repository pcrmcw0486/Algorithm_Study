/*단계별로 풀어보기 - 큐
카드2 (silver 4)
큐 이용 머리에 익히기 */
package toy.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Q2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deck = new LinkedList<>();
        for (int i = N; i > 0; i--) {
            deck.push(i);
        }
        while (deck.size() > 1) {
            deck.pop();
            deck.offerLast(deck.pop());
        }
        System.out.println(deck.pop());
    }
}
