package solvedac.level4;
/*
2021.12.09
문제번호 : Q11660
이름 및 난이도 : 구간합 구하기 5 Silver I
문제이해 
-----------------
N*N 크기 표 (x1,y1) ~(x2,y2)까지 합을 구하는 프로그램.
그냥 빡 구현인가..?

접근 방법 :
Naiive하게 하면 최악의 경우 10^11 ... O(N^2 * M)
구간합을 빠르게 구해야함.
 1. 여러 방법이 있을 수 있음
    지금 가장 먼저 떠오르는 건 역시 '누적 합'
    x1 ~  x2 인 경우 Data[x2] - Data[x1-1]
    인 O(1)을 y2-y1까지 구하면 됨. 최악의 경우 O(N)이라
    결국 O(N*M)에 해결이 가능하다.
2. 누적 합 OK 쉽게 할거야?
    S(i,j) = i,j로 만든 사각형의 총합이라고 계산하면
   S(i,j)   S(i,j+1)
   S(i+1,j),S(i+1,j+1)
   S(i+1,j+1) = S(i,j+1) + S(i+1,j) - S(i,j) 가된다.
   이를 이용하여 query에 대해 바로 접근하여 구할 수 있게 됨.
   더빠르게 할 수 있다.
    

제한 조건 : 
표 크기가 최대 1024 * 1024 = 2^20 = 10^6
쿼리개수가 최대 10^5
들어가는 수를 확인해보면 1000보다 작거나 같은 자연수이다.
10^6 * 10^3 = 10^9 (int형 가능.)

*/

import java.io.*;
import java.util.*;

public class Q11660 {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        // solution1();
        solution2();
    }

    public static void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] data = new int[N + 1][N + 1];
        // data input
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                data[i][j] = data[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        // query
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int j = x1; j < x2 + 1; j++) {
                sum += data[j][y2] - data[j][y1 - 1];
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] data = new int[N + 1][N + 1];
        // data input
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                data[i][j] = data[i][j - 1] + data[i - 1][j] - data[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sum = data[x2][y2] - data[x2][y1 - 1] - data[x1 - 1][y2] + data[x1 - 1][y1 - 1];
            sb.append(sum).append("\n");
        }
        System.out.print(sb.toString());

    }
}
