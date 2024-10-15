import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Puddle[] puddles = new Puddle[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            puddles[i] = new Puddle(s, e);
        }

        Arrays.sort(puddles);

        int count = 0;
        int now = 0;

        for (Puddle puddle : puddles) {

            if (now < puddle.start) {
                now = puddle.start;
            }

            while (now < puddle.end) {
                now += l;
                count++;
            }
        }
        System.out.println(count);
    }

    static class Puddle implements Comparable<Puddle> {

        int start, end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Puddle o) {
            return this.start - o.start;
        }
    }
}
