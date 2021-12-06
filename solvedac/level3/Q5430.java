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
사실 head tail만 써도 가능하긴 한데 ArrayDequeue써본다는 마인드로 해보았음.
static 클래스 선언해서 사용하는데에 꽤 어려움이 있었는데, 생각한대로 안되었던 부분이 있음
값을 계속 가지고 있어야하는데 this를 사용할 때는 그 잠시만 가지고 있고 다시 변경이 된다던가
하는 여러 생각과는 다르게 진행되는 부분이 많았기에
static class 관련하여 글을 찾아보고 정리하겠습니다.
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
            reverse = false;
        }

        public void add(int data) {
            dq.addLast(data);
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
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            if (!dq.isEmpty())
                sb.append(this.D());
            while (!dq.isEmpty()) {
                sb.append(',').append(this.D());
            }
            sb.append(']');

            return sb.toString();
        }
    }
}
