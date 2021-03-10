/*
단계별로 풀어보기 - 스택
괄호(Silver 4)

스택을 사용하였다가 필요 없을 것 같아 check를 스택 top처럼 사용함.
*/
package toy.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int i = 0, j = 0;
        for (i = 0; i < N; i++) {
            int check = 0;
            String target = br.readLine();
            int length = target.length();
            for (j = 0; j < length; j++) {
                if (target.charAt(j) == '(')
                    check++;
                else {
                    if (check > 0)
                        check--;
                    else
                        break;
                }
            }
            if (check == 0 && j == length)
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");

        }
        System.out.print(sb.toString());
    }
}
