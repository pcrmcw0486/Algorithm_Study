package solvedac.level3;
/*
2021.11.29
문제번호 : Q18870
이름 및 난이도 : 좌표압축 Silver II
문제이해 
-----------------
수직선 위에 N개의 좌표가 있다. 
Xi를 좌표 압축한 X'i 는 Xi > Xj를 만족하는 서로 다른 좌표의 개수와 같아야한다. 
ex) 2 4 -10 4 -9 > sorting -10 -9 2 4 4
    0 1 2 3 3  >> 2 3 0 3 1
접근 방법 :
N 10^6   Xi의 값 Int 범위.
NlogN(sort) 
map에 put O(1) * N   + NlogN + O(N)
쉽게 생각하면 set에 넣고 리스트 변환해서 소팅때리고
count개수 세고 map에 value값으로 넣어주고 입력값 돌면서 value값 주면되는데..

더 빠른 방법이 있을까?
제한 조건 : 
*/

import java.io.*;
import java.util.*;
public class Q18870 {
    public static void main(String[] args)throws IOException{ 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int[] data = new int[N];
        for(int i =0;i<N;i++){
            data[i] = Integer.parseInt(st.nextToken());
           pq.add(data[i]); // 
        }
        Map<Integer,Integer> ref = new HashMap<Integer,Integer>();
        int prev = Integer.MAX_VALUE;
        int cnt =0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            if(cur != prev){
                ref.put(cur,cnt);
                prev = cur;
                cnt++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<N;i++){
            sb.append(ref.get(data[i])).append(" ");
        }
        System.out.print(sb.toString());
    }
}
