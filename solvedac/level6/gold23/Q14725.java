package solvedac.level6.gold23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * @문제번호 : Q14725
 * @문제이름 : 개미굴
 * @난이도 : GoldII
 * @date : 2022-03-10 오후 6:31
 * @author : pcrmcw0486
 * @문제이해
 * 각 층의 먹이정보를 통해 개미굴 그림을 그려보자.
 * @알고리즘
 * @접근방법
 * 먹이 정보 N
 * 먹이정보개수 K개
 * 왼쪽 부터 순서대로 각 층마다 지나온 방에 있는 먹이 정보
*/
public class Q14725 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        Map<String, Map> root = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            Map<String, Map> cur = root;
                    st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            for (int j = 0; j < K; j++) {
                String s = st.nextToken();
                if (!cur.containsKey(s)) {
                    cur.put(s, new HashMap<String, Map>());
                }
                cur = cur.get(s);
            }
        }
        printCave(root, 0);
        System.out.print(sb);
    }

    public static void printCave(Map<String, Map> cur, int depth) {
        List<String> keys = cur.keySet().stream().sorted().collect(Collectors.toList());
        for (String s : keys) {
            sb.append("--".repeat(Math.max(0, depth)));
            sb.append(s);
            sb.append('\n');
            printCave(cur.get(s),depth+1);
        }
    }
}
