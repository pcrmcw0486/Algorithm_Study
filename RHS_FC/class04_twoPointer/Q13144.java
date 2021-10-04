package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/13144
List of Unique Numbers Gold III
길이가 N인 수열이 주어질 때, 수열에서 연속한 1개 이상의 수를 뽑았을 때 
같은 수가 여러 번 등장하지 않는 경우의 수를 구하라.

 */
import java.io.*;
import java.util.*;

public class Q13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] data = new int[N];
        int[] cnt = new int[100001];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        long ans = 0;
        for (int L = 0, R = -1; L < N; L++) {
            while (R + 1 < N && cnt[data[R + 1]] == 0) {
                R++;
                cnt[data[R]]++;

            }
            ans += R - L + 1;
            cnt[data[L]]--;
        }

        System.out.println(ans);

    }
}
