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

            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[n - 1];
            long ans = 0;

            for (int i = n - 2; i >= 0; i--) {
                if (arr[i] <= max) {
                    ans += max - arr[i];
                } else {
                    max = arr[i];
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}