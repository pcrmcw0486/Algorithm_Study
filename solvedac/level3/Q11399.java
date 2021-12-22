package solvedac.level3;
/*
2021.11.05
문제번호 : Q11399
이름 및 난이도 : ATM Silver III
문제이해 
-----------------
ATM 1대, N명의 사람이 줄 서있음. Pi분 
사람들이 줄을 서는 순서에 따라 돈을 인출하는데 필요한 시간의 합이 달라짐.
[1,2,3,4,5][ 3,1,4,3,2] = 
접근 방법 :
제한 조건 : 
1000명 1000시간 최악의 경우 1000000 int형 가능.
*/

import java.io.*;
import java.util.*;

public class Q11399 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int sum = 0;
        int prev = 0;
        for (int i = 0; i < a.length; i++) {
            a[i] += prev;
            prev = a[i];
            sum += a[i];
        }
        System.out.println(sum);

    }
}
