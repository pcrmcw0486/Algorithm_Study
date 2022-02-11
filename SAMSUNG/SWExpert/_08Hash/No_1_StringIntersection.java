package SAMSUNG.SWExpert._08Hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*

* */
public class No_1_StringIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tt = 1; tt <= T; tt++) {
            sb.append('#').append(tt).append(' ');
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Set<String> set = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toSet());
            st = new StringTokenizer(br.readLine());
            int cnt =0;
            for(int i =0;i<M;i++){
                if (set.contains(st.nextToken())) {
                    cnt++;
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
