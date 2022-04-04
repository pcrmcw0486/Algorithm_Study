package DayByDay._0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @문제번호 : Q2616
 * @문제이름 : 소형 기관차
 * @난이도 : GOLD IV
 * @date : 2022-04-04 오후 2:13
 * @author : pcrmcw0486
 * @문제이해
 * 기차는 맨 앞에 기관차 1대가 손님이 탄 객차 여러칸을 끌고 간다.
 * 몇몇 역에 소형 기관차 3대를 배치하기로 결정했다. 소형기관차는 평소 보다 적은 수의 객체만 끈다.
 * 1. 소형 기관차가 최대로 끌 수 있는 객차의 수를 미리 정하고, 그보다 많은 수의 객차를 절대로 끌게 하지 않는다.
 *    3대의 소형 기관차가 최대로 끌 수 있는 객차의 수는 같다.
 * 2. 소형 기관차 3대를 이용하여 최대한 많은 손님을 목적지까지 운송하도록 한다. 손님의 수는 미리 알고 있다.
 * 3. 번호가 연속적으로 이어진 객차를 끌게 한다. 객차는 기관차 바로 뒤 객차 부터 1부터 번호가 있다.
 *
 * 객차의 수는 50000이하이다.
 * 소형 기관차가 최대로 끌 수 잇는 객차의 수.
 * 나올 수 있는 경우의수 객차수 N에 대해 끌 수 있는 객차수 K라면
 * N-K개의  범위가 나옴.
 * 75  90 60 40 75 115
 * 첫 차 는 최대 0 ~  N-3K부터 시작해야함.
 * 총 가짓수 는 N-3K개수가 됩니다.7 - 2*3 = 1  앞자리칸이 올 수 있는건 2가지 경우.
 * @알고리즘

 * @접근방법

*/
public class Q2616 {
    static int[][] dp;
    static int[] prefixSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int K = Integer.parseInt(br.readLine());
        prefixSum = new int[N+1];
        for (int i = 1; i < N + 1; i++) {
            prefixSum[i] = prefixSum[i-1] + data[i-1];
        }
        for (int i : prefixSum) {
            System.out.println(i);
        }
        dp = new int[4][N+1];
        for (int i = 1; i < 4; i++) {
            for (int j = i * K; j < N + 1; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - K] + prefixSum[j] - prefixSum[j - K]);
            }
        }

        System.out.println(dp[3][N]);
    }
}
