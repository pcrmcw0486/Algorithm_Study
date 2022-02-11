package solvedac.level5.gold4;

/*
2022.01.13
문제번호 : Q2473
이름 및 난이도 : 세 용액 Gold IV
문제이해 
-----------------
세 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어 내시오.

제한 조건 : 

접근 방법 :
이전에 풀었던 두용액을 풀었던 것을 생각함.
하나를 집고 두용액을 만드는 방법으로 
지정한 x값을 만든다고 생각하면 된다. 
이번엔 BinarySearch로 풀어보자.
a   b|c
a+b < x인 값. 중 가장 큰 값.
a+c > x인 값.
binarysearch의 영역 [a,b] 포함
*/

import java.io.*;
import java.util.*;

public class Q2473 {
    static long[] data;
    static long[] ans;
    static long min = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        data = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
    }
    
    public static void solution() {
        min = Integer.MAX_VALUE;
        ans = new long[3];
        for (int i = 0; i < data.length - 2; i++) {
            long x = data[i];
            binarySearch(i+1, data.length-1, -x);
            //min = Math.min(min, Math.abs(binarySearch(i + 1, data.length - 1, x)));
        }
        //System.out.println(min);
        for(int i =0;i<3;i++){
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

    public static void binarySearch(int left, int right, long target) {
        int l = left;
        int r = right;
      //A+B == -x(==x)
        while(l<r){
            long sum = data[l] + data[r];
            if(Math.abs(sum-target) < min){
                min= Math.abs(sum-target);
                ans[0] = -target;
                ans[1] = data[l];
                ans[2] = data[r];
            }
            if(sum < target){
                //data[l] + data[r] + x < 0
                l++;
            }else{
                r--;
            }
        }

    }
}

/*
public static void solve_TwoPointer() {
        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int sum = data[left] + data[right];
            if (Math.abs(sum) < min) {
                ansMin = data[left];
                ansMax = data[right];
                min = Math.abs(sum);
            }
            if (sum < 0)
                left++;
            else
                right--;
        }
        System.out.println(ansMin + " " + ansMax);
    }
 */