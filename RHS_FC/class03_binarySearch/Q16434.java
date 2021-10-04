package RHS_FC.class03_binarySearch;

import java.io.*;
import java.util.*;

public class Q16434 {
    public static void main(String[] args) throws IOException {
        // 변수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken());
        long hAtk = Integer.parseInt(st.nextToken());
        long Hp = 0;
        long max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long h = Long.parseLong(st.nextToken());
            switch (t) {
                case 1:
                    // 몬스터방
                    long tmp = h % hAtk == 0 ? (h / hAtk) - 1 : h / hAtk;
                    Hp += tmp * a;
                    max = Math.max(max, Hp);
                    break;
                case 2:
                    // 포션방
                    hAtk += a;
                    Hp -= h;
                    if (Hp < 0)
                        Hp = 0;
                    break;
            }
        }
        System.out.println(max + 1);
    }
}
