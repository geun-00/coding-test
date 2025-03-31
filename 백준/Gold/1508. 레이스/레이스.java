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
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int low = 1;
        int high = arr[k - 1] - arr[0];
        int dist = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            int count = 1;
            int last = arr[0];

            for (int i = 1; i < k; i++) {
                if (arr[i] - last >= mid) {
                    count++;
                    last = arr[i];
                }
            }

            if (count >= m) {
                dist = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        int[] ans = new int[k];
        ans[0] = 1;
        int last = arr[0];

        int count = 1;

        for (int i = 1; i < k; i++) {
            if (arr[i] - last >= dist) {
                last = arr[i];
                ans[i] = 1;
                if (++count == m) break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(ans[i]);
        }
        System.out.print(sb);
    }
}