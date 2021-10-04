package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/1806
부분합 Gold IV
10,000 이하 자연수 길이 N 수열
그 합이 S 이상이 되는 것 중 가장 짧은 것의 길이
10<= N <= 100_000
0<S<= 100_000_000 int형에서 가능.
최소 길이를 출력하고 그 합이 없다면 0을 출력한다.
 */
import java.io.*;
import java.util.*;

public class Q1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int start = 0;
        int ans = Integer.MAX_VALUE;
        for (int end = 0; end < N; end++) {
            data[end] = Integer.parseInt(st.nextToken());
            sum += data[end];
            while (sum > S && start <= end) {
                ans = Math.min(ans, end - start + 1);
                sum -= data[start];
                start++;
            }
        }
        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }
}
