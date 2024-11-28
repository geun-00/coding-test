import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int sum = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int left = max;
        int right = sum;
        int ans = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            int withdraw = solve(arr, mid);

            if (withdraw <= m) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static int solve(int[] arr, int mid) {

        int now = mid;
        int count = 1;

        for (int num : arr) {
            now -= num;

            if (now < 0) {
                now = mid - num;
                count++;
            }
        }

        return count;
    }
}