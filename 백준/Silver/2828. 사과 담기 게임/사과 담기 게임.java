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

        int j = Integer.parseInt(br.readLine());

        int ans = 0;
        int left = 1;
        int right = m;

        for (int i = 0; i < j; i++) {
            int apple = Integer.parseInt(br.readLine());

            if (left <= apple && apple <= right) continue;

            if (apple < left) {
                int diff = left - apple;
                ans += diff;
                left = apple;
                right -= diff;
            } else {
                int diff = apple - right;
                ans += diff;
                right = apple;
                left += diff;
            }
        }

        System.out.println(ans);
    }
}