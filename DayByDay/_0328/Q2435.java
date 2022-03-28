package DayByDay._0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2435
 * @문제이름 : 기상청 인턴 신현수
 * @난이도 : Silver V
 * @date : 2022-03-28 오후 4:25
 * @author : pcrmcw0486
 * @문제이해
 * 측정한 온도가 어떤 정수의 수열로 주어질 때 연속적인 며칠 동안의 온도 합이 가장 큰값.
 * @알고리즘

 * @접근방법

*/
public class Q2435 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] data = new int[N];
        int sum=0;
        int max = Integer.MIN_VALUE,cnt =0;
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
            if (i < K) {
                sum += data[i];
            }
        }
        max = sum;
        for (int i = K; i < N; i++) {
            sum = sum - data[i-K] + data[i];
            max = Math.max(sum, max);
        }
        System.out.println(max);


    }
}
