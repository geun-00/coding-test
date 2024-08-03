import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            tree = new ArrayList[n + 1];
            visit = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                tree[a].add(b);
                tree[b].add(a);
            }

            int trees = 0;
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    if (dfs(i, -1)) {
                        trees++;
                    }
                }
            }

            sb.append("Case ").append(count++).append(": ");
            if (trees == 0) {
                sb.append("No trees.").append("\n");
            } else if (trees == 1) {
                sb.append("There is one tree.").append("\n");
            } else {
                sb.append("A forest of ").append(trees).append(" trees.").append("\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean dfs(int node, int parent) {

        if (visit[node]) {
            return false;
        }

        visit[node] = true;

        for (int child : tree[node]) {
            if (child != parent && !dfs(child, node)) {
                return false;
            }
        }
        return true;
    }
}
