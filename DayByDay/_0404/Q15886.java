package DayByDay._0404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Q15886
 * @문제이름 : 내 선물을 받아줘 2
 * @난이도 : Silver II
 * @date : 2022-04-04 오후 1:26
 * @author : pcrmcw0486
 * @문제이해
 * 구 사과 1*N 직사각형 지도, (1,x)
 * 지도 각 칸, E,W
 * E에 서있었다면 x+1, W라면 x-1로이동,
 * 최소 몇개의 칸 위에 선물을 놓으면 항상 선물을 가져갈까?
 * @알고리즘
 * 그루핑 해야되서 disjoint set 써도 되고,
 * dfs로 visited로 group체크해도되고,
 * @접근방법

*/
public class Q15886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split("WE");
        System.out.println(s.length);
    }
}
