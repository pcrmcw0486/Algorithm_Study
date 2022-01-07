package solvedac.level4.gold4;
/*
2021.12.21
문제번호 : Q9935
이름 및 난이도 : 문자열 폭발 Gold IV
문제이해 
-----------------
폭발 문자열. 
 - 폭발 문자열 포함시, 모든 폭발 문자열이 폭발. 남은 문자열 순서대로 이어붙임.
 - 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
 - 포발은 폭발 문자열이 문자열에 없을 때 까지 계속된다.
접근 방법 :
    twoPoint로 접근한다. 
    메모리 때문에 두개이상 못쓰는 것 같음.
    left(Pat아닌 left) right(검사 Point)
    left(시작 문자열과 다르다면 증가한다. 같다면 폭발 전 까지 항상 고정)
    끝나는건? right가 끝까지 갔는데 폭발하지 않는 경우?
    스택 이였네.
    앞의 폭발은 뒤에 영향을 주지 않는다.
    뒤의 폭발은 앞에 영향을 준다.
    뒤에서 부터 폭발을 체크하여야한다.
     1. 뒤에서 부터 확인하는 방법.
     2. 스택? 
     진짜 없애는 건 무리같은데.. 워프 같이 뛰어서 확인해야하는데..?
     스택은 맞는거 같고 어떻게 사용하지?
     근본적 문제에 들어가셈.
      아니였는데 삭제하고 나니 된다.
      즉 앞으로가면서 아니엿는데 삭제하고 보니 된다?


제한 조건 : 
    - 남아있는 문자열을 출력.
    - 없다면 "FRULA"
    - 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
*/

import java.io.*;
import java.util.*;

public class Q9935 {
    static String str;
    static String pat;
    final static String EXCEPT_CASE = "FRULA";

    public static void main(String[] args) throws Exception {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        pat = br.readLine();
    }

    public static void solution() {
        char[] strArr = str.toCharArray();
        char[] patArr = pat.toCharArray();
        char[] ans = new char[str.length()];
        int idx = 0;
        if (patArr.length > strArr.length) {
            System.out.println(str);
            return;
        }
        boolean explosion = false;
        for (int i = 0; i < str.length(); i++) {
            // 확인 요건
            if (strArr[i] == patArr[patArr.length - 1] && idx >= patArr.length - 1) {
                explosion = true;
                for (int j = idx - pat.length() + 1; j < idx; j++) {
                    if (patArr[j - (idx - pat.length() + 1)] != ans[j]) {
                        explosion = false;
                        break;
                    }
                }
                if (explosion) {
                    idx -= pat.length() - 1;
                    continue;
                }
            }

            ans[idx++] = strArr[i];
        }
        if (idx == 0)
            System.out.println(EXCEPT_CASE);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idx; i++) {
                sb.append(ans[i]);
            }
            System.out.println(sb.toString());
        }

    }

    public static void solutionPrev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        char[] pat = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        char[] answer = new char[input.length];

        int index = 0;
        for (int i = 0; i < input.length; i++) {
            answer[index] = input[i];
            index++;
            if (answer[index - 1] == pat[pat.length - 1]) {
                if (index - pat.length < 0)
                    continue;
                boolean flag = true;
                for (int j = 0; j < pat.length; j++) {
                    if (answer[index - 1 - j] != pat[pat.length - 1 - j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    index -= pat.length;
                }
            }
        }
        if (index == 0) {
            System.out.println("FRULA");
        } else {
            for (int i = 0; i < index; i++) {
                sb.append(answer[i]);
            }
            System.out.println(sb.toString());
        }
    }
}
