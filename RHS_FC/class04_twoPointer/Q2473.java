package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/2473
세 용액 Gold IV
산성 1~1_000_000_000
알칼리 -1_000_000_000 ~ -1
같은 양의 세가지 용액 합성한 특성값은 세 용액 특성값 합
0에 가까운 용액을 만드려고 한다.
세개 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내셈
N은 3이상 5000
세개 합치면 int넘어가니까 long으로 계산하는게 일단 안전하겟다.
정렬 nlogn
특성값의 오름차순 출력. 아무것이나 하나 출력한다.
모든 값들에 대해 O(N) 검색 O(N) N^2인데
정렬 NlogN
N^2 + NlogN 시간은 될듯.
 */
import java.io.*;
import java.util.*;

public class Q2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] data = new long[N];
        long[] answer = new long[3];
        long ansMin = Long.MAX_VALUE;
        for (int i = 0; i < N; i++)
            data[i] = Long.parseLong(st.nextToken());

        for (int target = 0; target < N - 2; target++) {
            int l = target + 1;
            int r = N - 1;
            while (l < r) {
                long sum = data[r] + data[l] + data[target];
                if (Math.abs(sum) < ansMin) {
                    answer[0] = data[target];
                    answer[1] = data[l];
                    answer[2] = data[r];
                    ansMin = Math.abs(sum);
                }
                if (data[l] + data[r] > -data[target])
                    r--;
                else
                    l++;
            }
        }
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);

    }
}
