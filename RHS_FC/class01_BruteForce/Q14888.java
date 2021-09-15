package RHS_FC.class01_BruteForce;

/*
 연산자 끼워넣기 SILVER I
 + - / * 
 연산자 우선순위를 무시한다..
 나눗셈은 정수 몫만. > 음수/양수 > 양수로 바꾼뒤 몫을 취하고 그 몫을 음수로 취한다.
 N개의 수와 N-1개의 연산자가 주어질 때 결과의 최대와 최소를 구하라.
 자리 N-1 // 
  > N-1개의 자리에서 4가지 종류에 대하여 주어진 개수만큼 조합하기.
  조합입니다. 4가지를 정해진 개수에 따라 중복있게 조합
  1 1 2 2 

  최대 최소
  연산자 개수 배열
  숫자배열
  
 */
import java.io.*;
import java.util.*;

public class Q14888 {
    static int[] data;
    static int[] operator;
    static int resultMax = Integer.MIN_VALUE;
    static int resultMin = Integer.MAX_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N];
        operator = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        rec_func(1, data[0]);
        System.out.println(resultMax + "\n" + resultMin);
    }

    static void rec_func(int k, int resultTmp) {
        if (k == N) {
            resultMax = Math.max(resultMax, resultTmp);
            resultMin = Math.min(resultMin, resultTmp);
        } else {
            // + - * /
            for (int i = 0; i < 4; i++) {
                if (operator[i] != 0) {
                    operator[i]--;
                    if (i == 0) {
                        rec_func(k + 1, resultTmp + data[k]);
                    } else if (i == 1) {
                        rec_func(k + 1, resultTmp - data[k]);
                    } else if (i == 2) {
                        rec_func(k + 1, resultTmp * data[k]);
                    } else if (i == 3) {
                        int tempa = Math.abs(resultTmp);
                        int tempb = Math.abs(data[k]);
                        int tempc = (int) tempa / tempb;
                        if ((tempa <= 0 && tempc > 0) || (tempa >= 0 && tempc < 0))
                            tempc *= -1;
                        rec_func(k + 1, tempc);
                    }
                    operator[i]++;
                }
            }
        }

    }
}
