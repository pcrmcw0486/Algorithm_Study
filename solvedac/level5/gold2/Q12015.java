package solvedac.level5.gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @문제번호 : Q12015
 * @문제이름 : 가장 긴 증가하는 부분 수열 2
 * @난이도 : Gold II
 * @date : 2022-02-15 오후 1:36
 * @author : pcrmcw0486
 * @문제이해
 * 가장 긴 증가하는 부분 수열을 찾으시오.
 * @알고리즘
 * dp , BinarySearch로도 풀 수 있습니다.
 * @접근방법
 * [ ... ]
 * idx count를 셀텐데요.
 * [min 값이 들어오고, max값이 있어요]
 *
*/
public class Q12015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N]; //실제 값 저장
        int[] memo = new int[N]; //Lis되는 값들을 memo
        int lisSize =0;
        data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //초기화
        memo[lisSize++] = data[0];
        for(int i =1;i<data.length;i++){
            if(data[i] > memo[lisSize-1]){
                memo[lisSize++] = data[i];
            }
            int findIdx = binarySearch(memo,lisSize, data[i]);
            memo[findIdx] = data[i];
        }

        System.out.println(lisSize);
    }
    public static int binarySearch(int[] arr, int size, int value){
        int left =0;
        int right = size-1;
        // 답이 나오는 경우가 [0~~size-1]까지 데이터가 있다고 볼때
        // 답이 나오는 건, 없다면-1이 나올수도 있고, size가 나올 수도 있다.
        // -1이라면 0이 나오는게 맞음.
        // 1 3 5 4라면
        // 1 3 5 고 4가 왔을 때 1 3 4 가 되어야함.
        // 즉 자신보다 작은게 아닌 처음 나오는 큰 값을 찾아야함.
        int ans =0;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid] < value){
                left = mid +1;
                ans = left;
            }else if(arr[mid] > value){
                right = mid -1;
            }else return mid;
        }
        return ans;
    }
}
