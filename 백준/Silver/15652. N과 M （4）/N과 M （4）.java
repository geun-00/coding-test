import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        solve(0, "", 1);

        System.out.print(sb);
    }

    private static void solve(int depth, String now, int start) {

        if (depth == m) {
            sb.append(now).append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            solve(depth + 1, now + i + " ", i);
        }
    }
}