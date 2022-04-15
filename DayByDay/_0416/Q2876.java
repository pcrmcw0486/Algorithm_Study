package DayByDay._0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2876
 * @문제이름 : 그래픽스 퀴즈
 * @난이도 : Silver II
 * @date : 2022-04-16 오전 5:58
 * @author : pcrmcw0486
 * @문제이해
 * N개의 책상(책상당 두명)
 * grade -> 색상표현(A빨강BCD빨강 X)
 * 두 책상을 선택, 두 책상과 그 사이 모든 책상에서
 * 각각 한명씩 지목하여 질문하고 답변
 * 한 가지 색의 색연필만 가져왓다.
 * 지목한 모두에게 같은 그레이드를 준다.
 * 교수님이 채점할 수 있는 학생의 수?
 * 연속해야함.
 * @알고리즘

 * @접근방법

*/
public class Q2876 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] desk = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            desk[i][0] = Integer.parseInt(st.nextToken());
            desk[i][1] = Integer.parseInt(st.nextToken());
        }
        int max =0;
        int grade =0;
        for (int i = 5; i > 0; i--) {
            int gradeMaxCnt =0;
            int count =0;
            for (int j = 0; j < N; j++) {
                if (desk[j][0] == i || desk[j][1] == i) {
                    count++;
                }else{
                    gradeMaxCnt = Math.max(count, gradeMaxCnt);
                    count =0;
                }
            }
            gradeMaxCnt = Math.max(count, gradeMaxCnt);
            if (gradeMaxCnt >= max) {
                max = gradeMaxCnt;
                grade = i;
            }
        }
        System.out.println(max + " " + grade);
    }
}
