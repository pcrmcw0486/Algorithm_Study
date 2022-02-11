/** 
https://www.acmicpc.net/problem/1305
광고 Platinum V
*** 접근 방법 ***
문제를 단순화 하면 L 길이의 문자열 s가 주어질 때,
문자열 s안에서 찾을수 있는 가장 짧은 패턴?
접두사동일한경우 무시가능함..abcab의 경우
abc 와 ab의 접두사가 동일함
어쨋든 광고가 무한히 반복되기 때문에 
패턴이 0번이든, 한번이든 반복되게 되어 있음.
 주의할 점이 0번일 경우 두가지
  1) 광고가 끝나고 다시 나오고 있는 경우
     (패턴이 반복되고 있는 경우)
  2) 광고가 아직 덜 끝난 경우.
가 존재한다. 이 경우 KMP 실패함수를 통해
getPartialMatch라고 하던데 여튼, 마지막 값에서 
확인이 가능하다.
뒤에 이어올 것은 상관쓰지 않아도 된다.
현재 경우에서 만들 수 있는 가장 짧은 패턴이기 때문에.
접두사가 연결되어 마지막에 되어 있으면 ㄱㅊ 
*** 알고리즘 자료구조 스킬 ***
KMP사용.
*** 문제 조건 ***
길이가 N인 광고를 무한히 붙여 광고
전광판 길이는 L
if 광고가 abcad L이 8이면
abcadabc > bcadabca > cadabcad 와 같은 식임.
이때 전광판을 본 순간 보이는 문자로
만들 수 있는 가장 짧은 광고의 길이.
 */
package SolveByCategory.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String S = br.readLine();
        int[] failure = piResult(S);
        System.out.println(size - failure[size - 1]);
    }

    public static int[] piResult(String S) {
        int[] failure = new int[S.length()];
        for (int i = 1, j = 0; i < S.length(); i++) {
            while (j > 0 && (S.charAt(i) != S.charAt(j)))
                j = failure[j - 1];
            if (S.charAt(i) == S.charAt(j))
                failure[i] = ++j;
        }
        return failure;
    }
}
