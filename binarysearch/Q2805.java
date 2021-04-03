package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int trees[] = new int[N];
        long right = -1;
        long left = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, trees[i]);
        }
        right--;
        while (left < right) {
            long tree_sum = 0;
            long middle = (left + right + 1) / 2;
            for (int i = 0; i < N; i++) {
                if (trees[i] > middle)
                    tree_sum += trees[i] - middle;
            }
            if (tree_sum >= M) {
                left = middle;
            } else {
                right = middle - 1;
            }

        }

        // 다른 방법
        // point는 답에 근접했을 때 인데
        // 박스를 그려서 박스가 3개있을 때
        // ㅁ ㅁ ㅁ ㅁ ㅁ
        // l m r l r
        // 이라고 했을 때를 생각해서 하나의 경우를 골라
        // 정해야 무한루프가 걸리지 않음.
        long answer = 0;
        while (left < right) {
            long mid = (left + right) / 2;
            long tree_sum = 0;
            // 계산
            if (tree_sum < M) {
                right = mid;
            } else if (tree_sum >= 0) {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(right);

    }
}
