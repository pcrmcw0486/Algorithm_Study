package RHS_FC.class05_graph;

/*
https://www.acmicpc.net/problem/2251
물통 Silver I
부피가 A, B, C (1~ 200)
처음 두 물통은 비어있고 세번째 물통은 가득 차 있다.
물통을 부을 때에는 물통이 비거나 다른 한 물통이 찰 때 까지 물을 부을 수 있다.
첫번째 물통이 비어있을 때 세번째 물통에 담겨 있을 수 있는 물의 양을 모두 구해내는 프로그램

 */
import java.io.*;
import java.util.*;

public class Q2251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        boolean[][][] state = new boolean[A + 1][B + 1][C + 1];
        int[] now = { 0, 0, C };
        ArrayList<Integer> answer = new ArrayList<Integer>();
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(now);
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int a = point[0];
            int b = point[1];
            int c = point[2];
            if (a < 0 || b < 0 || c < 0 || a > A || b > B || c > C || state[a][b][c])
                continue;

            state[a][b][c] = true;
            if (a == 0) {
                if (!answer.contains(c))
                    answer.add(c);
            }
            // 물 옮길때의 두가지 조건이 존재함.
            // 1. 받는 쪽이 넘지 않도록.
            // 2. 아니면 주는 쪽 모두
            // C->A
            // C->B
            if (c > 0) {
                int tmp = Math.min(A - a, c);
                queue.add(new int[] { a + tmp, b, c - tmp });
                tmp = Math.min(B - b, c);
                queue.add(new int[] { a, b + tmp, c - tmp });
            }
            // B->A
            // B->C
            if (b > 0) {
                int tmp = Math.min(A - a, b);
                queue.add(new int[] { a + tmp, b - tmp, c });
                tmp = Math.min(C - c, b);
                queue.add(new int[] { a, b - tmp, c + tmp });
            }
            // A->B
            // A->C
            if (a > 0) {
                int tmp = Math.min(B - b, a);
                queue.add(new int[] { a - tmp, b + tmp, c });
                tmp = Math.min(C - c, a);
                queue.add(new int[] { a - tmp, b, c + tmp });
            }
        }
        Collections.sort(answer);
        for (int a : answer) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

}
