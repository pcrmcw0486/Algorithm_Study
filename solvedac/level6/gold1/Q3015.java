package solvedac.level6.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q3015
 * @문제이름 : 오아시스 재결합
 * @난이도 : Gold I
 * @date : 2022-03-10 오후 10:39
 * @문제이해 N명이 한줄로 서서 기다림.
 * 자기가 볼 수 있는 사람의 수를 구하자
 * A와 B가 서로 볼 수 있으려면 두 사람 사이에 A또는 B보다 큰 사람이 없어야한다.
 * 줄에 서있는 사람의 키가 주어질때 서로 볼 수 있는 쌍의 수를 구하는 프로그램.
 * 무지성이면 N^3 무조건 넘는다.
 * @알고리즘
 * @접근방법 A와 B사이에 자신보다 큰 사람이 없어야한다.
 * 근데 결국 모든 쌍을 봐야하나요? 그건 맞음. N^2봐야되는건 맞음.아닌가봐
 * 줄을 바꾸진 못해 /(정렬은 아니야) 아니 할수 잇을지도
 * 자신보다 처음 큰 수 찾기. NlogN
 */
public class Q3015 {
    static int[] people;
    static class Status{
        int val, count;
        public Status(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
        Stack<Status> stack = new Stack<>();
        people = new int[N];
        people[0] = Integer.parseInt(br.readLine());
        stack.add(new Status(people[0],1));
        for (int i = 1; i < N; i++) {
            int check =0;
            people[i] = Integer.parseInt(br.readLine());
            while(!stack.isEmpty()) {
                if (stack.peek().val < people[i]) {
                    cnt += stack.pop().count;
                } else if (stack.peek().val == people[i]) {
                    check = stack.pop().count;
                    cnt += check;
                }else{
                    cnt++;
                    break;
                }
            }
            stack.push(new Status(people[i], check+1));
        }
        System.out.println(cnt);
    }
}
