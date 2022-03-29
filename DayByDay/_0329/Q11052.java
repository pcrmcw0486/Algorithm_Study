package DayByDay._0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q11052
 * @문제이름 : 카드 구매하기
 * @난이도 : Silver I
 * @date : 2022-03-29 오후 2:24
 * @author : pcrmcw0486
 * @문제이해
 * PS카드를 모아보자. 등급마다 8가지 색깔이 있음.
 * 카드 x개가 포함된 카드팩 총 x가지가 존재한다.
 * 카드의 개수가 적은 팩이더라도 가격이 비싸면 높은 등급이 있을 거라고 믿고 있다.
 * 돈을 최대한 많이 지불하여 카드 N개를 구매하려고 한다.
 *
 * @알고리즘

 * @접근방법

*/
public class Q11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            int cost = Integer.parseInt(st.nextToken());
            dp[i] = cost;
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }
        System.out.println(dp[N]);

    }
}
