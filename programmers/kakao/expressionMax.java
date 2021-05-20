package programmers.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class expressionMax {
    static boolean[] priority = new boolean[3];
    static int[] operatorPriority;
    static ArrayList<Long> operand;
    static ArrayList<Character> operator;
    static Set<Character> operatorSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        System.out.println(solution(expression));
    }

    public static long solution(String expression) {
        long answer = 0;

        StringBuilder sb = new StringBuilder(5);
        // data setting
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            } else {
                if (!operatorSet.contains(c)) {
                    operatorSet.add(c);
                }
                operator.add(c);
                operand.add(Long.valueOf(sb.toString()));
                sb.delete(0, sb.length());
            }
        }
        operatorPriority = new int[operator.size()];
        answer = comb(operatorSet.size(), 0);

        return answer;
    }

    public static comb(int size, int idx){
        if(size == idx){
            return calc();
        }
        for(int i =0;i<size;i++){
            if(!priority[i]){
                
            }
        }
    }

}
