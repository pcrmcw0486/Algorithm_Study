/*
*** 타임머신 Gold IV ***
*** 접근 방법 ***
- 한 도시에서 다른 도시로 가는 가장 빠른 시간
 (single source all destination) 
 - 음수의 경우
 - Dijkstra는 음수간선 X Bellman-ford
*** 활용 알고리즘 및 활용 방법 ***
- Bellman-ford알고리즘을 사용한다.
- 각 도시에 도착하는 거리를 계산할 배열( size = V)
- Edge를 저장할 배열 또는 리스트
*** 문제의 조건 ***
- N개의 도시 , 한 도시 -> 다른 도시 버스 M
- 버스(A, B, C : 시작도시, 도착도시, 시간)
- C는 음수인 경우도 있다.
- C = 0은 순간이동, C<0은 타임머신
- 1번도시에서 나머지 도시로 가는 가장 빠른 시간
- negative cycle? -1, 경로가 없다면 -1
- N번 도시로 가는 가장 빠른 경로를 도시 순서대로 출력한다.
- 해당도시로가는 경로가 없다면 -1을 출력
*/
package SolveByCategory.graph.ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Bus {
    int start;
    int end;
    int time;

    public Bus(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

}

public class Q11657 {
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] dist = new long[N];
        ArrayList<Bus> bus_list = new ArrayList<>();

        Arrays.fill(dist, INF);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            bus_list.add(new Bus(u, v, weight));
        }
        /*
         * now -> next로 갈때 dist[now] + w[now,next] 와 dist[next]를 비교 dist[a]는 a까지의 최단 거리를
         * 의미
         */
        dist[0] = 0;
        boolean check= false;
        for (int i = 0; i < N; i++) {
            for (Bus bus : bus_list) {
                if (dist[bus.start] == INF)
                    continue;
                if (dist[bus.start] + bus.time < dist[bus.end]) {
                    dist[bus.end] = dist[bus.start] + bus.time;
                    if(i == N-1){
                        check = true;
                        break;
                    }
                }
            }
        }
        
        if(!check){
            for(int i=1;i<dist.length;i++){
                sb.append(dist[i]!=INF?dist[i]:-1).append("\n");
            }
        }
        else
            sb.append(-1).append("\n");
        System.out.print(sb.toString());
    }
}
