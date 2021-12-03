package solvedac.level3;
/*
2021.12.03
문제번호 : Q5430
이름 및 난이도 : AC  Gold V
문제이해 
-----------------
AC는 정수 배열에 연산을 위함. 시간복잡도
R(뒤집기)
D(버리기)
R 숫자 순서 뒤집기. D는 첫자리 버리기. (비어있다면 에러)
pipelining.
T  /  p  // n // data

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q5430 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String query = br.readLine();
            boolean success = true;
            int N = Integer.parseInt(br.readLine());
            String data = br.readLine();
            data = data.substring(1, data.length() - 1);
            data = data.replace(",", " ");
            StringTokenizer st = new StringTokenizer(data);
            ACarr AC = new ACarr();
            for (int i = 0; i < N; i++) {
                AC.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < query.length(); i++) {
                char option = query.charAt(i);
                switch (option) {
                    case 'D':
                        if (AC.D() == -1) {
                            success = false;
                            break;
                        }
                        break;
                    case 'R':
                        AC.R();
                        break;
                }
            }
            if (success)
                sb.append(AC.toString()).append('\n');
            else
                sb.append("error\n");
        }
        System.out.print(sb.toString());
    }

    static class ACarr {
        static ArrayDeque<Integer> dq;
        static boolean reverse;

        ACarr() {
            dq = new ArrayDeque<Integer>();
            reverse = true;
        }

        public void add(int data) {
            dq.addFirst(data);
        }

        public void R() {
            reverse = !reverse;
        }

        public int D() {
            if (dq.isEmpty())
                return -1;
            if (!reverse) { // 정방향
                return dq.pollFirst();
            } else
                return dq.pollLast();
        }

        @Override
        public String toString() {
            // StringBuilder sb = new StringBuilder();
            // sb.append('[');
            // if (!dq.isEmpty())
            // sb.append(this.D());
            // while (!dq.isEmpty()) {
            // sb.append(',').append(this.D());
            // }
            // sb.append(']');
            return dq.toString();
            // return sb.toString();
        }
    }
}
