package SAMSUNG.SWExpert._07Heap.No5;

import java.util.*;

/*
- 게시글들은 등록되는 시점의 timestamp
- 다른 사용자를 'follow' 그 사용자의 게시글을 볼 수 있다.
- 게시글에 'like'를 추가한다.
- 특정 사용자를 기준으로 자신이 게시한 글과 자신이 'follow'한 사용자의 게시글 중
  우선순위가 높은 글 부터 내림차순으로 최대 10개의 게시글을 보여준다.
  우선순위?
    - 게시된지 1,000초 이내인 게시글일수록.
    - 1000초 이내인 게시글들 중에서는 'like'가 많을 수록
    - like개수가 같다면 timestamp가 높은 게시글일수록.
    - 1000초를 초과한 게시글의 경우 timestamp가 높은' 게시글의 우선순위가 높다. (최근과 가까울수록)

* */
class UserSolution {
    static int curTimeStamp = 0;
    static User[] users;
    static ArrayList<Post> posts;
    public void init(int N) {
        users = new User[N+1];
        for(int i =1;i<N+1;i++) users[i] = new User(i);
        posts = new ArrayList<Post>();
        posts.add(new Post(0,0));
    }

    public void follow(int uID1, int uID2, int timestamp) {
        users[uID1].followSet.add(uID2);
    }

    public void makePost(int uID, int pID, int timestamp) {
        Post newPost = new Post(timestamp, pID);
        users[uID].postSet.add(pID);
        posts.add(newPost);
    }

    public void like(int pID, int timestamp) {
        posts.get(pID).likeUp();
    }

    public void getFeed(int uID, int timestamp, int pIDList[]) {
        PriorityQueue<Post> pq = new PriorityQueue<>();
        curTimeStamp = timestamp;
        for (int p : users[uID].postSet) {
            pq.add(posts.get(p));
        }
        for (int u : users[uID].followSet) {
            for (int p : users[u].postSet) {
                pq.add(posts.get(p));
            }
        }
        ArrayList<Integer> retList = new ArrayList<>();
        int size = pq.size();
        for(int i =0;i<Math.min(10,size);i++){
            pIDList[i] = pq.poll().pid;
        }

    }

    static class User {
        int uid;
        Set<Integer> followSet;
        Set<Integer> postSet;

        User(int uid) {
            this.uid = uid;
            followSet = new HashSet<>();
            postSet = new HashSet<>();
        }
    }

    static class Post implements Comparable<Post> {
        int pid;
        int timestamp;
        int like;

        Post(int timeStamp, int pid) {
            this.pid = pid;
            this.timestamp = timeStamp;
            like = 0;
        }

        public void likeUp() {
            this.like++;
        }

        @Override
        public int compareTo(Post o) {
            //두 게시글 모두 1000초 안 최신 글일때.
            if (curTimeStamp - this.timestamp <= 1000 && curTimeStamp - o.timestamp <= 1000) {
                if(this.like == o.like){
                    return o.timestamp - this.timestamp;
                }
                return o.like - this.like;
            }
            return o.timestamp - this.timestamp;
        }
    }
}
