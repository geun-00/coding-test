import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int a, b, n;
    static int ans = -1;
    static int[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String temp = st.nextToken();

        a = Integer.parseInt(temp);
        b = Integer.parseInt(st.nextToken());
        n = temp.length();

        arr = new int[n];
        visit = new boolean[n];

        int index = 0;
        while (a > 0) {
            arr[index] = a % 10;
            a /= 10;
            index++;
        }

        solve(0, 0);
        System.out.println(ans);
    }

    private static void solve(int depth, int num) {
        if (depth == n) {
            if (num < b) {
                ans = Math.max(ans, num);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (depth == 0 && arr[i] == 0) continue;
            if (!visit[i]) {
                visit[i] = true;

                solve(depth + 1, num * 10 + arr[i]);

                visit[i] = false;
            }
        }
    }
}