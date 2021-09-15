package tmp;

import java.util.*;
import java.io.*;

public class Q1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int count = 0;
        int target = 0;
        while (true) {
            target = 28 * count + S;
            int checkE = target % 15 == 0 ? 15 : target % 15;
            int checkM = target % 19 == 0 ? 19 : target % 19;
            if (checkE == E && checkM == M)
                break;
            count++;
        }
        System.out.println(target);
    }
}
