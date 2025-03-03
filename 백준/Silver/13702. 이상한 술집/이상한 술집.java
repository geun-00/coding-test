import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        long left = 1;
        long right = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
            right = Math.max(right, arr[i]);
        }

        long ans = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (long a : arr) {
                count += (a / mid);
            }

            if (count >= k) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}