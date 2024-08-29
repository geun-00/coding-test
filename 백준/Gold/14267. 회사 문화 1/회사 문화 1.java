import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        result = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int boss = Integer.parseInt(st.nextToken());
            if (boss > 0) {
                tree[boss].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            result[num] += w;
        }

        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append(" ");
        }

        System.out.print(sb);
    }

    private static void dfs(int now) {
        for (int next : tree[now]) {
            result[next] += result[now];
            dfs(next);
        }
    }
}
