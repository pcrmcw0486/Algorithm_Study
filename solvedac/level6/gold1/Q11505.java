package solvedac.level6.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q11505
 * @문제이름 : 구간 곱 구하기
 * @난이도 : Gold I
 * @date : 2022-03-11 오후 4:46
 * @문제이해 세그트리
 * @알고리즘
 * @접근방법
 */
public class Q11505 {
    static long[] data;
    static long[] tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        data = new long[N];
        tree = new long[4 * N];
        for (int i = 0; i < N; i++) data[i] = Long.parseLong(br.readLine());
        initTree(0, data.length - 1, 1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (option == 1) {
                long c = Long.parseLong(st.nextToken());
                update(0, data.length - 1, 1, b - 1,  c);
                data[b-1] = c;
            } else if (option == 2) {
                int c = Integer.parseInt(st.nextToken());
                sb.append(getValue(b - 1, c - 1, 0, data.length - 1, 1)).append('\n');
            }
        }
        System.out.print(sb);
    }

    public static long getValue(int queryLeft, int queryRight, int left, int right, int node) {
        if (queryRight < left || queryLeft > right) return 1L;
        if (queryLeft <= left && queryRight >= right) return tree[node];
        int mid = (left + right) / 2;
        return (getValue(queryLeft, queryRight, left, mid, node * 2) * getValue(queryLeft, queryRight, mid + 1, right, node * 2 + 1)) % MOD;
    }

    //leaf부터 올라오면됨.
    public static long update(int start, int end, int node, int index,long cur) {
        //찾아가는 범위 밖이면
        if(index < start || index > end) return tree[node];
        if(start == end) return tree[node] = cur;
        int mid = (start+end)/2;
        return tree[node] = (update(start, mid, node * 2, index, cur) * update(mid + 1, end, node * 2 + 1, index, cur))%MOD;
    }

    public static void initTree(int start, int end, int node) {
        if (start == end) {
            tree[node] = data[start];
            return;
        }
        initTree(start, (start + end) / 2, node * 2);
        initTree((start + end) / 2 + 1, end, node * 2 + 1);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }
}
