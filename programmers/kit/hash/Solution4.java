package programmers.kit.hash;

import java.util.*;

public class Solution4 {
    public static void main(String[] args) {
        String[] genres = { "classic", "classic", "classic", "classic", "classic" };
        int[] plays = { 500, 600, 150, 800, 2500 };
        for (int a : solution(genres, plays)) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genrList = new HashMap<String, Integer>(); // 비교
        Map<Integer, PriorityQueue<Song>> genrQueue = new HashMap<Integer, PriorityQueue<Song>>(); // 순서저장
        Map<Integer, Integer> orderList = new HashMap<Integer, Integer>(); // 값 add

        int idx = 0;
        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            int p = plays[i];
            if (!genrList.containsKey(g)) {
                genrList.put(g, idx);
                genrQueue.put(idx, new PriorityQueue<Song>());
                orderList.put(idx, 0);
                idx++;
            }
            int genrIdx = genrList.get(g);
            genrQueue.get(genrIdx).add(new Song(i, p));
            orderList.put(genrIdx, orderList.get(genrIdx) + p);
        }
        idx = 0;
        ArrayList<Integer> answer = new ArrayList<Integer>();
        PriorityQueue<Song> order = new PriorityQueue<Song>((o1, o2) -> o2.value - o1.value);
        for (Map.Entry<Integer, Integer> entry : orderList.entrySet()) {
            order.add(new Song(entry.getKey(), entry.getValue()));
        }
        while (!order.isEmpty()) {
            int i = order.poll().idx;
            PriorityQueue<Song> queue = genrQueue.get(i);
            int it = 0;
            while (!queue.isEmpty() && it < 2) {
                answer.add(queue.poll().idx);
                it++;
                idx++;
            }
        }
        int[] ans = new int[answer.size()];
        idx = 0;
        for (int a : answer) {
            ans[idx++] = a;
        }
        return ans;
    }

    public static class Song implements Comparable<Song> {
        int idx;
        int value;

        Song(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Song o) {
            if (value == o.value)
                return idx - o.idx;
            return o.value - value;
        }
    }
}
