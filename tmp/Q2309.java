package tmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q2309 {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        int[] fuck = new int[2];
        int[] data = new int[9];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            data[i] = Integer.parseInt(reader.readLine());
            sum += data[i];
        }
        Arrays.sort(data);
        int target = sum - 100;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 9; i++) {
            if (map.containsKey(target - data[i])) {
                fuck[0] = i;
                fuck[1] = map.get(target - data[i]);
                break;
            }
            map.put(data[i], i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i == fuck[0] || i == fuck[1]) {
                continue;
            }
            sb.append(data[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
