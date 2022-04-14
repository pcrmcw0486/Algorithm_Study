package DayByDay._0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q16139
 * @문제이름 : 인간-컴퓨터 상호작용
 * @난이도 : Silver II
 * @date : 2022-04-10 오후 4:08
 * @author : pcrmcw0486
 * @문제이해

 * @알고리즘

 * @접근방법

*/
public class Q16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] text = br.readLine().toCharArray();
        int[][] preSum = new int[26][text.length];
        for (int i = 0; i < text.length; i++) {
            preSum[text[i]-'a'][i]++;
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < text.length; j++) {
                preSum[i][j] += preSum[i][j-1];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = st.nextToken().charAt(0) - 'a';
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from == 0) sb.append(preSum[idx][to]);
            else
                sb.append(preSum[idx][to] - preSum[idx][from - 1]);
            sb.append('\n');
        }
        System.out.print(sb);
    }

}
