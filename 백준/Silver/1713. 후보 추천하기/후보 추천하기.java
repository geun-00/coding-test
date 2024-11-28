import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] posted = new boolean[101];
        Student[] students = new Student[101];
        PriorityQueue<Student> qu = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {

            int num = Integer.parseInt(st.nextToken());

            if (posted[num]) {
                students[num].recommends++;
                qu.remove(students[num]);
                qu.offer(students[num]);
            } else {

                if (qu.size() >= n) {
                    Student removed = qu.poll();
                    posted[removed.idx] = false;

                }
                students[num] = new Student(num, 1, i);
                posted[num] = true;
                qu.offer(students[num]);
            }
        }

        for (int i = 1; i <= 100; i++) {
            if (posted[i]) {
                System.out.print(i + " ");
            }
        }

    }

    static class Student implements Comparable<Student> {

        int idx, recommends, time;

        public Student(int idx, int recommends, int time) {
            this.idx = idx;
            this.recommends = recommends;
            this.time = time;
        }

        @Override
        public int compareTo(Student o) {

            if (this.recommends == o.recommends) {
                return this.time - o.time;
            }

            return this.recommends - o.recommends;
        }
    }
}