package RHS_FC.class02_sort;

/*
수열정렬 Silver IV
P 0~N-1 까지 수를 한번씩 포함하는 수열.
P를 길이가 N인 배열 A에 적용하면 길이가 N인 배열 B
B[P[i]] = A[i]

A가 주어질 때 수열 P를 적용한 결과가
비 내림차순 이 되는 수열을 찾는 프로그램 작성
비내림차순 : ai >= aj(i<j) 사전순.


A를 그러하게 만드는 수열 P를 구하시오.
2 3 1
1 2  3
1 2 0

중복 오름차순
1 2 3 1 1 4
1 1 1 2 3 4
0 3 4 1 2 5
0 3 4 2 1 5 등등.

  //시간제한 2초
        // 원소 1000보다 작음 , N<=50 둘다 int형에서 가능함.
        // 가장 Naiive하게 푸는 방법은
        // sort시켜놓고 배열에서 찾아서 순서대로 index맞춰주는 방법
        // 정렬 NlogN + N^2(찾아서 넣어주는)
        // 2 3 1
        // 1 2 3
        //class 쓰기 싫은데 Object Arrays.sort()는 stable해서 하면되긴하는데.
        // P[i]의 정의 P[i]는 정렬된 배열에서 자신의 index를 의미함.
 */
import java.io.*;
import java.util.*;

public class Q1015 {
    private static class Nums {
        int idx;
        int num;

        Nums(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Nums[] A = new Nums[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = new Nums(i, Integer.parseInt(st.nextToken()));
        }
        int[] P = new int[N];
        Arrays.sort(A, (a, b) -> a.num - b.num);
        for (int i = 0; i < A.length; i++) {
            P[A[i].idx] = i;
        }
        for (int i = 0; i < P.length; i++) {
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

}
