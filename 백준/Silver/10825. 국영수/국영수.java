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
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());

            students[i] = new Student(name, korean, english, math);
        }

        StringBuilder sb = new StringBuilder();

        Arrays.stream(students)
              .sorted((a, b) -> {
                  if (a.korean != b.korean) {
                      return Integer.compare(b.korean, a.korean);
                  }
                  if (a.english != b.english) {
                      return Integer.compare(a.english, b.english);
                  }
                  if (a.math != b.math) {
                      return Integer.compare(b.math, a.math);
                  }
                  return a.name.compareTo(b.name);
              })
              .map(Student::getName)
              .forEach(name -> sb.append(name).append("\n"));

        System.out.print(sb);
    }

    static class Student {
        String name;
        int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }
    }
}