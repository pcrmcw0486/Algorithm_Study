/*
https://www.acmicpc.net/problem/1786
*** 찾기 Gold I ***
*** 접근 방법 ***
> 문자열 매칭 문제 using KMP
*** 알고리즘, 자료구조, 스킬 ***
KMP 알고리즘.
*** 문제 조건 ***
- 문제의 이해
 문자열 P와 T에 대해 P가 문자열 T 중간에 몇 번, 어느 위치에서 나타나는지 알아내는 문자열 매칭
 P를 pattern, T를 text라고 하자.
 T의 길이를 n, P의 길이를 m이라고 하자. 일반적으로, n>=m 으로 가정해도 무리가 없다.
 단순하게 문자열 매칭 비교를 하게 되면 T시작 지점, P시작 ~종료지점 모두 비교를 해야하므로
 O(nm)이 된다. 구체적으로는 n-m+1, m 이니까 O((n-m+1)*m)
이에 대해 KMP 알고리즘은 실패함수를 통해 처음 시작지점이 아닌, 패턴안의 패턴을 분석하여
새로운 시작지점에 대해 효율적으로 선택할 수 있도록 한다.
이 때, 이러한 실패함수를 만드는데에 O(m), 비교하는데 O(n)이므로
O(n+m)의 시간복잡도로 해결 가능하다.
 */

package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1786 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine();
        String P = br.readLine();
        int[] failure = new int[P.length()];

        for (int i = 1, j = 0; i < P.length(); i++) {
            while (j > 0 && P.charAt(i) != P.charAt(j))
                j = failure[j - 1];
            if (P.charAt(i) == P.charAt(j))
                failure[i] = ++j;
        }
        int ansCnt = 0;
        ArrayList<Integer> ansList = new ArrayList<>();
        for (int j = 0, i = 0; i < T.length(); i++) {
            while (j > 0 && T.charAt(i) != P.charAt(j))
                j = failure[j - 1];
            if (T.charAt(i) == P.charAt(j)) {
                if (j == P.length() - 1) {
                    ansList.add(i - P.length() + 2);
                    ansCnt++;
                    j = failure[j];
                } else
                    j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ansCnt).append("\n");
        for (int ans : ansList) {
            sb.append(ans).append(" ");
        }
        System.out.println(sb.toString());
    }

}
