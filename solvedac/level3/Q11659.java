package solvedac.level3;
/*
2021.11.05
문제번호 : Q11659
이름 및 난이도 : 구간 합 구하기 4
문제이해 
-----------------
수 N개가 주어졌을 때 i번째 수부터 j번째 수 까지 합을 구하는 프로그램.
접근 방법 :
아직까지는 그냥 twoPointer로 처리하면안되네.
또는 누적합 해놓고 빼면됨
즉 S(N) = 0~N까지의 합
 이기 때문에 i~j 라면
 S(j) - S(i-1)
  0 1 2 3 4 5 6 ..    i .... j (S(j)
- 0 1 2 3 45 6,,. i-1           (S(i-1))
100_000 번 들어오는데 1~100_000 하면 
10억을 넘어가네.
제한 조건 : 
N ~ 100_000
M~ 100_000
10^10까지니까 long이 편할듯.
*/

import java.io.*;
import java.util.*;

public class Q11659 {
    public static void main(String[] args) throws IOException {
        // S(0) : 0
        // S(1) == data[0]부터 들어가야함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] S = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            S[i] += S[i - 1] + Long.parseLong(st.nextToken());
        }
        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(S[end] - S[start - 1]).append('\n');
        }
        System.out.print(sb.toString());
    }
}
