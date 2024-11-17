import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Student[] students = new Student[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int d = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            students[i] = new Student(name, d, m, y);
        }

        Arrays.sort(students);

        System.out.println(students[n - 1].name);
        System.out.println(students[0].name);

    }

    static class Student implements Comparable<Student> {

        String name;
        int d, m, y;

        public Student(String name, int d, int m, int y) {
            this.name = name;
            this.d = d;
            this.m = m;
            this.y = y;
        }

        @Override
        public int compareTo(Student o) {
            if (this.y != o.y) return this.y - o.y;
            if (this.m != o.m) return this.m - o.m;
            return this.d - o.d;
        }
    }
}