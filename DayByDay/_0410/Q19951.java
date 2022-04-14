package DayByDay._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q19951
 * @문제이름 : 태상이의 훈련소 생활
 * @난이도 : Gold V
 * @date : 2022-04-10 오후 3:57
 * @author : pcrmcw0486
 * @문제이해
 * 흙을 옮기는 일.
 * 일렬로 이어진 N개의 칸으로 이루어져 있으며, 각 칸마다 높이를 가지고있음.
 * 조교는 M명.
 * a번 칸 부터 b번 칸까지 높이 K만큼 흙을 덮거나 파라고 (흙은 주변산에서 구할 수 있음)
 * 조교의 지시를 모아 한번에 수행해보자.
 *
 * @알고리즘
 * 변화량 누적 알고리ㅏ즘.
 * @접근방법

*/
public class Q19951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ground = new int[N+1];
        int[] preSum = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            ground[i] = Integer.parseInt(st.nextToken());
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            preSum[a]+=k;
            preSum[b+1]-=k;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            preSum[i] += preSum[i - 1];
            sb.append(ground[i] + preSum[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
