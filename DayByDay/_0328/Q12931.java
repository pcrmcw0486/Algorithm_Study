package DayByDay._0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q12931
 * @문제이름 : 두 배 더하기
 * @난이도 : Gold Iv
 * @date : 2022-03-28 오후 5:25
 * @author : pcrmcw0486
 * @문제이해
 * 모든 값이 0 으로 채워져 있는 길이가 N인 배열 A가 있다
 * - 배열에 있는 값 하나를 1 증가 시킨다.
 * - 배열에 있는 모든 값을 두배 시킨다.
 * 배여 ㄹA를 B로 만들기 위한 연산의 최소 횟수.
 * 배열의 크기 N = 50
 * 배열의 위치가 중요한가?
 * 배열 언소ㅡ 크기는 0 ~ 1000
 * log 1000 = 10정도.
 * @알고리즘

 * @접근방법

*/
public class Q12931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }
        int multiCnt =0;
        int plusCnt =0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            multiCnt++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if(cur%2==1)
                    plusCnt++;
                if (cur / 2 != 0) {
                    queue.add(cur / 2);
                }
            }
        }
        System.out.println(multiCnt + plusCnt -1);
    }
}
