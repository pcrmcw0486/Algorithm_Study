package solvedac.level4;
/*
2021.12.09
문제번호 : Q1629
이름 및 난이도 : 곱셈 Silver I
문제이해 
-----------------
A^b %c
(A*A*A)%c
A = a*q + b  b=a%c
(a*q+b)(a*q+b) = b^2 = (a%c)^2
b^2 (a*q +b) = b^3 = (a%c)^3

b^3 %c

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1629 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        System.out.println(find(A, B, C));
    }

    public static long find(int A, int B, int C) {
        if (B == 0)
            return 1;
        long tmp = find(A, B / 2, C);
        long result = (tmp * tmp) % C;
        if (B % 2 == 1) {
            result = (result * A) % C;
        }
        return result;
    }
}
