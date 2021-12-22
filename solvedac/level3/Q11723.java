package solvedac.level3;
/*
2021.11.05
문제번호 : Q11723
이름 및 난이도 : 
문제이해 
-----------------
bit연산이나 boolean[] 등으로도 빠르게 가능하다.
너무 갇히지 말자.
접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<Integer>();
        ArrayList<Integer> allList = new ArrayList<Integer>();
        for (int i = 1; i < 21; i++)
            allList.add(i);
        int x = -1;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String option = st.nextToken();
            switch (option) {
            case "add":
                x = Integer.parseInt(st.nextToken());
                if (!set.contains(x))
                    set.add(x);
                break;
            case "remove":
                x = Integer.parseInt(st.nextToken());
                if (set.contains(x))
                    set.remove(x);
                break;
            case "check":
                x = Integer.parseInt(st.nextToken());
                sb.append(set.contains(x) ? 1 : 0).append('\n');
                break;
            case "toggle":
                x = Integer.parseInt(st.nextToken());
                if (set.contains(x))
                    set.remove(x);
                else
                    set.add(x);
                break;
            case "all":
                set.clear();
                set.addAll(allList);
                break;
            case "empty":
                set.clear();
                break;
            }
        }
        System.out.print(sb.toString());

    }
}