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

        int[] level = new int[n];

        int low = Integer.MAX_VALUE;
        int max = 0;

        for (int i = 0; i < n; i++) {
            level[i] = Integer.parseInt(br.readLine());
            low = Math.min(low, level[i]);
            max = Math.max(max, level[i]);
        }

        int high = max + k;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long sum = 0;
            for (int lv : level) {
                sum += Math.max(0, mid - lv);
            }

            if (sum <= k) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}