package solvedac.level4.gold3;
/*
2021.12.29
문제번호 : Q1918
이름 및 난이도 : 
문제이해 
-----------------
infix to postfix

etc) infix to prefix
reverse postfix alg(nearly grater than to greater) reverse
or using two stacks.
https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;
import java.util.*;
import java.io.*;

public class Q1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] expression = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        Stack<Character> operatorStack = new Stack<Character>();
        for (int i = 0; i < expression.length; i++) {
            char c = expression[i];
            // isOperand
            if (c >= 'A' && c <= 'Z')
                sb.append(c);
            else {
                // isOperator
                if (c == '(') {
                    operatorStack.push(c);
                } else if (c == ')') {
                    while (!operatorStack.isEmpty()) {
                        // todo
                        char o = operatorStack.pop();
                        if (o == '(')
                            break;
                        sb.append(o);
                    }
                } else {
                    while (!operatorStack.isEmpty() && (getPriority(c) <= getPriority(operatorStack.peek()))) {
                        sb.append(operatorStack.pop());
                    }
                    operatorStack.push(c);
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            sb.append(operatorStack.pop());
        }
        System.out.println(sb.toString());
    }

    public static int getPriority(char c) {
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '/')
            return 2;
        else
            return 0;
    }
}
