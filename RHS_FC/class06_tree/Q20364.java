package RHS_FC.class06_tree;

/*
https://www.acmicpc.net/problem/20364
부동산 다툼 Silver II
1. 루트 땅의 번호는 1
2. 어떤 땅 번호 K라면 자식땅의 번호 2*K, 오른쪽 2*K +1
오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 있다면
못 지나감.

가려고 하는 땅의 부모가 점유당해있다면 못간다는 의미로 이해할 수 있다.
1까지 /2/2/2
 */
import java.io.*;
import java.util.*;

public class Q20364 {
    static boolean[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        tree = new boolean[N + 1];
        int Q = Integer.parseInt(st.nextToken());
        for (int i = 0; i < Q; i++) {
            int wantedNumber = Integer.parseInt(br.readLine());
            int tmp = wantedNumber;
            int ans = 0;
            while (tmp > 0) {
                if (tree[tmp])
                    ans = tmp;
                tmp /= 2;
            }
            if (ans == 0)
                tree[wantedNumber] = true;
            sb.append(ans).append('\n');

        }
        System.out.print(sb);
    }
}
