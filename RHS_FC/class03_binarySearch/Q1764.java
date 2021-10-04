package RHS_FC.class03_binarySearch;

/*
https://www.acmicpc.net/problem/1764
듣보잡 Silver IV
듣지못한 사람의 명단과 보지 못한 사람의 명단이 주어질 때, 
듣지도 보지도 못한 사람의 명단을 구하라
듣지 못한 N 
보지 못한 M
N,M <= 500_000
알파벳 소문자20이하.
듣보잡의 수와 그 명단을 사전 순으로 출력한다.
쉽게 Map또는 Set으로 구현할 수도 있겠지만 이분탐색 연습을 위하여
이분탐색으로 구현한다.
N과 M중 작은 수에서 꺼내서 큰 배열에서 검색한다.
어차피 소팅은 두번 해야함. 
 */
import java.io.*;
import java.util.*;

public class Q1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] noListen = new String[N];
        for (int i = 0; i < N; i++) {
            noListen[i] = br.readLine();
        }
        String[] noSee = new String[M];
        for (int i = 0; i < M; i++)
            noSee[i] = br.readLine();
        if (N > M)
            solution(noListen, noSee);
        else
            solution(noSee, noListen);
    }

    public static void solution(String[] list, String[] targets) {
        ArrayList<String> answer = new ArrayList<String>();
        Arrays.sort(list);
        for (String target : targets) {
            if (find(list, target))
                answer.add(target);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append('\n');
        Collections.sort(answer);
        for (String ans : answer) {
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    public static boolean find(String[] list, String target) {
        int l = 0;
        int r = list.length;
        int m;
        while (l < r) {
            if (list[m = l + r >> 1].equals(target))
                return true;
            if (list[m].compareTo(target) < 0)
                l = m + 1;
            else
                r = m;
        }
        return false;
    }
}
