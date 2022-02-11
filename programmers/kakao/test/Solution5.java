package programmers.kakao.test;

import java.util.*;

public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        int answer = solution.solution(new int[] { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 }, new int[][] { { 0, 1 }, { 0, 2 },
                { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 }, { 9, 10 } });
        System.out.println("answer" + answer);
        // int answer2 = solution.solution(new int[] { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1,
        // 1 }, new int[][] { { 0, 1 },
        // { 1, 2 }, { 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6,
        // 5 }, { 4, 6 }, { 8, 9 } });
        // System.out.println("answer" + answer2);
    }

    static ArrayList<Integer>[] SolveByCategory.tree;
    static int[] p;

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        p = new int[info.length];
        boolean visit[] = new boolean[info.length];
        tree = new ArrayList[info.length];

        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        traversal(-1, 0);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        int level = 1;
        int sheep = 1;
        int wolf = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (info[cur] == 0) {
                    int tmpwolf = wolf;
                    int parent = p[cur];
                    while (parent != -1) {
                        if (!visit[parent] && info[parent] == 1) {
                            tmpwolf++;
                            visit[parent] = true;
                            parent = p[parent];
                        } else {
                            break;
                        }

                    }
                    // System.out.println("cur" + cur + " sheep " + sheep + "wolf" + wolf);
                    if (tmpwolf < sheep) {
                        if (cur != 0)
                            sheep++;
                        wolf = tmpwolf;
                    }

                }

                for (int child : tree[cur]) {
                    if (child != p[cur])
                        queue.add(child);
                }
            }

            if (sheep < level)
                break;
            level++;
        }
        answer = sheep;

        return answer;
    }

    public void traversal(int parent, int cur) {
        p[cur] = parent;
        for (int a : tree[cur]) {
            if (a != parent)
                traversal(cur, a);
        }
    }

}
