import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] tree;
    static int[] apple;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        tree = new List[n];
        apple = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[p].add(c);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apple[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, 0, k));
    }

    private static int dfs(int node, int depth, int k) {
        if (depth > k) {
            return 0;
        }

        int ans = apple[node];
        visit[node] = true;

        for (int child : tree[node]) {
            if (!visit[child]) {
                ans += dfs(child, depth + 1, k);
            }
        }

        return ans;
    }
}