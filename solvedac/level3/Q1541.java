package solvedac.level3;
/*
2021.11.05
문제번호 : Q1541
이름 및 난이도 : 잃어버린 괄호 Silver II
문제이해 
-----------------
양수, +, - 로 식을 만든다 , 괄호를 다 지운 상태
괄호를 적절히 쳐서 이 식의 값을 최소로 만들려고 한다.
최소로 만드시오.
접근 방법 :
조합 BruteForce 같은데.
연산을 먼저하고 가냐 나중에 연산하냐 인 것 같은데.
제한 조건 : 
0~9, +, - 만 나온다.
처음과 마지막은 숫지다.
연속해서 연산자는 나오지 않고
5자리 보다 많이 연속되는 숫자는 없다.
수는 0으로 시작할 수 있다. 05 01 이런거 말하는거같죠.
*/

import java.io.*;
import java.util.*;

public class Q1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String equation = br.readLine();
        ArrayList<Character> operatorList = new ArrayList<Character>();
        ArrayList<Integer> operand = new ArrayList<Integer>();

        int sum = 0;
        int prev = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '-' || equation.charAt(i) == '+') {
                operand.add(Integer.parseInt(equation.substring(prev, i)));
                operatorList.add(equation.charAt(i));
                prev = i + 1;
            }
        }
        operand.add(Integer.parseInt(equation.substring(prev, equation.length())));

        sum = operand.get(0);
        int idx = 1;
        while (idx < operand.size()) {
            int tmp = 0;
            if (operatorList.get(idx - 1) == '-') {
                tmp = operand.get(idx);
                while (idx < operatorList.size() && operatorList.get(idx) != '-') {
                    idx++;
                    tmp += operand.get(idx);
                }
                sum -= tmp;
            } else {
                sum += operand.get(idx);
            }
            idx++;
        }
        System.out.println(sum);
    }
}
