package RHS_FC.class02_sort;

/*
단어정렬 Silver V
알파벳 소문자로 이루어진 N개의 단어가 들어오면
1. 길이가 짧은 것 따라
2. 길이가 같다면 사전순으로.
 */
import java.util.*;
import java.io.*;

public class Q1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] datas = new String[N];

        for (int i = 0; i < N; i++) {
            datas[i] = br.readLine();
        }
        Arrays.sort(datas, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length())
                    return o1.compareTo(o2);
                return o1.length() - o2.length();
            };
        });
        StringBuilder sb = new StringBuilder();
        sb.append(datas[0]).append('\n');
        for (int i = 1; i < N; i++) {
            if (!datas[i].equals(datas[i - 1])) {
                sb.append(datas[i]).append('\n');
            }
        }
        System.out.println(sb);
    }
}
