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

        st = new StringTokenizer(br.readLine());
        int left = 1;
        int right = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right += arr[i];
        }

        int minSum = 0;
        int[] ans = new int[m];

        while (left <= right) {
            int mid = (left + right) / 2;

            int[] result = solve(arr, mid, m);
            if (result == null) {
                left = mid + 1;
            } else {
                minSum = mid;
                ans = result;
                right = mid - 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minSum).append("\n");

        for (int i : ans) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }

    private static int[] solve(int[] arr, int mid, int m) {
        int group = 1;
        int count = 0;
        int sum = 0;
        int[] ans = new int[m];
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (group > m || arr[i] > mid) {
                return null;
            }

            if (sum + arr[i] > mid || n - i <= m - group) {
                ans[group - 1] = count;
                group++;
                count = 1;
                sum = arr[i];
            } else {
                count++;
                sum += arr[i];
            }
        }

        if (group > m) {
            return null;
        }

        ans[m - 1] = count;
        return ans;
    }
}