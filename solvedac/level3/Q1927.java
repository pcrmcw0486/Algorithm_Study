package solvedac.level3;
/*
2021.12.02
문제번호 : Q1927
이름 및 난이도 : 최소 힙.
문제이해 
-----------------

접근 방법 :
제한 조건 : 
*/

import java.io.*;
import java.util.*;

public class Q1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(br.readLine());
            if (val == 0) {
                if (pq.isEmpty())
                    sb.append(0).append('\n');
                else
                    sb.append(pq.poll()).append('\n');
                continue;
            }
            pq.add(val);
        }
        System.out.print(sb.toString());
    }

    public static class ArrayMinHeap {
        private int capacity;
        private int size;
        private int[] arr;

        public ArrayMinHeap(int capacity) {
            this.capacity = capacity;
            arr = new int[capacity];
            size = 0;
        }

        public void add(int num) {
            arr[size] = num;
            addHeapify(size);
            size++;
        }

        public int poll() {
            if (size == 0)
                return -1; // Heap empty
            int result = arr[0];
            arr[0] = arr[--size];
            pollHeapify(0);
            return result;
        }

        // sift-down
        private void pollHeapify(int index) {
            int minIndex = index;
            int leftChildIndex = (index << 1) + 1;
            int rightchildIndex = (index << 1) + 2;
            if (leftChildIndex < size && arr[leftChildIndex] < arr[minIndex]) {
                minIndex = leftChildIndex;
            }
            if (rightchildIndex < size && arr[rightchildIndex] < arr[minIndex]) {
                minIndex = rightchildIndex;
            }
            if (minIndex != index) {
                swap(index, minIndex);
                pollHeapify(minIndex);
            }
        }

        // sift-up
        private void addHeapify(int idx) {
            int temp = (idx - 1) >> 1;
            if (temp < 0)
                return;
            if (arr[temp] > arr[idx]) {
                swap(idx, temp);
                addHeapify(temp);
            }
        }

        private void swap(int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
