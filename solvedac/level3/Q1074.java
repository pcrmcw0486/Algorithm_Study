package solvedac.level3;
/*
2021.12.02
문제번호 : Q1074
이름 및 난이도 :  Z Silver I
문제이해 
-----------------
크기가 2^N * 2^N인 2차원 배열 Z 모양 탐색.
탐색방법   왼위 오위 왼아래 오아래. 재귀적으로 순서대로 방문한다.
이때 r행 c열을 몇번째로 방문하는지 출력하는 프로그램.
반으로 나누면 N개씩 나옴.
접근 방법 :
Naiive 하게 접근하면 다 탐색하면서 찾는 방법이 있을 수 있고
그게 아니라면 선 계산을 통해 구하는 방법이 있을 수 있음. 
R행 C열 포함 관계를 통해 더하는 방법으로 구하는 방법이 있을 수 있음.
시작 지점에서 자신의 위치에 따라 작은 단위 * 정해진 크기 이기 때문에.
이 방법으로 가본다.
결론은 다 볼 필요가 없다는게 결론임.
제한 조건 : 
N 이 15
2^30 == (10^3)^3

생각을 조금 더 정확하게 하고 짜야된다. 오래만에 풀어서 그런가 너무 막 접근했던 것 같다.
생각에 그치지 말고 구체화 시켜 작성하도록 하자.
*/

import java.io.*;
import java.util.*;

public class Q1074 {
    static int N, R, C;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        findPoint(0, 0, N);
        System.out.println(cnt);
    }

    public static void findPoint(int X, int Y, int N) {
        if (N == 0)
            return;
        int blockSize = (int) Math.pow(2, 2 * N - 2);
        int len = (int) Math.pow(2, N - 1);
        int index = -1;
        if (R < X + len && C < Y + len) {
            // case 0
            index = 0;
            findPoint(X, Y, N - 1);
        } else if (R < X + len && C < Y + 2 * len) {
            // case 1
            index = 1;
            findPoint(X, Y + len, N - 1);
        } else if (R < X + 2 * len && C < Y + len) {
            // case 2
            index = 2;
            findPoint(X + len, Y, N - 1);
        } else if (R < X + 2 * len && C < Y + 2 * len) {
            // case 3
            index = 3;
            findPoint(X + len, Y + len, N - 1);
        }
        // System.out.println(index + " " + blockSize);
        cnt += index * blockSize;
    }
}
