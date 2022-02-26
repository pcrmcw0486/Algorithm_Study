package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @문제번호 : Review_1202
 * @문제이름 : 보석도둑
 * @난이도 : Gold II
 * @date : 2022-02-19 오전 9:31
 * @author : pcrmcw0486
 * @문제이해

 * @알고리즘
 * 그리디 '무게를 만족하는 최대 가치를 챙기면된다.'
 * 가진 가방 최대 무게에 따라서 선택해야한다.
 * 즉, 가격에 대한 maxheap으로 보석을 정리하고, 해당 보석의 무게를 담을 수 있는 가방을
 * lower_bound로 찾는다.
 * 가치별로 정리해서 높은 가치순으로 살펴보면서 가능한 가방 중 가장 작은 무게로 담을 수 있는 가방을 찾아 없애준다.
 * 이 때 탐색을 lower_bound 이분탐색을 사용한다.'
 * 다르게 접근을 해보자면 다음과 같음
 * 3 2
 * 1 65
 * 5 23
 * 2 99
 * 10
 * 2
 * 라면 무게별로 정렬했을 때 다음과 같음
 * 1(65) ,  2(99) ,  2 ,  5(23),  10
 * 여기서 가장 큰 값 2를 꺼내고 2를 채울 수 있는 2무게 이상의 가장 작은 가방 무게를 찾는거임
 * 2(99)
 * 2 10이 되고, 여기서 2찾아서 빼줌.
 *  이과정을 진행하기 위해 key PQ를 사용하고 이를 돌면서 사용하는 방법도 존재함.
 *  + 여기서 그럼 2, 10일때 2를 어떻게 없애지? 라고 생각할수가 있음
 *  여기서 이제 (묶어주는 작업) 즉, 2도착하면 다음 큰 값까지의 point를 하나로 퉁쳐줄 수 있는 하나의 작업이
 *  Union-find disjointset임. 알고리즘 자체는 쉬운데, 적용하는 방법이 이거구나.
 *  그렇게 사용해서 1(65)그 다음 큰 보석을 찾으면 lowerBound는 2인데
 *  2인 무게로 왔을 때 아 얘는 10으로 가세요 라고 적혀있다면
 *  10으로 가서 채택하는 방식임.
 *  또 다른 방법은 이러한 작업에서 이동이 아니라 직접 삭제를 시켜주기 위해
 *  BinaryTree를 사용하는 방법임.
 *  즉 보석도둑 문제는 그리디로 '필수 만족 조건'을 먼저 만족하고 '다음 조건'을 생각하는
 *  '필수조건' 에 대한 생각을 하게 해줬네.
 * @접근방법
*/
public class Review_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Jewelry> jewelries = new PriorityQueue<>();
        PriorityQueue<Integer> bag = new PriorityQueue<>();
        for(int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries.add(new Jewelry(w, v));
        }
        for (int i = 0; i < K; i++) bag.add(Integer.parseInt(br.readLine()));
         PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
         long ans =0;
        while(!bag.isEmpty()){
            int w = bag.poll();
            while (!jewelries.isEmpty() && (jewelries.peek().weight <= w)) {
                pq.add(jewelries.poll().value);
            }
            if(!pq.isEmpty()) ans += pq.poll();
        }
        System.out.println(ans);
    }
    static class Jewelry implements Comparable<Jewelry>{
        int weight;
        int value;
        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }
}
