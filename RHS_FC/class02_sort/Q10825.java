package RHS_FC.class02_sort;
/*
 국영수 Silver IV
 국어, 영어, 수학 점수가 주어질때 주어진 조건으로 학생의 성적을 정렬하라.
 1. 국어 점수가 감소하는 따라 (내림차순)
 2. 국어 점수가 같으면 영어 점수가 증가하는 순서로(오름차순)
 3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로(내림차순)
 4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로
 (Ascii code에서 대문자는 소문자보다 작으므로 사전순으로 앞으로 온다.)

 접근 및 로직.
 생각나는 방법 1 => class 생성해서 Object 정렬 Tim sort.
 */

import java.io.*;
import java.util.*;

public class Q10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Student[] studentList = new Student[N];
        for (int i = 0; i < N; i++) {
            String[] record = br.readLine().split(" ");
            studentList[i] = new Student(record[0], Integer.parseInt(record[1]), Integer.parseInt(record[2]),
                    Integer.parseInt(record[3]));
        }
        Arrays.sort(studentList);
        for (Student s : studentList) {
            sb.append(s.name).append('\n');
        }
        System.out.print(sb.toString());

    }

    private static class Student implements Comparable<Student> {
        String name;
        int korean;
        int math;
        int english;

        Student(String name, int korean, int math, int english) {
            this.name = name;
            this.korean = korean;
            this.math = math;
            this.english = english;
        }

        // 내림 오름 내림 오름 국어 수학 영어 이름
        @Override
        public int compareTo(Student o) {
            return o.korean - korean != 0 ? o.korean - korean
                    : math - o.math != 0 ? math - o.math
                            : o.english - english != 0 ? o.english - english : name.compareTo(o.name);
        }
    }
}
