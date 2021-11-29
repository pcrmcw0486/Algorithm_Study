package solvedac.level3;
/*
2021.11.29
문제번호 : Q1931
이름 및 난이도 : 회의실 배정
문제이해 
-----------------
쉽게 생각해서 배정을 함에 있어서 어떻게 하면되는지
[  배정 ]  [ 남은 시간] 이라고 할 때
남은시간을 가장 크게 해주는 것이 중요하다 즉, 빨리 끝나는 것이 중요하다.
빨리 끝나게 되면 남은시간이 길기 때문에 그 만큼 많은 회의를 배정할 수 있다.

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;
public class Q1931 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            scheduleList.add(new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(scheduleList);
        int cnt = 0;
        int prev = 0;
        for(Schedule s : scheduleList){
            if(prev <= s.start){
                prev = s.end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }    
    static class Schedule implements Comparable<Schedule>{
        int start;
        int end;
        Schedule(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schedule s){
            if(this.end == s.end){
                return this.start-s.start;
            }
            return this.end - s.end;
        }
    }
}
