import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static int[] arr;
    static int leafNodes = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        arr = new int[n];

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

        int root = 0;

        dfs(root, -1);

        int q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken()) - 1;

            if (t == 1) {
                if ((k == root && arr[k] >= 2) || (k != root && arr[k] >= 1)) {
                    sb.append("yes");
                } else {
                    sb.append("no");
                }
            } else {
                sb.append("yes");
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int node, int parent) {

        for (int c : tree[node]) {
            if (c != parent) {
                arr[node]++;
                dfs(c, node);
            }
        }
    }
}