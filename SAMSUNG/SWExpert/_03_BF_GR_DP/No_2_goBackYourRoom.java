package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.util.*;

/*
최단 시간에 모든 학생이 자신의 방으로 돌아가야한다.
홀수 1 ~ 399
짝수 2 ~ 400
두 학생이 자기방으로 돌아가면서 지나는 복도의 구간이 겹치면 두 학생은 동시에 돌아갈 수 없다.
[a,b] [c,d] 일 때 겹치는 구간있으면 안된다.
가장 빠르게 돌아가게 만들어보자.
최소 몇 단위시간이 걸리는가.

가장 빠르게 들어갈 수 있다면 빨리 들어가는 것이 좋다.
그렇다면 언제 가장 빠르게 돌아갈 수 있을까
가장 움직임이 적다면 복도를 차지하는 크기가 작다.
복도한 곳에 지금 만약 3명이 겹친다면 어떻게 하더라도 3번 기다려야한다.
그렇다면 최대한 남에게 방해하지 않는 선에서 되도록 해야함.
왜냐하면 길 수록 겹치는 구간이 크다면
겹치는 애들이 최대한 많은 애들이 가장 늦게 들어가야 더 많은 친구들이 움직일 수 있기 때문.


1-3 5-4 이면
1-3 4-5라서 되는데
문제가 (3,4)라인에서 걸쳐져서 안됨.
기준을 두어야함.
무슨말이냐 하면
1-3이나 1-4나 같은 걸로 봐야함.

그래서 결국.. count 해줘서 Max count 구하는걸로 하긴 했음.

* */
public class No_2_goBackYourRoom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        ArrayList<Point> student;
        for(int test_case  = 1 ; test_case<=T;test_case++){
            student = new ArrayList<Point>();
            sb.append('#').append(test_case).append(' ');
            int N = sc.nextInt(); //총 학생.

            // 번호는 겹치지 않는다.
            for(int i =0;i<N;i++){
                int u = (sc.nextInt() + 1 )/ 2;  //현재 방 번호
                int v  = (sc.nextInt() + 1 ) / 2; //돌아갈 방 번호
                student.add(new Point(Math.min(u,v),Math.max(u,v)));
            }
            student.sort(Point::compareTo);
            Queue<Point> queue = new LinkedList<Point>(student);
            int time = 0;
            while(true){
                time++;
                int size = queue.size();
                int max = -1;
                for(int i =0;i<size;i++){
                    Point s = queue.poll();
                    if(s.start > max){
                        max = s.end;
                    }else{
                        queue.add(s);
                    }

                }
                if(queue.isEmpty())
                    break;
            }
            sb.append(time).append('\n');
        }
        System.out.print(sb.toString());
    }
    static class Point implements Comparable<Point>{
        int start, end;
        Point(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point o) {
            if(this.end == o.end){
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }
}
