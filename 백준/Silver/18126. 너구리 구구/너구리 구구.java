import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node>[] tree;
    static long ans = 0;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        visit = new boolean[n];

        for (int i = 0; i < n - 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        solve(0, 0);
        System.out.println(ans);
    }

    private static void solve(int node, long dist) {

        visit[node] = true;
        ans = Math.max(ans, dist);

        for (Node next : tree[node]) {
            if (!visit[next.to]) {
                solve(next.to, dist + next.dist);
            }
        }
    }

    static class Node {
        int to;
        long dist;

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }
    }
}