package DayByDay._0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @문제번호 : Q2204
 * @문제이름 : 도비의 난독증 테스트
 * @난이도 : Silver V
 * @date : 2022-03-23 오후 3:41
 * @author : pcrmcw0486
 * @문제이해
 * 대소문자를 구분하지 않고 사전순으로 가장 앞서는지 맞추도록한다.
 * 대소문자를 마구섞어가며 단어들을 제시한다.
 * @알고리즘
 * 단순 구현, 소팅
 * @접근방법

*/
public class Q2204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            ArrayList<String> data = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                data.add(s);
            }
//            data.sort(new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    return o1.toLowerCase().compareTo(o2.toLowerCase());
//                }
//            });
            data.sort(String::compareToIgnoreCase);
            sb.append(data.get(0)).append('\n');
        }
        System.out.print(sb);
    }
}
