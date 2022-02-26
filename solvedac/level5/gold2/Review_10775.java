package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @문제번호 : Review_10775
 * @문제이름 : 공항
 * @난이도 : GoldII
 * @date : 2022-02-22 오후 2:10
 * @author : pcrmcw0486
 * @문제이해
 * G개의 게이트 (1~G)
 * P개의 비행기가 순서대로 도착. 1~g 게이트 중 하나에 영구적으로 도킹하려 한다.
 * 최대 몇 대 도킹 시킬 수 있을까?
 * @알고리즘
 * 주어진 G 범위 안에 최대한 많은 비행기를 도킹시킨다. 즉, 그리디하게 해야함.
 * X보다 작은 y에 최대로 들어있을 때 X는 최대로 다시 넣을 수 있다.
 * @접근방법
 * 도킹을 할때 최대한 큰 수와 가깝게 넣어야한다.
 * 5 4 3 2 1 이라는 수로 들어온다고 칠 때,
 * 범위가 큰 것 부터 작은 것이기 때문에 범위가 큰 것은 최대한 그에 가깝게 넣어주는 것이
 * 최적을 보장하기 위함임.
 * 그렇게 되면 결국 grouping이 생긴다.
 * disjoint set union-foind

*/
public class Review_10775 {
    static int[] dockStation;
    static int G, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        int cnt =0;
        dockStation = new int[G + 1];
        for(int i =0;i<G+1;i++) dockStation[i] = i;
        for(int i =0;i<P;i++){
            int plane = Integer.parseInt(br.readLine());
            //union(find(plane)-1,find(plane));
            if (!union(find(plane) - 1, find(plane))) {
                break;
            }
            cnt++;
        }
        System.out.println(cnt);

    }
    public static int find(int x){
        if(dockStation[x] == x) return x;
        return dockStation[x] = find(dockStation[x]);
    }
    public static boolean union(int x, int y){
        if(y==0) return false;
        x = find(x);
        dockStation[y] = x;
        return true;
    }
}
