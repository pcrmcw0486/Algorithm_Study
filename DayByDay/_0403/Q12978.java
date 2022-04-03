package DayByDay._0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q12978
 * @문제이름 : 스크루지 민호2
 * @난이도 : Gold II
 * @date : 2022-04-03 오후 4:36
 * @author : pcrmcw0486
 * @문제이해
 * 천나라에는 N개의 도시가 있다.
 * 최소한 의 비용으로 N-1개 한개의 경로
 *
 *  경찰서를 세우려고 하는데 모든 도시에 건설하기 아깝다.
 *  최소한의 도시에 경찰서를 세워 모든 도로들과 도시들을 감시하고 싶다.
 *  최소한의 도시에 경찰을 세워 모든 도로와 도시를 감시.
 *  경찰서는 해당 도시와 그 도시에 연결된 양방향 도로
 *  최소 몇개를 세워야할까.
 * @알고리즘
 * 스패닝 트리, 트리 dp
 * @접근방법

*/
public class Q12978 {
    static int numOfCity;
    static ArrayList<Integer>[] city;
    static int[][] dp;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        init();
        dp = new int[numOfCity + 1][2];
        isVisited = new boolean[numOfCity +1];
        isVisited[1] =true;
        dfs(1, -1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }


    public static void dfs(int num, int parent) {
        int count =0;
        int childPoliceCnt =0;
        for (int nextCity : city[num]) {
            if (!isVisited[nextCity]) {
                isVisited[nextCity] = true;
                count++;
                dfs(nextCity, num);
                dp[num][0] += dp[nextCity][1];
                dp[num][1] += Math.min(dp[nextCity][0], dp[nextCity][1]);
            }
        }
        dp[num][1]++;
        //isLeaf
        if(count ==0){
            dp[num][0] = 0;
            dp[num][1] = 1;
        }
    }
    //dp[root][exist] = 루트에 경찰이 존재할때

    //dp[a][exist] = 연결된 모든 애들이 noExist
    //dp[a][noExist] = 연결된 모든 애들이 exist;

    static final int  NO_EXIST =0;
    static final int EXIST =1;

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        numOfCity = Integer.parseInt(br.readLine());
        city = new ArrayList[numOfCity + 1];
        for (int i = 0; i < numOfCity + 1; i++) {
            city[i] = new ArrayList<Integer>();
        }
        for (int edgeCnt = 0; edgeCnt < numOfCity-1; edgeCnt++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            city[u].add(v);
            city[v].add(u);
        }

    }

}
