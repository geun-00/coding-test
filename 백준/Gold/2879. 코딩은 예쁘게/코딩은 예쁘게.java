import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] current = new int[n];
        int[] right = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) current[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) right[i] = Integer.parseInt(st.nextToken());

        int ans = 0;
        int prev = 0;

        for (int i = 0; i < n; i++) {
            int diff = right[i] - current[i];
            if (prev * diff < 0) {
                ans += Math.abs(prev);
            }
            else if (Math.abs(prev) >= Math.abs(diff)) {
                ans += Math.abs(prev) - Math.abs(diff);
            }

            prev = diff;
        }

        ans += Math.abs(prev);
        System.out.println(ans);
    }
}