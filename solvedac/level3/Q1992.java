package solvedac.level3;
/*
2021.12.02
문제번호 : Q1992
이름 및 난이도 : 쿼드트리
문제이해 
-----------------
흑백영상 압축 표현 데이터 구조 쿼드트리.
접근 방법 :
재귀 분할정복

제한 조건 : 

성장했구나..
*/

import java.io.*;

public class Q1992 {
    public static int[][] img;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        img = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                img[i][j] = line.charAt(j) - '0';
            }
        }
        String zippedImg = zipImg(0, 0, N);
        System.out.println(zippedImg);
    }

    public static String zipImg(int x, int y, int N) {
        if (N == 1) {
            return String.valueOf(img[x][y]);
        }
        StringBuilder sb = new StringBuilder();

        // 확인.
        if (isPossible(x, y, N)) {
            return String.valueOf(img[x][y]);
            // sb.append(String.valueOf(img[x][y]));
        } else {
            // 4단계 나누어보기
            // 왼쪽 위
            sb.append('(');
            sb.append(zipImg(x, y, N / 2));
            sb.append(zipImg(x, y + N / 2, N / 2));
            sb.append(zipImg(x + N / 2, y, N / 2));
            sb.append(zipImg(x + N / 2, y + N / 2, N / 2));
            sb.append(')');
        }
        return sb.toString();

    }

    public static boolean isPossible(int x, int y, int N) {
        int initvalue = img[x][y];
        for (int i = x; i < x + N; i++) {
            for (int j = y; j < y + N; j++) {
                if (initvalue != img[i][j])
                    return false;
            }
        }
        return true;
    }
}
