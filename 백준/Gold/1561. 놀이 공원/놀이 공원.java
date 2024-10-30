import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] time;
    static int max = 0;
    static long n;
    static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, time[i]);
        }

        if (n <= m) {
            System.out.println(n);
            return;
        }

        long minTime = binarySearch();
        long child = getChild(minTime - 1);

        long res = 0;

        for (int i = 0; i < m; i++) {
            if (minTime % time[i] == 0) {
                child++;
            }
            if (child == n) {
                res = i + 1;
                break;
            }
        }

        System.out.println(res);
    }

    private static long binarySearch() {
        long left = 0;
        long right = n * max;

        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            long child = getChild(mid);

            if (child < n) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }

        return ans;
    }

    private static long getChild(long val) {
        long child = m;
        for (int i = 0; i < m; i++) {
            child += val / time[i];
        }
        return child;
    }
}
