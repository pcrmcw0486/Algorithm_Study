package programmers.kit.StackandQueue;

/*
프로그래머스 팀에서 기능 개선 작업 수행 중, 진도가 100%일 때 서비스 반영
각 기능의 개발 속도는 모두 달라, 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발 가능.
뒤에 있는 기능은 앞의 기능이 배포될 때 같이 됨.
배포 순서대로 작업의 진도가 적힌 정수 배열
개발 속도 speed
ex)
93 30 55 (순서대로 배포되어야함) > [1, 30, 5], 각 배포마다 몇개의 기능이 배포되는가.
7일 걸림, 3일 걸림, 9일 걸림 
7, 3, 9
생각의 흐름
Naiive 하게 앞의 순서가 다 될때까지 상황을 update시킴.
다되면 포인터를 넘기면서 개수를 세서 출력하면됨.
하나씩해서 다보는 데 O(N)
볼 때마다 update해주는데 O(N)이라 N^2이네.
N은 100개 이하임.

문제 유형에 맞게 풀려면 stack을 사용하면됨.
stack사용시 O(N)

나누기 연산 주의!
 */
import java.util.*;

public class Solution1 {
    public static void main(String[] args) {
        int[] progress = { 95, 90, 99, 99, 80, 99 };
        int[] speed = { 1, 1, 1, 1, 1, 1 };
        int[] ans = solution1(progress, speed);
        for (int a : ans) {
            System.out.println(a);
        }
    }

    // 1. naiive하게 풀기.
    // 이렇게 풀어도 overflow는 안나겟네. 10_000
    // 근데 메모리 가 적나봐. 메모리가 터지네
    public static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for (int i = 0; i < progresses.length;) {
            int day = (int) Math.ceil((100 - progresses[i]) / speeds[i]);
            int cnt = 0;
            boolean check = true;
            for (int j = i; j < progresses.length; j++) {
                progresses[j] += day * speeds[j];
                if (progresses[j] >= 100 && check) {
                    cnt++;
                    i++;
                } else {
                    check = false;
                }
            }
            answer.add(cnt);
        }
        int[] ans = new int[answer.size()];
        int idx = 0;
        for (int a : answer) {
            ans[idx++] = a;
        }

        return ans;
    }

    public static int[] solution1(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int cnt = 1;
        int tmp = (int) Math.ceil((100 - progresses[0]) / speeds[0]);

        for (int i = 1; i < progresses.length; i++) {
            int nxt = (int) Math.ceil((100 - progresses[i] / speeds[i]));
            if (tmp >= nxt)
                cnt++;
            else {
                answer.add(cnt);
                tmp = nxt;
                cnt = 1;
            }
        }
        answer.add(cnt);
        int[] ans = new int[answer.size()];
        int idx = 0;
        for (int a : answer) {
            ans[idx++] = a;
        }
        return ans;
    }

    public static int[] solution2(int[] progresses, int[] speeds) {
        int[] dayOfEnd = new int[100];
        int day = -1;
        for (int i = 0; i < progresses.length; i++) {
            if (progresses[i] + (day * speeds[i]) < 100) {
                day = (100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i]
                        : ((100 - progresses[i]) / speeds[i]) + 1;
            }
            dayOfEnd[day]++;
        }
        return Arrays.stream(dayOfEnd).filter(i -> i != 0).toArray();
    }
}
