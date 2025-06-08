import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int window = 0;
            int ans = 0;

            for (int i = 0; i < m; i++) {
                window += arr[i];
            }

            if (window < k) {
                ans++;
            }

            int maxCheck = (n == m) ? 1 : n;

            for (int i = 1; i < maxCheck; i++) {
                window -= arr[(i - 1) % n];
                window += arr[(i + m - 1) % n];
                if (window < k) {
                    ans++;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}