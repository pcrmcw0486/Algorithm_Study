package solvedac.level6.gold1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제번호 : Q2042
 * @문제이름 : 구간 합 구하기
 * @난이도 : Gold I
 * @date : 2022-03-10 오후 6:51
 * @author : pcrmcw0486
 * @문제이해
 * N개의 수가 주어지고, 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려한다.
 * N(1,000,000)과 M(10,000) K(10,000이 주어진다.
 * M은 수 변경 횟수, K는 구간의 합을 구하는 횟수 (질의 개수)
 * 입력의 모든 수는 long이다.
 * @알고리즘
 * 세그멘테이션 트리, 구간 합
 * @접근방법
*/
public class Q2042 {
    static class SegmentTree{
        int size;
        long[] tree;
        SegmentTree(long[] data) {
            size = getSize(data.length);
            tree = new long[size];
            //data시작 지점이 size/2에 위치 (root = 1)
            int idx  = size/2;
            for (long d : data) {
                tree[idx++] = d;
            }
            makeTree(1);
        }

        private void makeTree(int cur) {
            if(cur>=size/2) return;
            makeTree(cur * 2);
            makeTree(cur * 2 + 1);
            tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
        }

        private int getSize(int size) {
            int s = 1;
            while (s <= size) {
                s*=2;
            }
            return s*2;
        }

        private void update(int idx, long value) {
            int child = idx + (size / 2);
            int parent = child/2;
            value = value - tree[child];
            tree[child] += value;
            while (parent >= 1) {
                tree[parent] += value;
                parent = parent/2;
            }
        }

        public long getSum(int idx, int left, int right, int start, int end) {
            if(idx>=size) return 0;
            long ret =0;
            if(right<start || left>end) return 0;
            if(start>=left && right>=end){
            //    System.out.println("["+idx+"] [start: " + start + " - end : " + end + " ] = " + tree[idx]);
                return tree[idx];
            }
            ret += getSum(idx * 2, left, right, start, (start+end)/2);
            ret += getSum(idx * 2 + 1, left, right, (start+end)/2+1, end);
            //System.out.println("["+idx+"] [start: " + start + " - end : " + end + " ] = " + ret);
            return ret;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//data개수
        int M = Integer.parseInt(st.nextToken()); //변경 질의
        int K = Integer.parseInt(st.nextToken()); //구간합 질의
        long[] data = new long[N];
        for (int i = 0; i < N; i++) {
            data[i] = Long.parseLong(br.readLine());
        }
        SegmentTree segTree = new SegmentTree(data);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            if (option == 1) { //변경 질의 b번째수 -> c
                int idx = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                segTree.update(idx-1,value);
            }else{  //b부터 c까지의 합
                //TODO
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long ans  = segTree.getSum(1, left, right, 1, segTree.size/2-1);
                System.out.println(ans);
            }
        }
    }
}
