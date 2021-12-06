package solvedac.level3;
/*
2021.12.06
문제번호 : Q9019
이름 및 난이도 : DSLR GOld V
문제이해 
-----------------
네 개의 명령어 DSLR 이용 계산기.
0이상 10_000 미만의 십진수 저장가능. 
저장된 n 네 자릿수 d1,d2,d3,d4라고할때
1. D는 n을 두배로 바꾼다. 9999보다 크면 10000으로 나눈 나머지. 2n mod 10000
2. S S는 n-1레지스터 저장 n이 0이라면 9999가 레지스터 저장.
3. L n자릿수를 왼편으로 회전시켜 레지스터 저장. d2 d3 d4 d1
4. R 오른쪽으로 회전 d4 d1 d2 d3 

두 정수 A와 B에 대하여 A를 B로 바꾸는 최소한의 명령어를 생성하는 프로그램.

접근 방법 :
 4way + "최소한" bfs같은데?
 결국 바꿀 수 있는 상태의 모습이 10_000이다.

 역시 string가지고 노는건 시간이 많이 걸림
 + Left Right 부분 계산으로 구하는 것이 빠름. 이러한 부분들에서 시간이 많이 걸림.

제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q9019 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[10001];
            char[] command = new char[10001];
            int[] pre = new int[10001];
            Queue<Integer> queue = new LinkedList<Integer>();
            pre[A] = -1;
            visited[A] = true;
            command[A] = ' ';
            queue.add(A);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == B)
                    break;
                int nxtD = (cur * 2) % 10000;
                int nxtS = (cur - 1) == -1 ? 9999 : cur - 1;
                int nxtL = (cur % 1000) * 10 + cur / 1000;
                int nxtR = (cur % 10) * 1000 + cur / 10;
                if (!visited[nxtD]) {
                    pre[nxtD] = cur;
                    command[nxtD] = 'D';
                    visited[nxtD] = true;
                    queue.add(nxtD);
                }
                if (!visited[nxtS]) {
                    pre[nxtS] = cur;
                    command[nxtS] = 'S';
                    visited[nxtS] = true;
                    queue.add(nxtS);
                }
                if (!visited[nxtL]) {
                    pre[nxtL] = cur;
                    command[nxtL] = 'L';
                    visited[nxtL] = true;
                    queue.add(nxtL);
                }
                if (!visited[nxtR]) {
                    pre[nxtR] = cur;
                    command[nxtR] = 'R';
                    visited[nxtR] = true;
                    queue.add(nxtR);
                }
            }
            int tmp = B;
            StringBuilder sbb = new StringBuilder();
            while (tmp != A) {
                sbb.append(command[tmp]);
                tmp = pre[tmp];
            }
            sb.append(sbb.reverse().toString()).append('\n');
        }
        System.out.print(sb.toString());
    }

}
