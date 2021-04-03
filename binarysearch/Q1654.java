/*단계별로 풀어보기 - Binary Search
를 활용한 parametric search
주어진 조건을 만족하는 범위내에서의 최댓값 또는 최솟값을 구함
문제를 결정무넺로 정하여 이분탐색으로 해결.

 */
package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
    static int[] data;
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int max = -1;
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, data[i]);
        }

        solution(max);
    }

    public static void solution(int max) {
        long left = 1;
        long right = max;
        long middle = 0;
        // System.out.println(K);

        while (left <= right) {
            // System.out.println("left" + left + " right " + right);
            middle = (left + right) / 2;
            long c = count(middle);
            // System.out.println(middle + " " + c);
            if (c >= K) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        System.out.println(right);
    }

    public static long count(long num) {
        long result = 0;
        for (int i = 0; i < N; i++) {
            result += data[i] / num;
        }

        return result;
    }
}
