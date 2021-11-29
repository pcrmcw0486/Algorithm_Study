package solvedac.level3;
/*
2021.11.29
문제번호 : Q11279
이름 및 난이도 : 최대 힙 Silver II
문제이해 
-----------------
잘 알려진 자료구조 중 최대 힙이 있음.
배열에 자연수 X를 넣고 가장 큰 값 출력하고 제거.
접근 방법 : 그냥 PQ 사용법, 라이브러리 사용함.
이거 사실 최대힙 구현해보라는 뜻이거든요? 한번 해보겠습니다.
제한 조건 : 
*/

import java.io.*;
public class Q11279 {
    final static int SIZE = 100001;
    public static void main(String[] args) throws IOException{
      //  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
      //  int N = Integer.parseInt(br.readLine());
        // PriorityQueue<Integer> pq  = new PriorityQueue<Integer>(Collections.reverseOrder());
        // for(int i =0;i<N;i++){
        //     int option = Integer.parseInt(br.readLine());
        //     if(option ==0){
        //         if(pq.isEmpty()){sb.append(0).append('\n');}
        //         else sb.append(pq.poll()+"\n");
        //     }else{
        //         pq.add(option);
        //     }
        // }
      
        System.out.print(sb.toString());
    }

    class ArrayMaxHeap{
        private final int CAPACITY;
        private int[] array;
        private int size;

        public ArrayMaxHeap(int capacity){
            CAPACITY = capacity;
            array = new int[CAPACITY];
            for(int i =0;i<CAPACITY;i++){
                array[i] = Integer.MIN_VALUE;
            }
        }

        private int getParentIndex(int index){
            if(index ==0) return -1;
            return (index-1)/2;
        }

        private int getLeftChildIndex(int index){
            int leftChildIndex = (2*index) +1;
            if(leftChildIndex >= size) return -1;
            return leftChildIndex;
        }
        
        private int getRightChildIndex(int index){
            int rightchildIndex = 2*index;
            if(rightchildIndex >= size) return -1;
            return rightchildIndex;
        }
        
        private void heapify(int index){
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);
            int max = index;
            if(left != -1 && array[left]>array[max])
                max = left;
            if(right != -1 && array[right]>array[max])
                max = right;
            if(max != index){
                swap(index, max);
                heapify(max);
            }

        }

        private void swap(int index1, int index2){
            int tmp = array[index1];
            array[index1] = array[index2];
            array[index2] = tmp;
        }

        public void insert(int num){
            if(size == CAPACITY)
                throw new RuntimeException("Heap OverFlow");
            size++;
            int index = size-1;
            array[index] = num;
            int parentIndex = getParentIndex(index);
            //bottom - up
            while(index != 0 && array[parentIndex] < array[index]){
                swap(parentIndex, index);
                index = parentIndex;
                parentIndex = getParentIndex(index);
            }
        }

        public int delete(){
            if(size ==0) return 0;
            int max = array[0];
            array[0] = array[size-1];
            size--;
            heapify(0);
            return max;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int i =0;i<size;i++){
                sb.append(array[i]);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
