package RHS_FC.class06_tree;

/*
이진 검색 트리 Silver I 귀찮은 구현 문제

 */
import java.io.*;

public class Q5639 {
    static StringBuilder sb = new StringBuilder();
    static int[] data;
    static int length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        length = 0;
        data = new int[10001];
        while ((input = br.readLine()) != null) {
            data[length++] = Integer.parseInt(input);
        }
        postorder(0, length);
        System.out.print(sb);
    }

    public static void postorder(int start, int end) {
        if (end - start <= 0)
            return;
        if (end - start == 1) {
            sb.append(data[start]).append('\n');
            return;
        }
        int v = data[start];
        int idx = -1;
        for (idx = start + 1; idx < end; idx++) {
            if (data[idx] > v)
                break;
        }

        postorder(start + 1, idx);
        postorder(idx, end);
        sb.append(v).append('\n');
    }
}
