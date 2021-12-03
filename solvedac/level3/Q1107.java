package solvedac.level3;
/*
2021.12.03
문제번호 : Q1107
이름 및 난이도 : 리모컨 Gold V
문제이해 
-----------------
수빈이가 TV를 보고 있음. 일부 숫자 버튼이 고장남.
버튼은  0 ~9 , + - 가 있음.  
채널의 범위는 0<=  <= 500000
이동하려는 채널은 N이다.
고장난 버튼이 주어질 때 채널 N으로 이동하기 위해 눌러야하는 최소 버튼
지금 수빈이가 보고 있는 채널은 100번.

접근 방법 :
일단 주어진 버튼으로 채널과 가장 가까운 범위에 접근하여야함.
즉 
1. 버튼을 눌러 접근하는 것 보다 +,-로 접근하는 것이 가까운 것 아니라면
   ex) 1000 -> 1003 (+3번이 더빠름) 자릿수보다 작다면 +,-접근이 옳다.
2. 버튼을 통해 채널에 접근할 때 1회 가까운 범위로 접근하고 
   이후의 횟수는 +, - 로 접근하여야한다.( | 목표 채널 - 움직인 채널 | 절댓값)

현재 보고 있는 채널은 100
필요한 것은 현재 주어진 버튼으로 가장 가까운 채널을 찾는 것이 옳음.

각자리마다 정해지는 설정 값.?
 X1X2X3X4   X1 기준 가장가까운 값.(첫자리)
 X2X3X4 ( 첫자리에 따라 가장 가깝도록 가장 큰 값 또는 가장 작은 값)
 사실 X1X2X3 정해지는게 가장 가깝도록이 맞음. 
   speical case 1 , 9 
 - 현재 채널 위치와 목표 채널위치 의 차이값
 - 움직이는 채널위치와 목표 채널 위치 값.
    - 목표 채널보다 움직이는 채널위치가 클 때   (len, len+1) 일 때 
    - 목표 채널보다 움직이는 채널위치가 작을 때 (len, len-1) 일때
    - 목표 채널로 바로 이동 가능할 때.
    였는데 그냥 완탐이라네? 어지럽다. 더 나은 방법은 없나요. 내일 봅시다.
    지금은 머리가 아예 꽉 잡혀있어서 절대 안됨.

    납득이 안된다 이거죠...
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[] buttons = new boolean[10];
        int N = Integer.parseInt(br.readLine());
        int Nlen = String.valueOf(N).length();
        int ans = Math.abs(100 - N); // 현재 채널 위치와 목표 채널 위치 차이값.
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            buttons[Integer.parseInt(st.nextToken())] = true;
        }
        int channel = 100;
        // while(channel >=0 & channel <)
    }
}
