package solvedac.level4;

/*
2021.12.09
문제번호 : Q15663
이름 및 난이도 : N과 M(9)
문제이해 
-----------------
N개의 자연수와 자연수 M이 주어질 때
N개의 자연수 중에서 M개를 고른 수열
을 만족하는 모든 수열을 구하시오. 
수열은 사전 순으로 증가하는 순서로 출력해야 한다.
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q15663 {
    static TreeMap<Integer, Integer> treeMap;
    static StringBuilder sb;
    static int N, M;
    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        treeMap = new TreeMap<Integer, Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        ans = new int[M];
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            treeMap.merge(v, 1, Integer::sum);
        }
        solution(0);
        System.out.print(sb.toString());
    }

    public static void solution(int depth) {
        if (depth == M) {
            for (int a : ans) {
                sb.append(a).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            int v = entry.getValue();
            int k = entry.getKey();
            if (v > 0) {
                ans[depth] = k;
                treeMap.put(k, v - 1);
                solution(depth + 1);
                treeMap.put(k, v);
            }
        }
        // for (int key : treeMap.keySet()) {
        // if (treeMap.get(key) > 0) {
        // ans[depth] = key;
        // treeMap.merge(key, -1, Integer::sum);
        // solution(depth + 1);
        // treeMap.merge(key, 1, Integer::sum);
        // }
        // }
    }
}
