import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int minDiff = Integer.MAX_VALUE;
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];

                minDiff = Math.min(minDiff, Math.abs(k - sum));

                if (sum <= k) {
                    left++;
                } else {
                    right--;
                }
            }

            left = 0;
            right = n - 1;
            int ans = 0;

            while (left < right) {
                int sum = arr[left] + arr[right];

                if (Math.abs((k - sum)) == minDiff) {
                    ans++;
                }

                if (sum <= k) {
                    left++;
                } else {
                    right--;
                }
            }

            System.out.println(ans);
        }
    }
}