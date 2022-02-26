package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @문제번호 : Q1202
 * @문제이름 : 보석 도둑
 * @난이도 : Gold II
 * @date : 2022-02-12 오전 9:25
 * @author : pcrmcw0486
 * @문제이해
 * 보석점을 털어보자
 * 보석이 총 N개 있고, 각 보석은 무게 M과 가격 V를 가지고 있고,
 * 가방은 K개를 가지고 있다. 각 K마다 가방에 담을 수 있는 최대 무게는 C이다.
 *
 * 가방에는 최대 한개의 보석만 넣을 수 있다.
 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하시오.
 *  1<=N,K<=300,000
 *  0<=M,V<=1,000,000
 *  1<=C<=100,000,000
 * @알고리즘

 * @접근방법
 * 가방 K개를 가지고 있고, 각각의 최대 무게는 C이다.
 * 가방에는 하나만 들어간다..?
 * K개에 대해 O(logN)만큼 탐색
 * 즉,가방에 담을 수 있는 최대 가치 중 들어가는 것을 차례대로 담으면 되겠다.
 * 가방에 담을 수 있는 최대가치?
 *  가치가 높은 것 중에 무게를 만족하는 것을 고르면 된다.
 *   또는 무게를 만족하는 것 중 가치가 높은 것을 고르면 된다.
 *   Point는 큰 가방은 작은 가방의 최대가치를 포함할수 있다.
 *   각 가방마다 최대의 가치를 담아야하는데
 *   (0~N)무게까지의 가치에 그다음 무게가 들어오면
 *   그다음 무게만큼만 검사하면 된다.
*/
public class Q1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] jewelry = new int[N][2];
        int[] bag = new int[K];
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            jewelry[i][0] = Integer.parseInt(st.nextToken());  //무게
            jewelry[i][1] = Integer.parseInt(st.nextToken()); //가치
        }
        for(int i =0;i<K;i++){
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(jewelry, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(bag);
        //작은 가방 부터 챙겨야함.

        //가치가 높은 순서대로
        //만약에 가치가 같다면 무게는 낮은 것 부터
        //사실 무게는 아래에서 control해주기 때문에 크게 신경써주지 않아도 된다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            if(o1[1]==o2[1])
                return o1[0]-o2[0];
            return o2[1]-o1[1];
        });

        int j=0;
        long ans =0;
        for (int k : bag) {
            while (j < jewelry.length) {
                //무게를 여기서 관리한다. heapheap.
                if (jewelry[j][0] >= k) break;
                pq.add(jewelry[j]);
                j++;
            }
            if (!pq.isEmpty()) {
                ans += pq.poll()[1];
            }
        }
        System.out.println(ans);
    }
}
