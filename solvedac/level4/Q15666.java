package solvedac.level4;
/*
2021.12.09
문제번호 : Q15666
이름 및 난이도 : N과 M(12)
문제이해 
-----------------
N개의 자연수와 M이 주어질 때 아래 조건을 만족하는 길이가 M인 수열을
 - N개의 자연수 중 M개를 고른다.
 - 중복 허용
 - 비 내림차순  A1 <= A2 <=
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q15666 {
    static int N, M;
    static int[] arr, ans;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ans = new int[M];
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        solution(0, 0);
        System.out.print(sb.toString());
    }

    public static void solution(int depth, int idx) {
        if (depth == M) {
            for (int a : ans) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        int lastNum = -1;
        for (int i = idx; i < arr.length; i++) {
            if (lastNum != arr[i]) {
                lastNum = arr[i];
                ans[depth] = arr[i];
                solution(depth + 1, i);
            }
        }
    }
}
