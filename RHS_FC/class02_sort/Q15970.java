/*
화살표 그리기 Silver IV
음수가 아닌 정수들.
색깔은 1~N
점들의 위치 0~10^5
2<= N<=5000

정렬 시 class를 생성해서 하는 방법이 이미
sorting. (번호에 따라 sorting해야하고)
번호의 개수는 모름.

자료형은 long

ans += Math.min(pre, next)
next = 새로 계산.
pre = next

자료구조는?
일단 sorting을 하려면 각각 따로 받긴 해야함.
그 방식이 아니라면? int[]로 받아서
0 1 
3 1
4 1 
6 1 
9 1
7 2
10 2
의 방식으로 정렬한다.
다음 1과 2가 달라질 경우
next계산 말고 pre를 더해주고 다시 초기화한 뒤 계산을 해야하는데,
항상 고려할 것이 
 1. 바뀌는 부분
 2. 시작 및 끝 부분임.
        pre    next
 0 1   MAX  3-0
 3 1    3       X
 4 2   MAX  2
 6 2
=============
 + pre
  
 */
package RHS_FC.class02_sort;

import java.io.*;
import java.util.*;

public class Q15970 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Points[] datas = new Points[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            datas[i] = new Points(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(datas);
        long pre = Long.MAX_VALUE;
        long next = 0L;
        long answer = 0;
        for (int i = 0; i < datas.length - 1; i++) {
            if (datas[i].color != datas[i + 1].color) {
                answer += pre;
                pre = Long.MAX_VALUE;
                continue;
            }
            next = datas[i + 1].value - datas[i].value;
            answer += Math.min(pre, next);
            pre = next;
        }
        answer += pre;
        System.out.println(answer);

    }

    private static class Points implements Comparable<Points> {
        long value;
        int color;

        Points(long value, int color) {
            this.value = value;
            this.color = color;
        }

        @Override
        public int compareTo(Points o) {
            if (color == o.color) {
                return (int) (value - o.value);
            }
            return color - o.color;
        }
    }
}
