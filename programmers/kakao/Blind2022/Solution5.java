package programmers.kakao.Blind2022;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : pcrmcw0486
 * @문제번호 : Solution5
 * @문제이름 :
 * @난이도 :
 * @date : 2022-04-08 오후 7:29
 * 7:36
 * @문제이해 초원 루트 노드 출발
 * 각 노드 방문 시 양과 늑대가 따라옴
 * 늑대 수 >= 양의수 (양을 다 잡아먹힘)
 * 양이 잡아먹히지 않도록 하면서 최대한 많은 수의 양을 모아
 * 루트로 돌아오자.
 * @알고리즘
 * @접근방법
 */
public class Solution5 {
    public static void main(String[] args) {
        int[] info = new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = new int[][]{
                {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 1}, {9, 1}, {4, 3}, {6, 5}, {4, 6}, {8, 9}
        };
        info = new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        edges = new int[][]{
                {0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}
        };
        solution(info, edges);
    }

    static Status[] treeStatus;
    static ArrayList<Integer>[] tree;

    private static int solution(int[] info, int[][] edges) {
        int size = info.length;
        boolean[][] isVisited = new boolean[1 << size][size];
        treeStatus = new Status[size];
        tree = new ArrayList[size];
        for (int i = 0; i < size; i++) tree[i] = new ArrayList<>();
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
        int max = 0;
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(0, 1, 1, 0));
        while (!queue.isEmpty()) {
            Status curStatus = queue.poll();
            max = Math.max(max, curStatus.sheepCnt);
            for (int i = 0; i < size; i++) {
                if ((curStatus.status & (1 << i)) != 0) {
                    for (int nxt : tree[i]) {
                        if ((curStatus.status & (1 << nxt)) ==0) {
                            if (info[nxt] == 1 && curStatus.sheepCnt > curStatus.wolfCnt + 1) {
                                queue.add(new Status(nxt, curStatus.status | (1 << nxt), curStatus.sheepCnt, curStatus.wolfCnt + 1));
                            } else if (info[nxt] == 0) {
                                queue.add(new Status(nxt, curStatus.status | (1 << nxt), curStatus.sheepCnt+1, curStatus.wolfCnt ));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(max);


        return max;
    }
    public static class Status {
        int cur;
        int status;
        int sheepCnt;
        int wolfCnt;

        public Status(int cur, int status, int sheepCnt, int wolfCnt) {
            this.cur = cur;
            this.status = status;
            this.sheepCnt = sheepCnt;
            this.wolfCnt = wolfCnt;
        }
    }
}
