package SAMSUNG.SWExpert._07Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
SWEA 보급로
파손된 도로가 있음.
기공병대는 S에서 G까지 가기 위한 도로 복구 작업을 '빠른 시간' 내에 수행하려고 한다.
파여진 깊이에 비례해서 복구시간은 증가.
출발지에서 도착지까지 가는 경로 중 복구시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.(깊이 1 ->시간 1)

현재 위치 칸 도로 복구해야 다른 곳으로 이동 가능.
지도 크기는 최대 100 * 100
총 간선의 개수는 39600
근데 모든 노드를 연결하는게 아니라 최소 거리.
최소 가중치 거리.
노드가 10000개인데 약 4배이네요.
a -> b로 가는 최단경로(최소 가중치)  >> dijkstra.
퍼져나가면서 가장 최단 경로인 노드를 택하여 퍼뜨린다.
bellman ford 면 (10000 - 1) * 39600 >> 396000000 (4억)
O(Elog|v|)
* */
public class No_2_supply {
    public static int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            int[][] dist = new int[N][N];
            for(int i =0;i<N;i++) {
                Arrays.fill(dist[i],INF);
                map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(0, 0, 0));
            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if(cur.x == N-1  && cur.y == N-1){
                    sb.append(cur.dist).append('\n');
                    break;
                }
                if(cur.dist > dist[cur.x][cur.y]) continue;
                for(int[] d : dir){
                    int nx = cur.x + d[0];
                    int ny = cur.y + d[1];
                    if(nx<0||ny<0||nx>N-1||ny>N-1) continue;
                    //현재 자신의 위치를 해결해야 지나갈 수 있음.
                    int nd = cur.dist + map[cur.x][cur.y];
                    if(dist[nx][ny] == INF || nd < dist[nx][ny]){
                        dist[nx][ny] = nd;
                        pq.add(new Node(nx, ny, nd));
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }
    static class Node implements Comparable<Node>{
        int x, y;
        int dist;
        Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
