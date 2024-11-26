import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int leafNodes = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(0, 0);

        System.out.println(leafNodes % 2 == 1 ? "Yes" : "No");
    }

    private static void dfs(int node, int depth) {

        visit[node] = true;
        int child = 0;

        for (int c : tree[node]) {
            if (!visit[c]) {

                child++;
                dfs(c, depth + 1);
            }
        }

        if (child == 0) {
            leafNodes += depth;
        }
    }
}