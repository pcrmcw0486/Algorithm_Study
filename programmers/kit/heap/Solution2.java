package programmers.kit.heap;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        int[][] jobs = { { 0, 1 }, { 0, 1 }, { 1, 0 } };
        int ans = solution(jobs);
        System.out.println(ans);
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<JobStatus> waitQ = new PriorityQueue<JobStatus>(new Comparator<JobStatus>() {
            @Override
            public int compare(JobStatus o1, JobStatus o2) {
                if (o1.end == o2.end) {
                    if (o1.start == o2.start)
                        return o1.procTime - o2.procTime;
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        for (int i = 0; i < jobs.length; i++) {
            waitQ.add(new JobStatus(jobs[i][0], jobs[i][1]));
        }
        Queue<JobStatus> temp = new LinkedList<JobStatus>();
        int totalTime = 0;
        int sum = 0;
        while (!waitQ.isEmpty()) {
            totalTime = Math.max(totalTime, waitQ.peek().start);
            int size = waitQ.size();
            for (int i = 0; i < size; i++) {
                if (waitQ.peek().start > totalTime)
                    break;
                JobStatus js = waitQ.poll();
                temp.add(new JobStatus(js.start, js.procTime, totalTime - js.start + js.procTime));
            }
            while (!temp.isEmpty()) {
                waitQ.add(temp.poll());
            }
            // for (JobStatus js : waitQ) {
            // System.out.println(js.start + " " + js.procTime + " " + js.end);
            // }
            JobStatus js = waitQ.poll();
            System.out.println(js.start + " " + js.procTime + " " + js.end);
            totalTime += js.procTime;
            sum += js.end;
            System.out.println(js.end);
        }
        answer = sum / jobs.length;

        return answer;
    }

    static class JobStatus {
        int start;
        int procTime;
        int end;

        JobStatus(int start, int procTime) {
            this.start = start;
            this.procTime = procTime;
            this.end = Integer.MAX_VALUE;
        }

        JobStatus(int start, int procTime, int end) {
            this.start = start;
            this.procTime = procTime;
            this.end = end;
        }
    }
}
