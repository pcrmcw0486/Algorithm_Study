package toy.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17298 {
    static Stack<Integer> data;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int tlqkf[];
        data = new Stack<>();
        tlqkf = new int[N];
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tlqkf[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N - 1; i >= 0; i--) {
            data.push(tlqkf[i]);
        }
        while (!data.isEmpty()) {
            int x = data.pop();
            System.out.println("pop" + x);
            find(x);
        }
        System.out.println(sb.toString());
    }

    public static void find(int x) {
        if (!data.isEmpty()) {
            int temp = data.pop();
            System.out.println("pop temp " + temp);
            if (temp <= x)
                find(x);
            else
                sb.append(temp + " ");
            data.push(temp);
        } else {
            sb.append(-1 + " ");
        }
    }
}
