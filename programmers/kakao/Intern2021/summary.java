package programmers.kakao.Intern2021;

import java.util.*;
import java.io.*;

public class summary {
    public static void main(String[] args) {
        
    }

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    boolean[][] visited;
    boolean success_sol2 = true;
    public int[] solution2(String[][] places){
        //dfs 또는 bfs로 구현 
        int answer[] = new int[places.length];
        for(int i =0;i<5;i++){
            success_sol2 = true;
            visited = new boolean[5][5];
            for(int j =0;j<5 && success_sol2;j++){
                for(int k =0;k<5&& success_sol2;k++){
                    if(places[i][j].charAt(k) == 'P'){
                        visited[j][k] = true;
                        dfs_sol2(places[i],j,k,0);
                        visited[j][k] = false;
                    }
                }
            }//room-iter
            answer[i] = success_sol2?1:0;
        }//Test-iter

        return answer;
    }

    public void dfs_sol2(String[] places, int x, int y, int dist){
        if(dist>=2) return;
        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0||ny<0||nx>4||ny>4||places[nx].charAt(ny)=='X'||visited[nx][ny])
                continue;
            if(places[nx].charAt(ny)=='P'){
                success_sol2 = false;
                return;
            }
            visited[nx][ny] = true;
            dfs_sol2(places,nx,ny,dist+1);
            visited[nx][ny] = false;
        }
    }

    //=========================표 편집==============================

    public class Node{
        int idx;
        Node prev, next;

        Node(int idx){
            this.idx = idx;
        }

        public void remove(){
            prev.next = next;
            next.prev = prev;
        }

        public void restore(){
            prev.next = this;
            next.prev = this;
        }
        
        public boolean hasNext(){
            return next.idx != -1;
        }
    }

    public Node init(int n){
        Node start = new Node(-1);
        Node prev = start;
        Node cur = null;
        for(int i =0;i<n;i++){
            cur = new Node(i);
            prev.next = cur;
            cur.prev = prev;
            prev = cur;
        }
        cur.next = new Node(-1);
        return start.next;
    }

    

    public String solution3(int n, int k, String[] cmd){
        Stack<Node> stack = new Stack<Node>();
        Node cur= init(n);
        while(k-->0) cur = cur.next;

        for(String c : cmd){
            char command = c.charAt(0);
            int dist = 0;
            switch(command){
                case 'U':
                    dist = Integer.parseInt(c.substring(2));
                    while(dist-->0) cur = cur.prev;
                    break;
                case'D':
                    dist = Integer.parseInt(c.substring(2));
                    while(dist-->0) cur = cur.next;
                    break;
                case'C':
                    stack.add(cur);
                    cur.remove();
                    cur = cur.hasNext() ? cur.next : cur.prev;
                    
                    break;  
                case'Z':
                    stack.pop().restore();
                break;
            }
        }
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while(!stack.isEmpty()){
            sb.setCharAt(stack.pop().idx,'X');
        }
        return sb.toString();
    }
 
    //====================미로 탈출======================
    private class GNode{
        int trapIdx;
        boolean isTrap;
        List<Edge> adj = new ArrayList<>();
    }

    private class Edge{
        int to;
        int dist;
        int dir;
        Edge(int to, int dist, int dir){
            this.to = to;
            this.dist = dist;
            this.dir = dir;
        }
    }
    GNode[] graph;
    int[][] dp;
    final static int INF = Integer.MAX_VALUE;
    public int solution4(int n, int start, int end, int[][] roads, int[] traps){
        int answer = -1;
        graph = new GNode[n+1];
        dp = new int[n+1][1<<(traps.length)];
        for(int i =0;i<n+1;i++){
            Arrays.fill(dp[i], INF);
        }

        for(int i=1;i<n+1;i++){
            graph[i] = new GNode();
        }
        //trap 초기화
        for(int i =0;i<traps.length;i++){
            graph[traps[i]].isTrap = true;
            graph[traps[i]].trapIdx= i;
        }

        //road 초기화
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int d = road[2];

            graph[u].adj.add(new Edge(v,d,0));
            if(graph[u].isTrap || graph[v].isTrap){
                graph[v].adj.add(new Edge(u,d,1));
            }
        }

        Queue<int[]> pq = new PriorityQueue<>((a,b)->(a[2]-b[2]));
        dp[start][0] = 0;
        pq.add(new int[]{0,start,0});//status, idx, dist
        while(!pq.isEmpty()){
            int[] tmp = pq.poll();
            int status = tmp[0];
            int idx = tmp[1];
            int dist = tmp[2];
            if(idx == end) {
                answer = dist;
                break;
            }   
            if(dp[idx][status] < dist) continue;
            if(graph[idx].isTrap){
                status ^= 1<<graph[idx].trapIdx;
            }
            int curStatus = isTrapped(idx,status);
            
            for(Edge next : graph[idx].adj){
                if((curStatus^isTrapped(next.to,curStatus))==next.dir){
                    if(dp[next.to][status] > dist + next.dist){
                        dp[next.to][status] = dist + next.dist;
                        pq.add(new int[]{status,next.to,dist+next.dist});
                    }
                }
            }
        }
    
        return answer;
    }

    public int isTrapped(int idx, int status){
        return graph[idx].isTrap && (status & (1<<graph[idx].trapIdx))>0?1:0;
    }

    //==========================시험장 나누기===============================
    int[][] L;
    int[] N;
    int n, root;
    int cnt = 0;
    public int solution5(int k, int[] num, int[][] links){
        n = num.length; root = n*(n-1)/2;
        N = num;
        L = links;
        for(int[] link : links){
            for(int l : link){
                if(l>-1){
                    root -= l;
                }
            }
        }

        int start = 0;
        int end =0;
        for(int i =0;i<N.length;i++){
            start = Math.max(start, N[i]);
            end += N[i];
        }
        while(start<end){
            int mid = (start + end)/2;
            if(solve(mid)<=k)
                end = mid;
            else
                start = mid+1;
        }
        return start;
    }

    public int solve(int limit){
        cnt =0;
        dfs_sol5(root, limit);
        cnt++;
        return cnt;
    }

    public int dfs_sol5(int cur, int limit){
        int lv = 0;
        if(L[cur][0] != -1) lv = dfs_sol5(L[cur][0], limit);
        int rv = 0;
        if(L[cur][1] != -1) rv = dfs_sol5(L[cur][1], limit);
        
        if(N[cur] + lv + rv <= limit)
            return N[cur] + lv + rv;
        if(N[cur] + Math.min(lv,rv) <= limit){
            cnt++;
            return N[cur] + Math.min(lv, rv); 
        }
        cnt+=2;
        return N[cur];
    }

    boolean tooMin = true;
    public int solution5_1(int k, int[] num, int[][] links){
        n = num.length; root = n*(n-1)/2;
        N = num;
        L = links;
        for(int[] link : links){
            for(int l : link){
                if(l>-1){
                    root -= l;
                }
            }
        }
        int end = 0 ;
        for(int i =0;i<N.length;i++){
            end += N[i];
        }
        int start = end/k;
        while(start<end){
            tooMin = false;
            cnt = 1;
            int mid = (start + end)/2;
            search(root,mid);
            if(cnt>k || tooMin){
                start = mid;
            }else{
                end = mid -1;
            }
        }
        return start;
    }
    public int search(int cur, int limit){
        int n = N[cur];
        int l  = search(L[cur][0], limit);
        int r = search(L[cur][1], limit);
        if(n>limit) tooMin = true;
        else if(n+l+r > limit){
            cnt++; 
            if(n+l>limit && n+r>limit){
                cnt++; 
                return n;
            }else return  n+ Math.min(l,r);
        }
        return n+r+l;
    }
}
