package DayByDay._0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2578
 * @문제이름 : 빙고
 * @난이도 : Silver V
 * @date : 2022-03-29 오후 1:31
 * @author : pcrmcw0486
 * @문제이해
 *
 * @알고리즘

 * @접근방법

*/
public class Q2578 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        Point[] pos = new Point[26];
        int[] row = new int[5];
        int[] col = new int[5];
        int[] diagonal = new int[2];
        int idx = 1;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());
                pos[num] = new Point(i, j);
            }
        }
        int callCnt =0;
        int lineCnt =0;
        boolean success = false;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                Point p = pos[Integer.parseInt(st.nextToken())];
                callCnt++;
                row[p.x]++;
                col[p.y]++;
                if (p.x - p.y == 0) {
                    diagonal[0]++;
                    if(diagonal[0] == 5)
                        lineCnt++;
                }
                if(p.x + p.y == 4){
                    diagonal[1]++;
                    if(diagonal[1]==5)
                        lineCnt++;
                }
                if (row[p.x] == 5)
                    lineCnt++;
                if(col[p.y] == 5)
                    lineCnt++;
                if (lineCnt >= 3)
                    break;
            }
            if (lineCnt >= 3)
                break;
        }

        System.out.println(callCnt);
    }
    static class Point{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
