package SAMSUNG.SWExpert._03_BF_GR_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Review_No_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tt=1;tt<=T;tt++){
            sb.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            ArrayList<Point> info = new ArrayList<>();
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken())-1;
                int v = Integer.parseInt(st.nextToken())-1;
                //방 압축
                u = u/2;
                v = v/2;
                info.add(new Point(Math.min(u,v), Math.max(u,v)));
            }
            info.sort(Point::compareTo);
            Queue<Point> queue = new LinkedList<Point>(info);
            int cnt =0 ;
            while (!queue.isEmpty()) {
            Point prev = new Point(-1, -1);
                int size = queue.size();
                for(int i =0;i<size;i++){
                    Point cur = queue.poll();
                    if (prev.to < cur.from) {
                        prev = cur;
                    }else{
                        queue.add(cur);
                    }
                }
                cnt++;
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
    static class Point implements Comparable<Point>{
        int from;
        int to;

        public Point(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Point o) {
            return this.from - o.from;
        }
    }
}
