package programmers.kit.StackandQueue;

/*
중요도가 높은 문서를 먼저 인쇄한다.
1. 인쇄 대기목록의 가장 앞에 있는 문서 J를 대기목록에서 꺼낸다.
2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 하나라도 존재하면 J를 대기목록의 가장 마지막에 넣는다.
3. 그렇지 않으면 J를 인쇄한다.

ex) ABCD (2132)라면 CDAB
대기목록     ABCD 
대기목록 A
대기목록 AB
대기목록 AB =>  C
대기목록 AB => D
대기목록 AB => A
대기목록 B => B

총 목록 1~100개 이하의 문서, 중요도 1~9
location 0~N-1 의 수 , 원하는 location의 문서는 언제 나오는가?

마지막에 넣는다.이기때문에 마지막에 넣으면 마지막에 나와야지 FIFO (QUEUE)
나보다 높은 우선순위가 있는가를 확인해야겟음.(이게 가장 오래 걸릴 것 같은데) 어떻게 확인할 수 있는가.
 */
import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        int[] priorities = { 1, 1, 9, 1, 1, 1 };
        int location = 0;
        int a = solution(priorities, location);
        System.out.println(a);
    }

    static class Task {
        int location;
        int priority;

        public Task(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Task> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.add(new Task(i, priorities[i]));
        }

        int now = 0;
        while (!queue.isEmpty()) {
            Task cur = queue.poll();
            boolean flag = false;
            for (Task t : queue) {
                if (t.priority > cur.priority) {
                    flag = true;
                }
            }
            if (flag) { // 우선순위 높은게 있으면 뒤로 보낸다
                queue.add(cur);
            } else {
                now++;
                if (cur.location == location) {
                    answer = now;
                    break;
                }

            }
        }
        return answer;
    }
}
