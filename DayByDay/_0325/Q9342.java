package DayByDay._0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Q9342
 * @문제이름 : 염색체
 * @난이도 : Silver IV
 * @date : 2022-03-25 오후 3:22
 * @author : pcrmcw0486
 * @문제이해
 * 상근이는 생명과학 연구소에서 염색체가 특정한 패턴인지 확인한다.
 * 규칙
 *  A, B, C, D, E, F 중 0개 또는 1개로 시작해야한다.
 *  그 다음에는 A가 하나 또는 그 이상 있어야 한다.
 *  그다음에는 F가 하나 또는 그 이상 있어야 한다
 *  그 다음에는 C가 하나 또는 그 이상 있어야 한다.
 *  그 다음은 A, B, C, D, E, F중 0개 또는 1개가 ㅣㅇㅆ으며, 더 이상의 문자는 없어야 한다...?
 *  ....A...F...C..{A,B,C,D,E,F} 중 0개 또는
 * @알고리즘
  무슨문제임이게..
 * @접근방법
  
*/
public class Q9342 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        char[] pat = {'A', 'F', 'C'};
        for (int i = 0; i < N; i++) {
            boolean success = true;
            int idx =0;
            char[] s = br.readLine().toCharArray();
            if(s[0] - 'A' > 4 || s[s.length-1] -'A' > 4)
                success = false;

            for (int j = 1; j < s.length-1; j++) {
                if(s[j] == pat[idx]) continue;
                if(idx+1 < 3 && s[j] == pat[idx+1]){
                    idx++;
                }else{
                    success = false;
                    break;
                }
            }
            sb.append(success ? "Infected!" :"Good").append('\n');
        }
        System.out.print(sb);
    }
}
