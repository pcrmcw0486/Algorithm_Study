package solvedac.level3;
/*
2021.11.05
문제번호 : Q1620
이름 및 난이도 : 나는야 포켓몬 마스터 이다솜
문제이해 
-----------------
가지고 있는 포켓몬 도감에서
이름 > 번호
번호 > 이름
접근 방법 :
제한 조건 :
포켓몬 개수 N 문제의 개수 M
1~100_000
첫글자만 대문자 나머지는 소문자, 일부는 마지막문자만 대문자일수도
2~20
//100_000 하나로만 하면 100_000 * 100_000 하면 답도 없다.
그냥 자료저장을 두군데 해서 한번에 보면 되지 않을까?
*/

import java.io.*;
import java.util.*;

public class Q1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] baseInfo = br.readLine().split(" ");

        int N = Integer.parseInt(baseInfo[0]);
        int M = Integer.parseInt(baseInfo[1]);
        String[] pockList = new String[N + 1];
        HashMap<String, Integer> pockMap = new HashMap<String, Integer>();
        for (int i = 1; i < N + 1; i++) {
            pockList[i] = br.readLine();
            pockMap.put(pockList[i], i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String question = br.readLine();
            sb.append(pockMap.containsKey(question) ? pockMap.get(question) : pockList[Integer.parseInt(question)])
                    .append('\n');
        }
        System.out.print(sb.toString());
    }
}
