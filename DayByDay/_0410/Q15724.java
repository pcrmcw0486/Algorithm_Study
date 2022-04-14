package DayByDay._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q15724
 * @문제이름 : 주지수
 * @난이도 : Silver I
 * @date : 2022-04-10 오후 3:48
 * @author : pcrmcw0486
 * @문제이해
 * 일정한 직사각형 범위 내 살고 있는 사람 수를 알고 싶다.
 *
 * @알고리즘
 *
 * @접근방법
  
*/
public class Q15724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][M+1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = map[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                map[i][j] += map[i-1][j];
            }
        }
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int cnt = map[r2][c2]-map[r1-1][c2]-map[r2][c1-1]+map[r1-1][c1-1];
            sb.append(cnt).append('\n');
        }
        System.out.print(sb.toString());
    }
}
