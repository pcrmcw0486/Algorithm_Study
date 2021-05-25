package programmers.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class expressionMax {
    static Long ans = 0L;
    static ArrayList<Long> operand;
    static ArrayList<Character> operator;
    static Set<Character> resultSet;
    static Character[] operatorSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        System.out.println(solution(expression));
    }

    public static long solution(String expression) {
        // 정규식을 통한 구분
        operand = new ArrayList<>();
        operator = new ArrayList<>();
        resultSet = new HashSet<>();
        operand.addAll(Arrays.stream(expression.split("[*,\\-,+]")).mapToLong(Long::parseLong).boxed()
                .collect(Collectors.toList()));
        operator.addAll(
                expression.replaceAll("[0-9]", "").trim().chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
        resultSet = expression.replaceAll("[0-9]", "").trim().chars().mapToObj(e -> (char) e)
                .collect(Collectors.toSet());
        operatorSet = new Character[resultSet.size()];
        resultSet.toArray(operatorSet);

        int[] visit = new int[operatorSet.length];
        comb(1, visit);

        return ans;
    }

    private static void comb(int idx, int[] visit) {
        if (idx == operatorSet.length) {
            ans = Math.max(ans, calcProgress(visit));
        }
        for (int i = 0; i < operatorSet.length; i++) {
            if (visit[i] == 0) {
                visit[i] = idx;
                comb(idx + 1, visit);
                visit[i] = 0;
            }
        }
    }

    private static long calcProgress(int[] operatorPriority) {
        ArrayList<Long> tmpOperand = new ArrayList<>();
        ArrayList<Character> tmpOperator = new ArrayList<>();
        tmpOperand.addAll(operand);
        tmpOperator.addAll(operator);
        for (int i = 0; i < operatorSet.length; i++) {
            for (int j = 0; j < tmpOperator.size(); j++) {
                if (operatorSet[operatorPriority[i]] == tmpOperator.get(j)) {
                    long op1 = tmpOperand.get(j);
                    long op2 = tmpOperand.get(j + 1);
                    Character oper = tmpOperator.get(j);
                    tmpOperand.remove(j);
                    tmpOperand.remove(j);
                    tmpOperand.add(j, calc(op1, op2, oper));
                    tmpOperator.remove(j);
                    j--;
                }
            }
        }
        return Math.abs(tmpOperand.get(0));
    }

    private static long calc(long op1, long op2, Character oper) {
        if (oper == '+')
            return op1 + op2;
        else if (oper == '-')
            return op1 - op2;
        else
            return op1 * op2;
    }
}