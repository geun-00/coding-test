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

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 10000;
        int ans = 0;

        while (left <= right) {

            int mid = (left + right) / 2;

            int count = 1;
            int min = arr[0];
            int max = arr[0];

            for (int i = 1; i < n; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);

                if (max - min > mid) {
                    count++;
                    min = arr[i];
                    max = arr[i];
                }
            }

            if (count <= m) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}