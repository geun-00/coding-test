import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] tree;
    static char[] color;
    static int[] parent, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            tree[u].add(v);
            tree[v].add(u);
        }

        color = br.readLine().toCharArray();
        parent = new int[n];
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            if (color[i] == 'R') {

                for (int node : tree[i]) {
                    if (color[node] == 'R') {
                        union(i, node);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            arr[find(i)]++;
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (color[i] == 'B') {
                Set<Integer> set = new HashSet<>();
                for (int node : tree[i]) {
                    if (color[node] == 'R') {
                        int root = find(node);

                        if (set.add(root)) {
                            ans += arr[root];
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a < b) {
                parent[b] = a;
            } else {
                parent[a] = b;
            }
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}