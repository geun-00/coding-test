import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int ans = 0;
    static char[] arr;
    static List<Integer>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        tree = new ArrayList[n];
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

        for (int i = 0; i < n; i++) {
            if (arr[i] == '1') {
                visit = new boolean[n];
                solve(i, arr[i]);
            }
        }

        System.out.println(ans);
    }

    private static void solve(int node, char c) {
        visit[node] = true;

        for (int next : tree[node]) {
            if (!visit[next]) {
                if (arr[next] == '0') {
                    solve(next, arr[next]);
                } else {
                    ans++;
                }
            }
        }

        visit[node] = false;
    }
}