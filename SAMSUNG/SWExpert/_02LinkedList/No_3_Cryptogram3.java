package SAMSUNG.SWExpert._02LinkedList;

import java.io.FileInputStream;
import java.util.Scanner;

/*0 ~ 999999 사이 수를 나열하여 만든 암호문
특수제작된 처리기로 수정 가능.
 - operation
  1. I (삽입) x , y , s : 앞에서 x위치 바로 다음에 y개 숫자를 삽입 s는 덧 붙일 숫자.
  2. D(삭제) x, y : x의 위치 바로 다음 부터 y개 숫자를 삭제
  3. A(추가) y,s : 암호문 맨 뒤에 y개 숫자를 붙인다.

* */
public class No_3_Cryptogram3 {
    public static void main(String[] args) throws  Exception{
        //System.setIn(new java.io.FileInputStream("src/Samsung_SW_Expert/preTest/Problem1/sample_input.txt"));
        System.setIn(new FileInputStream("src/SWExpert/_02LinkedList/input.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1 ; test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            int N = sc.nextInt(); // 암호문 길이
            Node head = new Node(-1);
            Node tail = new Node(-1);
            head.nxt = tail;
            tail.prev = head;
            Node cur = head;
            for(int i =0 ;i<N;i++){
                //원 본 데이터
                int S = sc.nextInt();
                cur.nxt = new Node(S);
                cur.nxt.prev = cur;
                cur = cur.nxt;
            }
            cur.nxt = tail;
            tail.prev =cur;
            int M = sc.nextInt();
            int idx = -1;
            int size= -1;
            for(int q = 0; q<M;q++){
                cur = null;
                Node prev = null;
                String option = sc.next();
                switch (option){
                    case "I":
                        idx = sc.nextInt();
                        prev = head;
                        for(int i = 0;i<idx;i++){
                            prev = prev.nxt;
                        }

                        size = sc.nextInt();
                        for(int i =0 ;i<size;i++){
                            int s = sc.nextInt();
                            cur = new Node(s);
                            cur.nxt = prev.nxt;
                            cur.prev = prev;
                            prev.nxt.prev = cur;
                            prev.nxt = cur;
                            prev = cur;
                        }
                        break;
                    case"D":
                        idx = sc.nextInt();
                        size = sc.nextInt();
                        prev = head;
                        for(int i =0 ;i<idx;i++){
                            prev = prev.nxt;
                        }

                        for(int i =0 ;i<size;i++){
                            cur = prev.nxt;
                            cur.nxt.prev = prev;
                            prev.nxt = cur.nxt;
                            cur.nxt = null;
                            cur.prev = null;
                        }

                            break;
                    case "A":
                        size = sc.nextInt();
                        prev = tail.prev;
                        for(int i =0;i<size;i++){
                            int s = sc.nextInt();
                            cur = new Node(s);
                            cur.prev = prev;
                            cur.nxt = prev.nxt;
                            prev.nxt.prev = cur;
                            prev.nxt = cur;
                            prev= cur;
                        }
                        break;
                }
            }
            cur = head.nxt;
            for(int i =0;i<10;i++){
                sb.append(cur.data).append(' ');
                cur = cur.nxt;
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static class Node{
        int data;
        Node nxt;
        Node prev;
        Node(int data){
            this.data = data;
            this.nxt = null;
            this.prev = null;
        }
    }
}
