package SAMSUNG.SWExpert._02LinkedList;

import java.util.Scanner;

/*
N 개 10억 이하 자연수로 이루어진 수열. M번의 편집이 필요.
인덱스 L의 데이터를 출력하시오.
    -operation
    1. I idx x : a번 인덱스 앞에 b를 추가하고 한칸씩 뒤로 이동한다.
               => a번 인덱스에 b를 추가하고 뒤로 미룬다.
    2. D idx : idx번 자리를 지우고 한 칸씩 앞으로 이동한다.
    3. C idx x : idx 자리를 x로 바꾼다.

* */
public class NO_4_EditSequence {
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for(int test_case = 1 ;test_case<=T;test_case++){
            sb.append('#').append(test_case).append(' ');
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            NodeManager nm = new NodeManager();
            Node item = null;
            Node cur = nm.head;
            for(int i = 0;i<N;i++){
                int s = sc.nextInt();
                item = new Node(s);
                cur.nxt = item;
                cur = item;
            }

            int idx = -1;
            int data = -1;
            for(int i =0 ;i< M ;i++){
                String option = sc.next();

                switch(option){
                    case "I":
                        idx = sc.nextInt();
                        data = sc.nextInt();
                        nm.insertNode(idx,data);
                        break;
                    case "D":
                        idx = sc.nextInt();
                        nm.deleteNode(idx);
                        break;
                    case "C":
                        idx = sc.nextInt();
                        data = sc.nextInt();
                        nm.changeNode(idx,data);
                        break;
                }
                Node n = nm.head;
//                System.out.print(i + " + : ");
//                while(n.nxt != null){
//                    System.out.print(n.nxt.data + " ");
//                    n = n.nxt;
//                }
//                System.out.println();
            }
            //find L
            Node ans = nm.findPrevNode(L);
            sb.append(ans==null?-1:ans.nxt.data).append('\n');
        }
        System.out.print(sb.toString());
    }

    static class Node{
        int data;
        Node nxt;
        Node(int data){
            this.data = data;
            this.nxt = null;
        }
    }
    static class NodeManager{
        Node head;
        NodeManager(){
            head = new Node(-1);
        }

        private Node findPrevNode(int idx){
            Node prev = head;
            for(int i =0 ;i<idx;i++){
                if(prev.nxt == null) return null;
                prev = prev.nxt;
            }

            return prev;
        }
        //I
        public void insertNode(int idx, int data){
            Node prev = findPrevNode(idx);
            Node cur = new Node(data);
            cur.nxt = prev.nxt;
            prev.nxt = cur;
        }
        //D
        public void deleteNode(int idx){
            Node prev = findPrevNode(idx);
            prev.nxt = prev.nxt.nxt;
        }
        //C
        public void changeNode(int idx, int data){
            Node prev = findPrevNode(idx);
            prev.nxt.data = data;
        }

    }
}
