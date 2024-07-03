import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Lecture implements Comparable<Lecture> {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(s, e);
        }

        Arrays.sort(lectures);

        PriorityQueue<Integer> qu = new PriorityQueue<>();

        qu.offer(lectures[0].end);

        for (int i = 1; i < n; i++) {
            if (!qu.isEmpty() && lectures[i].start >= qu.peek()) {
                qu.poll();
            }
            qu.offer(lectures[i].end);
        }

        System.out.println(qu.size());
    }

}
