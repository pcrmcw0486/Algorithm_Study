package DayByDay._0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2792
 * @문제이름 : 보석 상자
 * @난이도 : Silver II
 * @date : 2022-04-16 오전 5:39
 * @author : pcrmcw0486
 * @문제이해
 * 보석은 M가지 서로 다른 색상 중 한 색상
 * 보석을 N명에게 (보석을 받지 못하는 학생이 있어도 가능, 학생은 항상 같은 색의 보석만 가진다.)
 * 질투심 : 가장 많은 보석을 가져간 학생이 가진 보석 개수.
 * 질투심이 최소가 되도록
 * 아이들의 수 너무 많음. 색상수 너무 많음
 * K번째 줄에 주어지는 K번 색상 보석의 수
 * 아이들 5에 색상 2개 (7, 4)
 * 아이들이 갖지 못해도 도지만, 항상 같은색의 보석만 가진다.
 * 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 수
 * 모든 보석을 나누어야함.
 * 최대 3개를 가져야함
 * 3 3 2 1 3 3 2 4 4(count넘음)
 * 최대 4개를 가져야함
 * 4 3 1 4 3 4 4  (질투심 : 4)
 * 최대 5?
 * 5 2 1 5 2 4 4 (질투심 : 5)  count가능
 *
 * 1 7  최대4개
 * 4 3 4 (3) 0 0  질투심 4 count3 너무 적다 더 고르게 더 낮춰보자
 * 1 4 > 2
 * 2 2 2 1 2 2 (count넘음 높아야함)
 * 3 4 > 4
 * 3 3 1 3 1 (5)
 * 가능.
 * @알고리즘
 * 이분탐색.
 * @접근방법

*/
public class Q2792 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] data = new int[M];
        int left = 1;
        int right = 1;
        for (int i = 0; i < M; i++) {
            data[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, data[i]);
        }

        int ans =0;
        int mid;
        while (left <= right) {
            mid = (left+right)>>1;
            int count =0;
            for (int i = 0; i < M; i++) {
                count += data[i]/mid;
                if(data[i]%mid !=0) count++;
            }
            if (count <= N) {
                right =mid-1;
            }else{
                left = mid +1;
            }
        }
        System.out.println(left);



    }
}
