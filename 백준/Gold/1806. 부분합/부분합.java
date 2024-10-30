import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        long[] arr = new long[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 1, right = 1;

        int res = Integer.MAX_VALUE;

        while (right <= n) {
            long sum = arr[right] - arr[left - 1];

            if (sum >= s) {
                res = Math.min(res, right - left + 1);
                left++;
            } else {
                right++;
            }
        }

        System.out.println(res != Integer.MAX_VALUE ? res : 0);
    }
}
