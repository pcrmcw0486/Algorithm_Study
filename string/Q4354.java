/*
https://www.acmicpc.net/problem/4354
문자열 제곱 Platinum V
*** 접근 방법 ***
문제가 말이 어렵게 되어 있으나 결국은 
A*B와 같이 연결된 문자열이 있을 때
S안에서 그러한 문자열 패턴 반복될 때 가장 큰 패턴 횟수를 묻는것.
패턴횟수? > 아이디어는 KMP에서 따오도록 한다.
하지만 KMP의 경우 failure함수의 값은 index값을 기록함으로
패턴횟수와 동일하지 않다.
ex) 곱하기 > 연결되어 있음
    bcabcabc 라고 bc가 3번나온게 아니고 bca *3이되는게 맞음.
    또는 뭐 cab*3이겟지.abc*2
    즉, 패턴 연속성.
    abcabcabdabc? > abc *2가 맞음.
    abababacabab > ab가3번연속. 3이 답임.
    001234501234
    abcdefdefa라면? 이지만 def*2가됨.
    0000000001
KMP는 처음부터 비교해나아가기 때문에 해당 문제를
KMP와 연관해서 풀기는 어렵고 응용은 가능할듯?
응용 how?
 실패함수를 구하는 것도 KMP엿지.
 가 아니야 시발!
 문제를 잘 읽어봐
 s = a^n이라잖아. 즉 s는 어떠한 문자열 a의 반복으로 이루어져 
 있다.이말임.n=1이라면 s=a겟지.n=2면 abab와 같은 식임.
*** 알고리즘 자료구조 스킬 ***
*** 문제 조건 ***
 A = "abc"이고 B= "def"일 때
 A*B = "abcdef"로 문자열을 연결시키는 것이라고 보자.
 곱하기라면 제곱이 가능하다고 생각하고
 A^0 = 0 A^(n+1) = A*(A^n)이 된다.
 어떤 문자열 S가 주어질 때, 어떤 문자열 a에 대해서
 s = a^n을 만족하는 가장 큰 n을 찾는 프로그램
 */
package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4354 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = null;
        StringBuilder sb = new StringBuilder();
        while (true) {
            S = br.readLine();
            if (S.length() == 1 && S.charAt(0) == '.')
                break;
            int[] failure = new int[S.length()];
            for (int i = 1, j = 0; i < failure.length; i++) {
                while (j < 0 && S.charAt(i) != S.charAt(j))
                    j = failure[j - 1];
                if (S.charAt(i) == S.charAt(j)) {
                    failure[i] = ++j;
                }
            }
            // 반례 ababa -> failure[S.length()-1] = 3이라
            // 5/(5-3) = 2
            // 패턴이 홀수일 때 s=a^n을 만족하려면
            // 총 문자열에서 패턴을 뺏을 때 나머지로도 빠져야함.
            int ans = S.length() % (S.length() - failure[S.length() - 1]) != 0 ? 1
                    : S.length() / (S.length() - failure[S.length() - 1]);
            sb.append(ans).append("\n");
        }
        System.out.print(sb.toString());
        // System.out.println((int) (8 / 5));
    }

}
