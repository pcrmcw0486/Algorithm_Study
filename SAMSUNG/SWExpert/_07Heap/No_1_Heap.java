package SAMSUNG.SWExpert._07Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//1 연산을 삽입
//2 최대 힙의 루트노드의 키 값을 출력하고 해당 노드를 삭제
public class No_1_Heap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            pq = new PriorityQueue<>(Collections.reverseOrder());
            int N = Integer.parseInt(br.readLine());
            for(int i =0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                switch (st.nextToken()){
                    case "1":
                        pq.add(Integer.parseInt(st.nextToken()));
                        break;
                    case"2":
                        if (pq.isEmpty()) {
                            sb.append("-1");
                        }else{
                            sb.append(pq.poll());
                        }
                        sb.append(' ');
                        break;
                    default:
                        break;
                }
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}
