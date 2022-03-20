package solvedac.level6.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q2367
 * @문제이름 : 최솟값과 최댓값
 * @난이도 : GoldI
 * @date : 2022-03-10 오후 10:07
 * @문제이해 N개의 정수들에 대해 a정수부터 b번째 정수까지 제일 작은 정수 혹은 제일 큰 정수를 빠르게 찾아보자
 * 각각의 정수는 int값.
 * @알고리즘 세그먼트 트리.
 * @접근방법
 */
public class Q2367 {
    static int[] data;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }
        tree = new Node[data.length * 4];
        init(0, data.length - 1, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            int max = getMax(1, 0, data.length - 1, left, right);
            int min = getMin(1, 0, data.length - 1, left, right);
            sb.append(min).append(' ').append(max).append('\n');
        }
        System.out.print(sb);
    }

    public static int getMax(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) return -1;
        if (queryLeft <= left && queryRight >= right) return tree[node].max;
        int mid = (left + right) / 2;
        return Math.max(getMax(node * 2, left, mid, queryLeft, queryRight),
                getMax(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    public static int getMin(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryLeft > right || queryRight < left) return Integer.MAX_VALUE;
        if (queryLeft <= left && queryRight >= right) return tree[node].min;
        int mid = (left + right) / 2;
        return Math.min(getMin(node * 2, left, mid, queryLeft, queryRight),
                getMin(node * 2 + 1, mid + 1, right, queryLeft, queryRight));
    }

    //트리의 index와 범위의 index는 별개이다.
    public static void init(int start, int end, int node) {
        if (start == end) {
            tree[node] = new Node(data[start], data[start]);
//            tree[node].max = data[start];
//            tree[node].min = data[start];
        } else {
            init(start, (start + end) / 2, node * 2);
            init((start + end) / 2 + 1, end, node * 2 + 1);
            int max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);
            int min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
            tree[node] = new Node(max, min);
        }
    }

    static class Node {
        int max, min;

        public Node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }
}
