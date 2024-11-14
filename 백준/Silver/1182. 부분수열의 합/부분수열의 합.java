import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, s;
    static int[] arr;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        if (s == 0) {
            ans--;
        }
        System.out.println(ans);
    }

    private static void solve(int sum, int depth) {

        if (depth == n) {
            if (sum == s) {
                ans++;
            }
            return;
        }

        solve(sum, depth + 1);
        solve(sum + arr[depth], depth + 1);
    }
}