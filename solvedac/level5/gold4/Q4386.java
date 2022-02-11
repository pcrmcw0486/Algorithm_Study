package solvedac.level5.gold4;
/*
2022.01.13
문제번호 : Q4386
이름 및 난이도 : 별자리 만들기 Gold IV
문제이해 
-----------------
아무렇게나 널브러져 있는 n개의 별들을 이어 별자리를 만든다.
 - 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
 - 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져있다.
 - 별자리를 만드는 최소 비용을 구하시오.

제한 조건 :
   n <= 100
   100 * 99 /2 =  4950 (최대 edge 개수)

접근 방법 :
  - 모든 별들을 잇는다. (모든 정점 포함)
  - 직/간접적으로 이어져있다. (연결되어 있다.)
   모든 정점을 포함한 가장 짧은 연결된 그래프 > 최소 스패닝 트리.
  - 그래프를 만들어 최소 스패닝 트리를 만든다.
    - kruskal
    - prim
*/

import java.io.*;
import java.util.*;
public class Q4386 {
    static Point[] stars;
    static PriorityQueue<Edge> pq;
    static int[] parent;
    static int N;
    public static void main(String[] args) throws IOException{
        input();
        solution();    
    }

    public static void input()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        stars = new Point[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Point(x,y);
        }

        parent = new int[N];
        for(int i=0;i<N;i++){
            parent[i] = i;
        }

        //pq 말고
        // 배열 하나에 다 넣은다음
        //sorting해서 n-1개 골라도 됨. 굳이 pq안써도 가능함.
        pq = new PriorityQueue<Edge>();
        for(int i =0;i<N-1;i++){
            for(int j =i+1;j<N;j++){
                pq.add(new Edge(i,j,calcDist(stars[i],stars[j])));
            }
        }
    }

    //kruskal
    public static void solution(){
        int cnt = 0;
        double ans = 0;
        while(true){
            Edge cur = pq.poll();
            if(union(cur.x,cur.y)){
                cnt++;
                ans += cur.dist;
            }else
                continue;
            
            if(cnt == N-1)
                break;
        }
        System.out.printf("%.2f",ans);
    }
    
    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if( x== y) return false;
        parent[x] = y;
        return true;
    }

    public static double calcDist(Point a, Point b){
        return Math.sqrt(Math.pow(a.x-b.x,2) + Math.pow(a.y-b.y,2));
    }

    static class Edge implements Comparable<Edge>{
        int x, y;
        double dist;
        Edge(int x, int y, double dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
//            return (this.dist - o.dist)>0?1:-1;
        }
    }
    static class Point {
        double x, y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
        
    }


}
