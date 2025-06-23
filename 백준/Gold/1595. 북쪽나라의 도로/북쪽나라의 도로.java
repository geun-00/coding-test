import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node>[] tree;
    static boolean[] visit;
    static int maxDist = 0;
    static int maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tree = new List[10001];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        String input;
        while ((input = br.readLine()) != null && !input.trim().isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        visit = new boolean[10001];
        dfs(1, 0);

        visit = new boolean[10001];
        dfs(maxNode, 0);

        System.out.println(maxDist);
    }

    private static void dfs(int node, int dist) {
        if (dist > maxDist) {
            maxDist = dist;
            maxNode = node;
        }

        visit[node] = true;
        for (Node next : tree[node]) {
            if (!visit[next.to]) {
                dfs(next.to, dist + next.w);
            }
        }
    }

    static class Node {
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}