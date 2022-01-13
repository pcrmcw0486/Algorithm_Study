package solvedac.level5.gold4;
/*
2022.01.13
문제번호 : Q20040
이름 및 난이도 : 사이클 게임 Gold IV
문제이해 
-----------------
선플레이어가 홀수 , 후 플레이어가 짝수번 차례 진행
0-n-1 고유번호 점n개
어느 세점도 일직선위에 있지 않다.
두 점을 선택하여 연결하는 선분을 긋는다.
 - 교차하는 것은 가능하다.
 - 첫 사이클이 완성 된 순간 게임이 종료된다.

 - 몇번째 차례에서 사이클이 완성되었는지, 혹은 게임이 진행중인지 판단해보자.
 - 사이클의 판단.
    사이클을 판단하는 데는 여러 방법이 있다.
     1. Disjoint set
     2. Prim 알고리즘.(N-1 이후에 가능한 선분이 있다면.)
     여기서는 X 번째 전에 사이클이 만들어 질 수 도 있기 때문에 이를 파악하기 위해
     Disjoint set을 이용하여 사이클 여부를 확인한다.
제한 조건 : 
접근 방법 :
*/

import java.io.*;
import java.util.*;
public class Q20040 {
    static int N, M;
    static int[] parent;
    static Edge[] line;
    public static void main(String[] args) throws IOException{
        input();
        solution();
    }

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        line = new Edge[M+1];
        for(int i =1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            line[i] = new Edge(u,v);
        }

        parent = new int[N];
        for(int i =0;i<N;i++) parent[i]= i;
    }

    public static void solution(){
        int ans = 0;
        for(int i =1;i<M+1;i++){
            if(!union(line[i].x,line[i].y)){
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y ){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        parent[y] = x;
        return true;
    }

    static class Edge{
        int x, y;
        Edge(int x, int y){
            this.x= x;
            this.y= y;
        }
    }
}
