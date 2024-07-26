import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {

    static int[] parent;
    static int[] depth;
    static ArrayList<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        depth = new int[n + 1];
        tree = new ArrayList[n + 1];
        visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0); //부모 노드와 깊이 구하기

        StringBuilder result = new StringBuilder();

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            result.append(solveLca(a, b)).append("\n");
        }

        System.out.println(result);
    }

    private static int solveLca(int a, int b) {

        //a노드가 항상 더 깊은 노드가 되도록 한다.
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //두 노드의 깊이 맞추기
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        //최소 공통 조상 찾기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void dfs(int now, int level) {
        visit[now] = true;
        depth[now] = level;

        for (int child : tree[now]) {
            if (!visit[child]) {
                parent[child] = now;
                dfs(child, level + 1);
            }
        }
    }
}
