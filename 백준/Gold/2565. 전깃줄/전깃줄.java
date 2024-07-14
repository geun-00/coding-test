import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Line implements Comparable<Line> {
        int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            if (this.a == o.a) {
                return this.b - o.b;
            }
            return this.a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j <= i; j++) {
                if (lines[i].b > lines[j].b && dp[i] + 1 == dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
    }
}
