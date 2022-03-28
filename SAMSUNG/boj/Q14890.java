package SAMSUNG.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14890
 * @문제이름 : 경사로
 * @난이도 : Gold III
 * @date : 2022-03-28 오전 12:08
 * @author : pcrmcw0486
 * @문제이해
 * 크기 N*N 지나갈 수 있는 길 찾기.
 * 경사로는 낮은칸에 놓이며 L개의 연속된 칸에 경사로 바닥이 모두 접해야한다.
 * 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어야 한다.
 * Naiive하게 한다면 그냥 쭉 탐색하면서 맞추면되는거아닌가?
 * 차이가 나는 지점으로 부터 L개가 같아야 한다.
 * | _______|는 어떻게 구현할 수 있을까
 * stack
 * 두 가지 경우가 있어요.
 * 큰 -> 낮 과 낮 -> 큰이 있는데,
 * 큰 -> 낮 : diff가 먼저 나오고 L을 계산해야한다. prev-cur == 1
 * 낮->큰 :  그전에 샇여있고 diff가 나온다.       prev-cur == -1
 * 즉, 이 두 경우를 분리해서 보거나, 한번에보려면 새로운 방법이 필요하다.
 *
 * @알고리즘

 * @접근방법

*/
public class Q14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] data = new int[N][N];
        int ans =0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //가로줄
        boolean flag= true;

        System.out.println(ans);
    }
}
