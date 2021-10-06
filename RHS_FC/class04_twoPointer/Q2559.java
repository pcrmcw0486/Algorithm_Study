package RHS_FC.class04_twoPointer;

/*
https://www.acmicpc.net/problem/2559
수열 Silver III 
매일 아침 9 시 학교 온도 정수 수열이 주어짐.
며칠동안 온도의 합이 가장 큰 값을 알아보고자함.
연속적인 K일 온도의 합이 최대가 되는 값을 구한다.
쉽게 Naiive하게 생각하면 O(N*K)
N-K 일 * O(K) 
0일 때 0~4
1일때 1~5로 for문으로 계속 계산할 수 있으나
자세히보면 연속적이기 때문에 O(N)으로 값의 변화를 통해 값 갱신만 하면 
조금 더 효율적으로 구할 수 있다.
N <100_000
1< K < N
모든 수는 -100 < < 100
N의 모든 경우를 다 더하여도 int형 범위 내에서 계산 가능함.
 */
import java.io.*;
import java.util.*;

public class Q2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] temper = new int[N];
        int sum = 0;
        int ans = -100 * N;
        for (int i = 0; i < N; i++) {
            temper[i] = Integer.parseInt(st.nextToken());
            sum += temper[i];
            if (i >= K - 1) {
                ans = Math.max(sum, ans);
                sum -= temper[i - K + 1];
            }
        }
        System.out.println(ans);

    }
}