package DayByDay._0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q11265
 * @문제이름 : 끝나지 않는 파티
 * @난이도 : Silver I
 * @date : 2022-04-14 오후 12:19
 * @author : pcrmcw0486
 * @문제이해
 * N개의 파티장.
 * 파티장 증축 시 편의를 위해 새로운 파티장과 기존의 모든 파티장이
 * 직접적으로 연결될 수 있는 도로를 만들었다.(일방통행)
 * A->B 직접 연결된 일방통행 도로, A와 B가 아닌 파티장을 경유해서
 * 더 빨리 갈 수 있는 경우가 있을 수 있다.
 * 지금부터 C만큼 시간 뒤에 B번 파티장에서 새롭게 파티가 열리는데,
 * 1번과 같은 이유에 A파티장에서 B파티장까지 파티가 열리는 시간까지
 * 맞춰갈 수 있는지 쉽게 알 수 없다.
 * 1~N개 파티장에 i->j
 * A에서 B로 가는데 C안에 갈 수 있는가를 물어보네요.
 * @알고리즘

 * @접근방법

*/
public class Q11265 {
    static int partySize, querySize;
    static int[][] routes;
    static int[][] optRouteTable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        makePreprocessTable();
        processQuery(br);
    }

    private static void processQuery(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (querySize-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            sb.append(routes[start][end] <= time?"Enjoy other party":"Stay here");
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void makePreprocessTable() {
        optRouteTable = new int[partySize + 1][partySize + 1];
        for (int k = 1; k < partySize + 1; k++) {
            for (int start = 1; start < partySize + 1; start++) {
                for (int end = 1; end < partySize + 1; end++) {
                    int dist = routes[start][k] + routes[k][end];
                    if (dist < routes[start][end]) {
                        routes[start][end] = dist;
                    }
                }
            }
        }
    }

    private static void init(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        partySize = Integer.parseInt(st.nextToken());
        querySize = Integer.parseInt(st.nextToken());
        routes = new int[partySize + 1][partySize+1];
        for (int partyNum = 1; partyNum < partySize + 1; partyNum++) {
            st = new StringTokenizer(br.readLine());
            for (int conPartyNum = 1; conPartyNum < partySize + 1; conPartyNum++) {
                routes[partyNum][conPartyNum] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
