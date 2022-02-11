package SAMSUNG.SWExpert._07Heap.PartialSort;

import java.util.PriorityQueue;

public class UserSolution {
    static int MAX_SIZE = 100000;
    //static PriorityQueue<User> pq;
    static Heap pq;
    User[] ans;
    int idx;

    public void init() {
        //pq = new PriorityQueue<>();
        pq = new Heap();
        ans = new User[10];
        idx = 0;
    }

    public void addUser(int uID, int income) {
        //todo
        pq.add(new User(uID, income));
    }

    int getTop10(int[] result) {
        //insertion Sort

        while (!Heap.isEmpty()) {
            User cur = Heap.poll();
            for (int i = 0; i < 10; i++) {
                if (ans[i] == null) {
                    ans[i] = cur;
                    break;
                }
                if (cur.compareTo(ans[i]) < 0) {
                    User tmp = ans[i];
                    ans[i] = cur;
                    cur = tmp;
                }
            }
        }
        int cnt = 0;
        for (int i = 0; i < 10; i++) {
            if (ans[i] == null) break;
            result[i] = ans[i].id;
            cnt++;
        }
        return cnt;
    }

    static class User implements Comparable<User> {
        int id;
        int income;

        public User(int id, int income) {
            this.id = id;
            this.income = income;
        }

        @Override
        public int compareTo(User o) {
            if (o.income == this.income) {
                return this.id - o.id;
            }
            return o.income - this.income;
        }
    }

    static class Heap {
        static User[] data;
        static int size;

        Heap() {
            data = new User[MAX_SIZE + 1];
            size = 0;
        }

        public static void add(User u) {
            if(size == MAX_SIZE){
                System.out.println("overFlow");
                return;
            }
            data[++size] = u;
            int index = size;
            int parentIdx = getParentIndex(size);
            while(index != 1 && data[parentIdx].compareTo(data[index])>0){
                swap(parentIdx, index);
                index = parentIdx;
                parentIdx = getParentIndex(index);
            }
            //올라가기.
        }
        public static void swap(int u, int v){
            User tmp = data[u];
            data[u] = data[v];
            data[v] = tmp;
        }

        private static int getParentIndex(int index){
            if(index == 1) return -1;
            return index/2;
        }
        private static int getLeftChildIndex(int index){
            int leftChildIndex = index*2;
            if(leftChildIndex>=size) return -1;
            return leftChildIndex;
        }
        private static int getRightChildIndex(int index){
            int rightChildIndex = index*2 +1;
            if(rightChildIndex>=size ) return -1;
            return rightChildIndex;
        }
        public static User poll() {
            if(size == 0) return null;
            User max = data[1];
            data[1] = data[size];
            size--;
            heapify(1);
            return max;
        }

        public static void heapify(int index){
            int left = getLeftChildIndex(index);
            int right = getRightChildIndex(index);
            User max = data[index];
            int maxIdx = index;
            if(left != -1 && data[left].compareTo(max) < 0){
                max = data[left];
                maxIdx = left;
            }
            if(right != -1 && data[right].compareTo(max) <0){
                max = data[right];
                maxIdx = right;
            }
            if(maxIdx != index){
                swap(index, maxIdx);
                heapify(maxIdx);
            }
        }
        public static boolean isEmpty(){
            return size == 0;
        }

    }
}