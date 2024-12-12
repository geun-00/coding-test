import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static long maxPossible;
    static int ans = -1;
    static long diff = Long.MAX_VALUE;
    static long[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String song = st.nextToken();

            for (int j = 0; j < m; j++) {
                if (song.charAt(j) == 'Y') {
                    arr[i] |= (1L << (m - j - 1));
                }
            }
        }

        maxPossible = (1L << m) - 1;

        for (int i = 1; i <= n; i++) {
            solve(0, i, 0, 0);
        }

        System.out.println(ans);
    }

    private static void solve(int depth, int limit, int visit, int start) {

        if (depth == limit) {
            check(visit, limit);
            return;
        }

        for (int i = start; i < n; i++) {

            if ((visit & (1 << i)) != 0) {
                continue;
            }

            solve(depth + 1, limit, visit | (1 << i), i + 1);
        }
    }

    private static void check(int visit, int limit) {

        long temp = 0L;

        for (int i = 0; i < n; i++) {

            if ((visit & (1 << i)) != 0) {

                temp |= arr[i];
            }
        }

        if (temp != 0) {
            long d = maxPossible - temp;
            if (d < diff) {
                diff = d;
                ans=Math.max(ans, limit);
            }
        }
    }
}