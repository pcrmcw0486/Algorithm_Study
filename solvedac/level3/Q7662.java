package solvedac.level3;
/*
2021.12.04
문제번호 : Q7662
이름 및 난이도 : 이중 우선순위 큐
문제이해 
-----------------
삽입 연산 및 삭제 연산 
삽입 : 데이터 삽입 후 우선순위 맞추기
2. 삭제 
    2.1 우선순위 높은 것 삭제
    2.2 우선순위 낮은 것 삭제
Q가 주어질 때 저장된 각 정수 값 자체를 우선순위라고 하자.
Q에 저장된 데이터 중 최댓값과 최솟값.

접근 방법 :
자료구조 만들기 문제 유형이다. 있는 라이브러리를 쓰기보다는 새롭게 생각하도록 한다.
앞 뒤 삭제는 head tail을 통해 쉽게 접근 가능하다.
삽입의 경우 '우선순위'에 맞게 정렬하여야한다.
데이터를 넣을 때 마다 정렬하는 것은 꽤 시간이 걸린다.
삭제의 경우 O(1)으로 가능하기 때문에 데이터 삽입의 시간을 줄여야한다.
정렬되어 있는 자료구조에 값이 들어왔을 때, 정렬이 가장 빠르게 되는 경우?
근데 앞과 뒤까지 모두 정렬이 되어 있어야하는데 힙은 그렇지가 않다.
아니면 PQ두개쓰고 pointer로 추적? 그러기엔 데이터가 너무 많은디 10^6이니까 k가.
 >> 최소 최대 세그먼트 트리 찾아보기.  
 https://real-012.tistory.com/178

 한쪽을 고정시켜두는 것이 필요하다.
 remove(Object o) 함수를 사용해도 되지만 결국 O(N)을 봐야하기도하고,
 BST를 사용할 경우 한쪽으로 치우친 트리의 경우 오래걸리게 된다.
 https://girawhale.tistory.com/14

 1. PQ + map 사용하기.
 한쪽에 대해서만 상태를 추적한다. 
 삭제 연산에 대해 min, max PQ에 대해 수행하면서
 한쪽의 기록을 map을 통해 기록한다. map에는 숫자와 개수를 기록해둔다.
 print시에 한쪽을 기준으로 한다.
제한 조건 : 
*/

import java.io.*;
import java.util.*;
import java.util.TreeMap;

public class Q7662 {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            solution();
        }
        System.out.print(sb.toString());
    }

    // PQ with Map
    // 우선순위를 PQ로 관리하고, Map으로 data들을 관리한다.
    public static void solution() throws IOException {
        PriorityQueue<Integer> minQ = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<Integer>(Collections.reverseOrder());
        Map<Integer, Integer> dataInfo = new HashMap<Integer, Integer>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case "I":
                    minQ.add(val);
                    maxQ.add(val);
                    dataInfo.put(val, dataInfo.getOrDefault(val, 0) + 1);
                    break;
                case "D":
                    PriorityQueue<Integer> q = val == 1 ? maxQ : minQ;
                    removeData(q, dataInfo);
                    break;
            }
        }
        // 8개들어올때 min 4개빼고 max 4개빼면 각각은 모두 있다.
        // empty가 아니다.
        // 생각을 잊지 말자 우리는 data를 dataInfo로 관리하기로 하였다.
        if (dataInfo.size() == 0)
            sb.append("EMPTY\n");
        else {
            int n = removeData(maxQ, dataInfo);
            sb.append(n).append(" ").append(dataInfo.size() > 0 ? removeData(minQ, dataInfo) : n).append('\n');
        }
    }

    public static int removeData(PriorityQueue<Integer> q, Map<Integer, Integer> map) {
        while (!q.isEmpty()) {
            int num = q.poll();
            int numCnt = map.getOrDefault(num, 0);
            if (numCnt == 0)
                continue;
            if (numCnt == 1) {
                map.remove(num); // data없어짐.
            } else
                map.put(num, numCnt - 1);
            return num;
        }
        return 0;

    }

    // TreeMap
    public void solution2() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();// TreeMap생성
        map.put(1, "사과");// 값 추가
        map.put(2, "복숭아");
        map.put(3, "수박");

    }
}
