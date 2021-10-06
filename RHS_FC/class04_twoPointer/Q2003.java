package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/2003
수들의 합2 Silver III
N개의 수가 있을 때 i번째 수 부터 j번째 수 까지 합이 M이 되는 경우의 수를 구하시오.
N<10_000
M300_000_000 int형 가능.
 */
import java.io.*;
import java.util.*;

public class Q2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        int count = 0;
        int sum = 0;
        for (int l = 0, r = 0; r < N; r++) {
            sum += data[r];
            if (sum == M) {
                count++;
            }
            while (sum > M) {
                sum -= data[l];
                l++;
                if (sum == M)
                    count++;
            }

        }
        System.out.println(count);
    }
}
