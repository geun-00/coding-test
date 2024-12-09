import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] tree;
    static int[] depth;
    static int total = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken());

        tree = new List[n];
        depth = new int[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            tree[x].add(y);
            tree[y].add(x);
        }

        dfs(s, -1, d);

        System.out.println(total);
    }

    private static int dfs(int node, int parent, int d) {

        int maxDepth = 0;

        for (int next : tree[node]) {

            if (next != parent) {
                int childDepth = dfs(next, node, d) + 1;
                if (childDepth > d) {
                    total += 2;
                }
                maxDepth = Math.max(maxDepth, childDepth);

            }
        }

        return maxDepth;
    }
}