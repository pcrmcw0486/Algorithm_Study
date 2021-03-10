/* 단계별로 풀어보기 - 스택
균형잡힌 세상 - 실버4
문자열 연습 및 스택 연습
*/
package toy.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Q4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> data;

        while (true) {
            String input = br.readLine();
            if (input.charAt(0) == '.') {
                System.out.print(sb.toString());
                break;
            }
            data = new Stack<>();
            boolean check = true;
            int length = input.length();
            for (int i = 0; i < length; i++) {
                char a = input.charAt(i);
                if (a == '(' || a == '[')
                    data.push(a);
                else if (a == ')') {
                    if (data.isEmpty() || data.pop() != '(') {
                        check = false;
                        break;
                    }
                } else if (a == ']') {
                    if (data.isEmpty() || data.pop() != '[') {
                        check = false;
                        break;
                    }
                }
            }
            if (check && data.isEmpty())
                sb.append("yes");
            else
                sb.append("no");
            sb.append("\n");
        }
    }
}
