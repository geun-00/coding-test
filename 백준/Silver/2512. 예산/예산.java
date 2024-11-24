import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = 100_001;
        long max = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        long m = Long.parseLong(br.readLine());

        long l = 1, r = max;
        long ans = 0;

        while (l <= r) {

            long mid = (l + r) / 2;

            long sum = 0;

            for (long i : arr) {
                sum += Math.min(mid, i);
            }

            if (sum <= m) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }
}