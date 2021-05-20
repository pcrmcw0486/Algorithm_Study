/*
 키패드
 https://programmers.co.kr/learn/courses/30/lessons/67256?language=java
 *** 접근방향, 문제 조건 ***
 휴대폰 키패드를 쓴다고할때, 왼손 오른손 구하기.
 *** 알고리즘 자료구조 스킬 ***
 구현, %와 /를 이용하는. 순차적으로 증가하는 2차원 배열.
 */
package programmers.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class keypad {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String hand = br.readLine();
        System.out.println(solution(numbers, hand));
    }

    public static String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        // 들어온 input값 hand를 사용하기 쉽도록 변경 (변경부분)
        String myHand = (hand.equals("right")) ? "R" : "L";

        int rightHand = 10;
        int leftHand = 12;
        for (int num : numbers) {
            if (num == 0)
                num = 11;
            switch (num % 3) {
                case 0:
                    sb.append('R');
                    rightHand = num;
                    break;
                case 1:
                    sb.append('L');
                    leftHand = num;
                    break;
                case 2:
                    String tempHand = checkDist(rightHand, leftHand, num, myHand);
                    if (tempHand.equals("R"))
                        rightHand = num;
                    else
                        leftHand = num;
                    sb.append(tempHand);
            }
        }

        String answer = sb.toString();
        return answer;
    }

    private static String checkDist(int rightHand, int leftHand, int num, String myHand) {
        int leftDist = Math.abs((num - 1) / 3 - (leftHand - 1) / 3) + Math.abs((num - 1) % 3 - (leftHand - 1) % 3);
        int rightDist = Math.abs((num - 1) / 3 - (rightHand - 1) / 3) + Math.abs((num - 1) % 3 - (rightHand - 1) % 3);
        return (leftDist == rightDist) ? myHand : ((leftDist > rightDist) ? "R" : "L");
    }
}
