package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/2230
수 고르기 Gold V
N개의 정수에서 두 수를 골랐을 때
그 차이가 M 이상이면서 제일 작은 경우를 구하는 프로그램.
N 100_000
M 2_000_000_000
각수는 1_000_000_000
 */
import java.io.*;
import java.util.*;

public class Q2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        for (int i = 0; i < N; i++)
            data[i] = Integer.parseInt(br.readLine());
        Arrays.sort(data);
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 1; l < N; l++) {

            while (r < N) {
                int diff = Math.abs(data[r] - data[l]);
                if (diff >= M) {
                    ans = Math.min(ans, diff);
                    break;
                }
                r++;
            }
        }
        System.out.println(ans);
    }
}
