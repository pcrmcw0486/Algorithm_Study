package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q10775
 * @문제이름 : 공항
 * @난이도 : Gold II
 * @date : 2022-02-15 오전 11:47
 * @문제이해 G개의 게이트 1에서 G까지 번호를 가짐.
 * P개의 비행기가 순서대로 도착할 예정
 * i번째 비행기를 번부터 g 번째 게이트 중 하나에 영구적 도킹
 * 도킹할 수 없다면 폐쇄됨.
 * 비행기를 최대 몇대 까지 도킹시킬 수 있는가?
 * G<=10^5// P<=10^5
 * 비행기를 주어지는 1 ~g 까지중 하나에 넣고 싶다.
 * @알고리즘 query 처리
 * 최대한 많이 넣어야함. 그리디.
 * 그리디
 * @접근방법 순서, 범위. query? 넣을 수 있는가?
 * Naiive하게 접근하면 O(GP) 10^10
 * 최적화를 해보자.
 * 세그먼트 트리 '누적합 + 질의 최적화"
 * <p>
 * (실패)
 * 내가 하고 싶었던게 결국, 다음에 어디로 가시면 됩니다. 를 구현하고 싶어서 처음 생각한게
 * 사이즈로 계싼해서  1 2 3 4 5 6 7 8 9에서 하나씩 빼주는 느낌으로 가다보니
 * 2 2 1이 들어오면 1 0 3 4 5 6 7 8 9가 되고 결국 gate 1 에 대한 질의가 나오면
 * 절대 가능하지 않았다.
 * 그래서 다음 생각한게 어떠한 query를 날려서 그 안에 가능한가를 하고 싶어
 * 세그먼트 트리를 사용하였는데, 이 때도 역시
 * 2 2 1이라면 0 2 0 0 0 0 0 이되서 [1,2]에 대한 질의는 잘 되지만
 * [1,1]이 되면 못찾아 낸다. 결국, 어디에 다음에 어디로 가야하는가를 어떻게 구하는가에 대한 문제엿다.
 * <p>
 * 그리디라는 접근 방법은 맞았고 이 방법에 집중했어야햇는데 true false에 집중한거같기도 하다.
 * 여튼, 처음 생각한것과 비슷하다.
 * 1 2 3 4 5 6 7 8 9 에서 2가 두번 들어올 때 다음 확인할 곳이
 * 1 0 3 4 5 6 7 8 9라 0이 되기 때문에 안되는데, 이렇게 되면 1이 update가 되지 않는다.
 * 이를 업데이트 하기 위해 2가 줄때 (2-1)과 같이 연결해놓고
 * 다음에 2가또 들어오면 (2-1-0) 이 되면 된다. 즉, union&find로 연결해주면 된다는 뜻이다.
 * 바로 밑 자식이 가야할 곳만빠르게 찾을 수 있다면 된다.
 */
public class Q10775 {

    static int[] nextGate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int ans = 0;
        nextGate = new int[G + 1];
        for (int i = 1; i < G + 1; i++) nextGate[i] = i;
        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            int nextG = find(g);
            if (nextG == 0) break;
            //union(nextG-1, g);
            nextGate[nextG] = find(nextGate[nextG]-1);
            ans++;
        }
        System.out.println(ans);
    }

    public static int find(int x) {
        if (nextGate[x] == x) return x;
        return nextGate[x] = find(nextGate[x]);
    }

    //x가 항상 낮은 숫자임을 보장해야함.
    public static void union(int x, int y) {
        if (x > y) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        x = find(x);
        y = find(y);
        nextGate[y] = x;
    }

//    public static int getSize(int G) {
//        int tmp = 1;
//        while (G > tmp) {
//            tmp *= 2;
//        }
//        return tmp * 2;
//    }
//
//    public static int sum(int start, int end, int node, int left, int right) {
//        if (left > end || right < start) return 0;
//        if (left <= start && end <= right) return tree[node];
//        int mid = (start + end) >> 1;
//        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, (node * 2) + 1, left, right);
//    }
//
//    public static void update(int start, int end, int node,int index, int offset) {
//        if (index < start || index > end) return;
//        tree[node] += offset;
//        if(start == end) return;
//        int mid = (start+end)>>1;
//        update(start, mid, node*2, index,offset);
//        update(mid + 1, end, (node * 2) + 1, index, offset);
//    }
}
