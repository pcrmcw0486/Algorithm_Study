package solvedac.level6.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q14428
 * @문제이름 : 수열과 쿼리 16
 * @난이도 : Gold I
 * @date : 2022-03-11 오후 9:35
 * @author : pcrmcw0486
 * @문제이해
 * 수열 A1...An이 주어질때 다음 쿼리를 수행하는 프로그램 작성
 * 1 : Ai를 v로 바꾼다.
 * 2 : Ai~Aj에서 크기가 가장 작은 값의 '인덱스'를 출력한다. 그러한 값이 여러개라면
 * 인덱스가 작은 것을 출력한다.
 * 값을 찾는데 logN 인덱스 찾는데 logN
 * setTree구조를 '최소 값의 인덱스를 가지게 하면될거같은데'
 * 인덱스를 기록해두고 참조해서 비교하는걸로하자.
 * @알고리즘

 * @접근방법

*/
public class Q14428 {
    static int[] data;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        data = new int[N + 1];
        tree = new int[4 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++)data[i] = Integer.parseInt(st.nextToken());
        data[0] = Integer.MAX_VALUE;
        initTree(1,N,1);
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (option == 1) {
                // 바꾸기
                data[a] = b;
                update(1, N, 1, a);
            } else if (option == 2) {
                //출력
                sb.append(getIdx(a, b, 1, N , 1)).append('\n');
            }
        }
        System.out.print(sb);
    }

    public static int getIdx(int queryLeft, int queryRight, int left, int right, int node) {
        if(queryRight < left || queryLeft > right) return 0;
        if(queryLeft<=left && queryRight>=right) return tree[node];
        int mid =(left+right)/2;
        int l = getIdx(queryLeft, queryRight, left, mid, 2*node);
        int r = getIdx(queryLeft, queryRight, mid + 1, right, 2*node+1);
        if (data[l] <= data[r]) {
            return l;
        }
        return r;
    }

    public static int update(int start, int end, int node, int idx) {
        if(idx<start || idx>end) return tree[node];
        if(start == end) return start;
        int mid = (start+end)>>1;
        int l = update(start, mid, node * 2, idx);
        int r = update(mid + 1, end, node * 2 + 1, idx);
        return tree[node] = data[l] <= data[r] ? l : r;
    }
    public static void initTree(int start, int end, int node) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int mid = (start+end)/2;
        initTree(start, mid, node * 2);
        initTree(mid + 1, end, node * 2 + 1);
        int l = tree[2*node];
        int r = tree[2*node+1];
        tree[node] = data[l] <= data[r] ? l : r;
    }
}
