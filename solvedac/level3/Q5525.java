package solvedac.level3;
/*
2021.11.29
문제번호 : Q5525
이름 및 난이도 :  IOIOI Silver II
문제이해 
-----------------
N+1 개의 I와 N개의 O로 이루어져 있으면 I와 O이 교대로 나오는 문자열을 PN이라고 한다.
만약 첫 Pattern이 맞다면 뒤에 OI 개수가 그 안에서 가질 수 있는 개수가 됨.
ex) IOIOIOIOIOIOIOIOIOIOI 라고하고
 IOIOIOI 일때 이 뒤를 다 다시 검사하게 되면 시간도 많이 걸림 그냥 뒤에 OI개수를 확인하면됨.
 이를 통해 O(N)으로 검사가 가능하다.
 Pattern 이 처음에 만족하지 못하게되면 그냥 그건 안되는거임.
 그러다 IOIOIOIOI I OIOI 처럼 OI검사중 틀리면 그 부분에서 다시 검사시작하면됨. 

접근 방법 :
  O(NM)은 절대 안됨. O(N)으로 해결해야한다는 것은 '인지'하고있었으나
  생각이 잘 들지 않아 여러 방법으로 접근해봄
  1. Naiive 하게 한다 생각했을 떄 O(NM)이 걸림
  2. KMP처럼 Failure 함수를 둔다.
  3. 근데 생각해보니 Pattern이 어떻게 들어오는지 알고있고
     어떠한 형태인지 알 수 있음. 필요하다는 생각이 들지 않음.
     그래서 생각해보니 I + OI(개수) = P(개수) 를 알아냈고
     OI개수가 3개일때 P(3) 임이 만족함.이를 이용하여
     O(N)에 String을 검사하면서 파악하고자함.
      + toggle형태로 구현하였는데 이 부분을 두개 한번에 보는 방법 또는
      배열을 통해 mod연산을 이용하는 방법이 있을 수 있음.
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();
        int ans = 0;
        int cur = 0;
        while (cur < M - ((2 * N) + 1)) {
            if (S.charAt(cur) == 'I') {
                cur++;
                boolean toggle = true;

                int cnt = 0;
                while (cur < M) {
                    if (toggle) {
                        if (S.charAt(cur) != 'O') {
                            break;
                        }
                    } else {
                        if (S.charAt(cur) != 'I') {
                            break;
                        } else
                            cnt++;
                    }
                    toggle = !toggle;
                    cur++;
                }
                ans += (cnt - N + 1) > 0 ? cnt - N + 1 : 0;
            } else {
                cur++;
            }
        }
        System.out.println(ans);

    }
}
