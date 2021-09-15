package programmers.kakao.Intern2021;

import java.io.*;
import java.util.*;

public class Solution4 {
    public static void main(String[] args) {

    }

    class Node {
        int trapIdx;
        boolean isTrap;
        List<Edge> adj = new ArrayList<Edge>();
    }

    class Edge {
        int to;
        int dist;
        int dir;

        Edge(int to, int dist, int dir) {
            this.to = to;
            this.dist = dist;
            this.dir = dir;
        }
    }

    Node[] node;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        node = new Node[n + 1];
        int trapCnt = 0;
        int INF = Integer.MAX_VALUE;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));

        for (int i = 0; i < n + 1; i++)
            node[i] = new Node();
        for (int trap : traps) {
            node[trap].isTrap = true;
            node[trap].trapIdx = trapCnt++;
        }

        for (int road[] : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];
            node[u].adj.add(new Edge(v, dist, 0));
            if (node[u].isTrap || node[v].isTrap) {
                node[v].adj.add(new Edge(v, dist, 1));
            }
        }

        int dp[][] = new int[1 << trapCnt][n + 1];
        for (int d[] : dp) {
            Arrays.fill(d, INF);
        }

        dp[0][start] = 0;
        pq.add(new int[] { 0, start, 0 }); // state idx value
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int state = temp[0], cur = temp[1], dist = temp[2];
            if (dp[state][cur] < dist)
                continue;
            if (cur == end) {
                answer = dist;
                break;
            }
            if (node[cur].isTrap) {
                state ^= (1 << node[cur].trapIdx); //
            }
            int isCurTrapOn = isTrapOn(state, cur);
            for (Edge edge : node[cur].adj) {
                if ((isCurTrapOn ^ isTrapOn(state, edge.to)) == edge.dir) {
                    int d = dist + edge.dist;
                    if (dp[state][edge.to] > d) {
                        dp[state][edge.to] = d;
                        pq.add(new int[] { state, edge.to, d });
                    }
                }
            }

        }

        return answer;
    }

    public int isTrapOn(int state, int idx) {
        return node[idx].isTrap && (state & 1 << (node[idx].trapIdx)) > 0 ? 1 : 0;
    }

    class tuple implements Comparable<tuple> {
        int val, idx, state;

        public tuple(int val, int idx, int state) {
            this.val = val;
            this.idx = idx;
            this.state = state;
        }

        public int compareTo(tuple o) {
            return val - o.val;
        };
    }

    class pair {
        int x, y;

        pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final int INF1 = 0x7f7f7f7f;
    int d1[][] = new int[1004][1024];
    List<pair> adj[] = new ArrayList[1004];
    List<pair> adjrev[] = new ArrayList[1004];
    int[] trapidx = new int[1004];

    // 상태 state에 idx번 비트가 켜져있는가.
    boolean bitmask(int state, int idx) {
        return ((1 << trapidx[idx]) & state) != 0;
    }

    public int solution2(int n, int start, int end, int[][] roads, int[] traps) {
        // dp 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(d1[i], INF1);
        }
        // 정방향, 역방향 edge 초기화
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adjrev[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++)
            trapidx[i] = -1;
        for (int i = 0; i < traps.length; i++) {
            trapidx[traps[i]] = i;
        }

        // 간선처리
        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int val = roads[i][2];
            adj[u].add(new pair(v, val));
            adjrev[v].add(new pair(u, val));
        }

        d1[start][0] = 0;
        Queue<tuple> pq = new PriorityQueue<>();
        pq.add(new tuple(d1[start][0], start, 0));
        while (!pq.isEmpty()) {
            tuple cur = pq.poll();
            if (cur.idx == end)
                return cur.val;
            if (d1[cur.idx][cur.state] != cur.val)
                continue;

            // 정방향 간선
            for (int i = 0; i < adj[cur.idx].size(); i++) {
                pair nxt = adj[cur.idx].get(i);
                int reverse = 0;
                // cur이 트랩이고 밟아져 있다면
                if (trapidx[cur.idx] != -1 && bitmask(cur.state, cur.idx))
                    reverse ^= 1;
                // next로 갈 곳이 트랩이고 이미 켜져있다면
                if (trapidx[nxt.x] != -1 && bitmask(cur.state, nxt.x))
                    reverse ^= 1;
                if (reverse != 0)
                    continue; // reverse가 1 이면 역방향임
                int nxt_state = cur.state; // 다음 갈곳이 트랩이면 밟은걸로 상태 변경
                if (trapidx[nxt.x] != -1)
                    nxt_state ^= (1 << trapidx[nxt.x]);
                if (d1[nxt.x][nxt_state] > nxt.y + cur.val) {
                    d1[nxt.x][nxt_state] = nxt.y + cur.val;
                    pq.add(new tuple(d1[nxt.x][nxt_state], nxt.x, nxt_state));
                }
            }

            for (int i = 0; i < adjrev[cur.idx].size(); i++) {
                pair nxt = adjrev[cur.idx].get(i);
                int reverse = 0;
                if (trapidx[cur.idx] != -1 && bitmask(cur.state, cur.idx))
                    reverse ^= 1;
                if (trapidx[nxt.x] != -1 && bitmask(cur.state, nxt.x))
                    reverse ^= 1;
                if (reverse != 1)
                    continue; // 정방향임
                int nxt_state = cur.state;
                if (trapidx[nxt.x] != -1)
                    nxt_state ^= (1 << trapidx[nxt.x]);
                if (d1[nxt.x][nxt_state] > nxt.y + cur.val) {
                    d1[nxt.x][nxt_state] = nxt.y + cur.val;
                    pq.add(new tuple(d1[nxt.x][nxt_state], nxt.x, nxt_state));
                }
            }
        }

        return -1;
    }
}
