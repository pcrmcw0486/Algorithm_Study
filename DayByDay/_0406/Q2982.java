package DayByDay._0406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q2982
 * @문제이름 : 국왕의 방문
 * @난이도 : Gold II
 * @date : 2022-04-06 오후 3:57
 * @문제이해 도시는 여러개 교차로와 교차로를 연결하는 양방향 도로.
 * 각 도로를 이동하는데 걸리는 시간.
 * 고돌라가 길에 있는 동안 다른 차량은 들어올 수 없다.
 * 하지만, 그전에 들어온 차량은 가능하다.
 * @알고리즘
 * @접근방법
 */
public class Q2982 {
    static int N, M, A, B, K, G;
    static int[] restrictedPath;
    static int[] restrictedStartTime;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        input();
        initRestrict();
        bfs(A);
    }

    private static void bfs(int a) {
        int ans = -1;
        int[] arriveTime = new int[N + 1];
        Arrays.fill(arriveTime, Integer.MAX_VALUE);
        PriorityQueue<Status> queue = new PriorityQueue<Status>();
        queue.add(new Status(a, K));
        arriveTime[a] =K;
        while (!queue.isEmpty()) {
            Status cur = queue.poll();
            if (cur.idx == B) {
                ans = cur.time;
                break;
            }
            if(cur.time > arriveTime[cur.idx]) continue;
            for (int i = 1; i < N + 1; i++) {
                if (dist[cur.idx][i] == 0) continue;
                int timeCost = dist[cur.idx][i];
                //통제를 당하는 경우
                //cur.idx에서 출발해서 i로 가는 길이 막히는 경우
                if (i == restrictedPath[cur.idx]) {
                    if(cur.time >= restrictedStartTime[cur.idx]
                       && cur.time < restrictedStartTime[cur.idx] + timeCost){
                        timeCost += timeCost - Math.abs(restrictedStartTime[cur.idx] - cur.time);
                    }
                } else if (cur.idx == restrictedPath[i]) {
                    if(cur.time >= restrictedStartTime[i]
                            && cur.time < restrictedStartTime[i] + timeCost){
                        timeCost += timeCost - Math.abs(restrictedStartTime[i] - cur.time);
                    }
                }
//                if (restrictedPath[] != -1
//                        && (cur.time >= restrictedStartTime[i]
//                        && cur.time < restrictedStartTime[i] + timeCost)) {
//                    timeCost += timeCost - restrictedStartTime[i];
//                }
                if (arriveTime[cur.idx] + timeCost < arriveTime[i]) {
                    arriveTime[i] = arriveTime[cur.idx] + timeCost;
                    queue.add(new Status(i, arriveTime[i]));
                }
            }
        }
        System.out.println(ans-K);
    }

    private static void initRestrict() {
        restrictedStartTime = new int[N + 1];
        int prev = restrictedPath[0];
        while (true) {
            int nxt = restrictedPath[prev];
            if (nxt == -1) break;
            restrictedStartTime[nxt] = restrictedStartTime[prev] + dist[prev][nxt];
          //  System.out.println(prev + " ~ " + nxt + " ");
           // System.out.println("restrictedStartTime[nxt] = " + restrictedStartTime[nxt]);
            prev = nxt;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        restrictedPath = new int[N + 1];
        dist = new int[N + 1][N + 1];
        st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        Arrays.fill(restrictedPath, -1);
        restrictedPath[0] = prev;
        for (int i = 1; i < G; i++) {
            restrictedPath[prev] = Integer.parseInt(st.nextToken());
            prev = restrictedPath[prev];
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[u][v] = dist[v][u] = cost;
        }

    }

    static class Status implements Comparable<Status>{
        int idx, time;

        public Status(int to, int time) {
            this.idx = to;
            this.time = time;
        }

        @Override
        public int compareTo(Status o) {
            return this.time - o.time;
        }
    }
}
