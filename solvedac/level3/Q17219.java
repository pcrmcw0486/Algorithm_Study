package solvedac.level3;
/*
2021.11.05
문제번호 : Q17219
이름 및 난이도 : 비밀번호 찾기
문제이해 
-----------------
사이트 주소와 비밀번호
저장된 사이트 주소의 수 100_000
찾으려는 사이트 주소의 수 1<~100_000
사이트 주소는 알파벳 소문자, 알파벳 대문자, '대시', '마침표' 중복 X
비밀번호는 알파벳 대문자로만 이루어짐.
접근 방법 :
해쉬 밖에 생각이 안나는데. .. 키가 중복이지만, 의미없음.
찾으려는 키가 사이트 주소라.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, String> pwMap = new HashMap<String, String>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pwMap.put(st.nextToken(), st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            sb.append(pwMap.get(br.readLine())).append('\n');
        }
        System.out.print(sb.toString());
    }
}
