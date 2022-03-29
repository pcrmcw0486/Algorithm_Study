package DayByDay._0329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author : pcrmcw0486
 * @문제번호 : Q15787
 * @문제이름 : 기차가 어둠을 헤치고 은하수를
 * @난이도 : Silver II
 * @date : 2022-03-29 오후 2:00
 * @문제이해 N개의 기차가 은하수를 건넌다. 20개의 일렬로 된 자석이 있고 한개의 좌석에 한명이 탄다.
 * 기차 번호는 1~N
 * 명령
 * 1 i x : i번째 기차 x번째 좌석에 사람을 태워라 (이미 사람이 있다면 패스)
 * 2 i x : i번째 기차에 x번째 좌석에 앉은 사람이 하차,
 * 3 i : i번째 기차에 앉아 있는 승객들이 모두 한칸씩 뒤로 간다. (뒤는 +1)
 * 20번째 사람은 하차한다.
 * 4 i : 1번째 사람은 하차한다.
 * M번의 명령 후 1번째 기차부터 순서대로 한 기차씩 은하수를 건너는데 조건이 있다
 * 기차는 순서대로 지나가며 기차가 지나갈 때 승객이 앉은 상태를 목록에 기록,
 * @알고리즘 bit연산 20칸 (좌석 1~20칸 1칸에 타면 00000010)이라고 하자.
 * @접근방법
 */
public class Q15787 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Train[] train = new Train[N + 1];
        for (int i = 0; i < N + 1; i++) {
            train[i] = new Train();
        }
        for (int i = 0; i < M; i++) {
            //명령
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    train[Integer.parseInt(st.nextToken())].enter(Integer.parseInt(st.nextToken()) - 1);
                    break;
                case 2:
                    train[Integer.parseInt(st.nextToken())].quit(Integer.parseInt(st.nextToken()) - 1);
                    break;
                case 3:
                    train[Integer.parseInt(st.nextToken())].goBack();
                    break;
                case 4:
                    train[Integer.parseInt(st.nextToken())].goForward();
                    break;
            }
        }
        int ans = 0;
        Set<Integer> trainSet = new HashSet<>();
        for (int i = 1; i < N+1; i++) {
            System.out.println((Integer.toBinaryString(train[i].status)));
            if (!trainSet.contains(train[i].status)) {
                trainSet.add(train[i].status);
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static class Train {
        static int LIMIT = 1 << 20;
        static int FULL = (1 << 20) - 1;
        int status;

        public void enter(int x) {
            status |= 1 << x;
        }

        public void quit(int x) {
            int masking = FULL ^ (1 << x);
            status &= masking;
        }

        public void goBack() {
            status = status << 1;
            status &= FULL;
        }

        public void goForward() {
            status = status >> 1;
        }
    }
}
