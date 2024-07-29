import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int[] subTreeSize;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        subTreeSize = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        getSubTreeSize(r);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());

            result.append(subTreeSize[u]).append("\n");
        }

        System.out.print(result);
    }

    private static void getSubTreeSize(int now) {

        visit[now] = true;
        subTreeSize[now] = 1;

        for (int child : tree[now]) {
            if (!visit[child]) {
                getSubTreeSize(child);

                subTreeSize[now] += subTreeSize[child];
            }
        }
    }
}
