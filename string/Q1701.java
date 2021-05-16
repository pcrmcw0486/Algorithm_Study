/*
https://www.acmicpc.net/problem/1701
*** Cubeditor Gold II ***
*** 접근 방법 ***
부분 문자열을 구할때 항상 배열의 앞은
 패턴의 시작과 같다.
 주어진 문자열은 임의의 문자열이기 때문에 패턴의 시작이
 맞을수도, 아닐 수도 있다
 문제에서 문자열 최대길이가 5000이므로
 실패함수 구하는데 O(2n) * n번 
 O(2 * n^2)정도? 면 5천만. 가능할지도?
 구해진 배열 값중 가장 큰 값이 해당 문자열에서 가장 긴 패턴임.
 0이 아닌이상, 어차피 두번 나오게 되어 있음.
*** 알고리즘 자료구조 스킬 ***
KMP
*** 문제 조건 ***
주어진 문자열 s에 속한 어떠한 부분 문자열p가
두번 이상 나올 때, p 최대 길이를 구하라.
접두사와 접미사가 같은 가장 긴 길이.
 [   [  ]  ] 와 같이 겹쳐도 된다.


 >> 처음에는 String으로 그냥 구했는데 char배열이 훨씬 빠르다.
     로 받아서 ArrayList
 

 */
package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1701 {
    static int MAX = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 처음
        // String S = br.readLine();
        char strArr[] = br.readLine().toCharArray();
        ArrayList<Character> S = new ArrayList<Character>();
        for (int i = 0; i < strArr.length; i++)
            S.add(strArr[i]);

        for (int i = 0; i < strArr.length - 1; i++) {
            solution(S);
            S.remove(0);
        }
        System.out.println(MAX);
    }

    // 그에 맞게 변경.
    public static void solution(ArrayList<Character> S) {
        int[] failure = new int[S.size()];
        for (int i = 1, j = 0; i < S.size(); i++) {
            while (j > 0 && S.get(i) != S.get(j))
                j = failure[j - 1];
            if (S.get(i) == S.get(j)) {
                failure[i] = ++j;
                MAX = Math.max(j, MAX);
            }
        }
    }

}
