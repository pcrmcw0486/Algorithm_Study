package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/13702
이상한 술집 Silver III
막걸리 시키면 주전자의 용량은 같은데 안에 막걸리 용량은 랜덤임.
N 주전자를 주문하여 K명에게 같은 양으로 나눠주려고함.
다른 주전자의 막걸리가 섞이는 것이 싫어서 분배후 주전자에게 막걸리가 남아있다면 버림.
5명이 3 주전자 주문 1002 802 705 401로 나누면
200 0 304 만큼은 버린다는 의미.
2 2 1
N 10_000 이하 K 1_000_000 이하의 정수
막걸리 용량은 2^31 - 1 보다 작거나 같은 자연수 또는 0이다.
N<=K임.

N개의 막걸리에서 X만큼 정량으로 나누어줄 때 K명을 줄 수 있는가? Yes/No 라는 문제로 변형
최대 막걸리 용량은?
한사람이 받을 수 있는 막걸리 용량 범위 [L...R] L : 0, R : 2^31-1 (한주전자가 한계)

*/
import java.io.*;
import java.util.*;

public class Q13702 {
    static int N;
    static int K;
    static int[] alcohol;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        alcohol = new int[N];
        for (int i = 0; i < N; i++) {
            alcohol[i] = Integer.parseInt(br.readLine());
        }
        long l = 0;
        long r = (long) Integer.MAX_VALUE;
        long ans = 0;
        while (l <= r) {
            long mid = (l + r) >> 1;
            if (determination(mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean determination(long mid) {
        int count = 0;
        if (mid == 0)
            return true;
        for (int i = 0; i < alcohol.length; i++) {
            count += alcohol[i] / mid;
        }
        return count >= K;
    }
}
