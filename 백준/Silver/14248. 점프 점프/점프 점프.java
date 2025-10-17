import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] visited;
    static int ans;
    static int[] stones;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        stones = new int[n];
        visited = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            stones[i] = Integer.parseInt(st.nextToken());
        }

        int s = Integer.parseInt(br.readLine()) - 1;

        dfs(s, n);

        System.out.println(ans);
    }

    private static void dfs(int cur, int n) {
        if (cur < 0 || cur >= n || visited[cur]) {
            return;
        }
        visited[cur] = true;
        ans++;

        dfs(cur - stones[cur], n);
        dfs(cur + stones[cur], n);
    }
}