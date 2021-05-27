package programmers.kakao.year2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class jewelryShopping {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gems = br.readLine().split(" ");
        // int[] result = solution(gems);
        int[] result = solution2(gems);
        System.out.println(result[0] + " " + result[1]);

    }

    // 내풀이. queue안쓰고 점프
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        // === Without Stream ===
        // String[] jewelrySet = Arrays.stream(gems).distinct().toArray(String[]::new);
        // HashMap<String, Integer> jewelryMap = new HashMap<>();
        // for (int i = 0; i < jewelrySet.length; i++) {
        // jewelryMap.put(jewelrySet[i], i);
        // }

        // Int stream 을 이용한 auto Increment
        List<String> jSet = Arrays.stream(gems).distinct().collect(Collectors.toList());
        Map<String, Integer> jewelryMap = IntStream.range(0, jSet.size()).boxed()
                .collect(Collectors.toMap(i -> jSet.get(i), i -> i));

        // === with Stream ===
        // String[] jewelrySet = Arrays.stream(gems).distinct().toArray(String[]::new);
        // Map<String, Integer> jewelryMap =
        // Arrays.asList(jewelrySet).stream().collect(Collectors.toMap(s -> s, s -> 0));

        // === with Stream, without String[] ===
        // value값 모두 0임.
        // Map<String, Integer> jewelryMap = Arrays.asList(gems).stream().distinct()
        // .collect(Collectors.toMap(s -> s, s -> 0));

        // 이건 Collectors.counting이라 일단, Long이고 빈도수 (개수)가 나옴.
        // Map<String,Long> counts =
        // Arrays.asList(gems).stream().collect(Collectors.groupingBy(s->s,
        // Collectors.counting()));

        int size = jewelryMap.size();
        int[] nextPosition = new int[size];
        Arrays.fill(nextPosition, -1);
        int dist = Integer.MAX_VALUE;
        int parseMin = 0;
        int start = 0;
        int end = 1;
        nextPosition[0] = 0;
        int cnt = 1;

        for (int i = 0; i < gems.length; i++) {
            if (gems[start].contentEquals(gems[i])) {
                parseMin = Integer.MAX_VALUE;
                nextPosition[jewelryMap.get(gems[i])] = i;
                for (int j = 0; j < size; j++) {
                    if (nextPosition[j] != -1) {
                        parseMin = Math.min(parseMin, nextPosition[j]);
                    }
                }
                end = i;
                start = parseMin;

            } else {
                if (nextPosition[jewelryMap.get(gems[i])] == -1)
                    cnt++;
                end = i;
                nextPosition[jewelryMap.get(gems[i])] = i;
            }
            if (cnt == size && end - start + 1 < dist) {
                answer[0] = start + 1;
                answer[1] = end + 1;
                dist = end - start + 1;
            }
            System.out.println("start" + start + " end : " + end + " count : " + cnt);
        }
        return answer;
    }

    // 카카오 해설집 기준 풀이
    public static int[] solution2(String[] gems) {
        int[] ans = new int[2];
        Map<String, Integer> countMap = new HashMap<>();
        Set<String> jSet = Arrays.asList(gems).stream().distinct().collect(Collectors.toSet());

        int size = jSet.size();
        int left = 0;
        int right = 0;
        int dist = Integer.MAX_VALUE;
        while (left <= gems.length) {
            if (countMap.size() == size) {
                if (right - left + 1 < dist) {
                    ans[0] = left + 1;
                    ans[1] = right;
                    dist = right - left + 1;
                }
                if (countMap.get(gems[left]) == 1)
                    countMap.remove(gems[left]);
                else
                    countMap.put(gems[left], countMap.get(gems[left]) - 1);

                left++;
            } else {
                if (right < gems.length) {
                    if (!countMap.containsKey(gems[right])) {
                        countMap.put(gems[right], 1);
                    } else {
                        countMap.put(gems[right], countMap.get(gems[right]) + 1);
                    }
                    // 위의 if-else문 줄이면
                    // countMap.put(gems[left],countMap.getOrDefault(gems[left],0)+1)
                } else
                    break;
                right++;
            }

            // System.out.println(left + " " + right);
            // countMap.forEach((K, V) -> System.out.println(K + " : " + V));
        }
        return ans;
    }

    // 해설 + 내풀이 아이디어(사람들이 한거 queue이용하여 점프)
    public static int[] solution3(String[] gems) {
        Queue<String> queue = new LinkedList<>();
        List<String> jSet = Arrays.stream(gems).distinct().collect(Collectors.toList());
        HashMap<String, Integer> countMap = new HashMap<>();

        int start = 0;
        int end = Integer.MAX_VALUE;
        int startPoint = 0;

        for (int i = 0; i < gems.length; i++) {
            countMap.put(gems[i], countMap.getOrDefault(gems[i], 0) + 1);
            queue.add(gems[i]);
            while (true) {
                String temp = queue.peek();
                if (countMap.get(temp) > 1) {
                    queue.poll();
                    start++;
                    countMap.put(temp, countMap.get(temp) - 1);
                } else {
                    break;
                }
            }
            if (countMap.size() == jSet.size() && end > queue.size()) {
                end = queue.size();
                startPoint = start;
            }
        }
        return new int[] { startPoint + 1, startPoint + end };
    }
}
