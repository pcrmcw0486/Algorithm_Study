package solvedac.level4.gold3;
/*
2022.01.07
문제번호 : Q1918_Review
이름 및 난이도 : 후위 표기식. Gold III
문제이해 
-----------------
우선순위에 따른 . stack 사용.
  + (우선순위가 낮음)
  * + *  >> 우선순위가 높은 걸 먼저 계산해야함.
  낮은 것이 온다면 그 전에 계산해주어야할 것을 미리 계산해주어야한다.
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1918_Review {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> op = new Stack<Character>();
        char[] expression = br.readLine().toCharArray();
        char[] postfix = new char[expression.length];
        int idx = 0;
        for (int i = 0; i < expression.length; i++) {
            if (expression[i] >= 'A' && expression[i] <= 'Z') {
                postfix[idx++] = expression[i];
                continue;
            }
            // () 는 새로운 하나의 식 따로 계산해주어야함.
            if (expression[i] == '(') {
                op.push(expression[i]);
            } else if (expression[i] == ')') {
                // 밀려있는 계산 마무리.
                while (op.peek() != '(') {
                    postfix[idx++] = op.pop();
                }
                op.pop();
            } else {
                if (op.isEmpty()) {
                    op.push(expression[i]);
                } else {
                    int curPriority = getPriority(expression[i]);
                    while (!op.isEmpty()) {
                        int prevPriority = getPriority(op.peek());
                        if (prevPriority >= curPriority) {
                            postfix[idx++] = op.pop();
                        } else {
                            break;
                        }
                    }
                    op.push(expression[i]);
                }
            }
        }
        while (!op.isEmpty()) {
            postfix[idx++] = op.pop();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            sb.append(postfix[i]);
        }
        System.out.println(sb.toString());
    }

    public static int getPriority(char a) {
        if (a == '+' || a == '-')
            return 1;
        else if (a == '*' || a == '/')
            return 2;
        else if (a == '(')
            return 0;
        return -1;
    }
}
