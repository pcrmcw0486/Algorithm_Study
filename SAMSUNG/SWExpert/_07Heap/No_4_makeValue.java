package SAMSUNG.SWExpert._07Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/*  --------- 수 만들기 ------------
* N개의 수와 K개 주어짐.
* X =0, D =1 이고 X의 값을 K로 만들어야함.
* 두가지 방법을 활용해서
* 1. X + D
* 2. D * (A1~~~An)중 하나
* X에 D를 더하는 횟수를 최소화 하면 몇번이 될까?
*
* 주어지는 N은 최대 10개
* K는 1<= K <= 10^9
* */
public class No_4_makeValue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(K, 0));
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if(cur.num == 0){
                    sb.append(cur.leftSum).append('\n');
                    break;
                }
                for (int j : input) {
                    int leftSum = cur.num % j;
                    int num = cur.num / j;
                    pq.add(new Node(num, cur.leftSum + leftSum));
                }
            }
        }
        System.out.print(sb.toString());
    }
    static class Node implements Comparable<Node>{
        int leftSum;
        int num;

        Node(int num, int leftSum) {
            this.num = num;
            this.leftSum = leftSum;
        }

        @Override
        public int compareTo(Node o) {
            return this.leftSum - o.leftSum;
        }
    }
}
