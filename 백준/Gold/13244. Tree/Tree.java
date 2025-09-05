import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            if (m != n - 1) {
                System.out.println("graph");
                continue;
            }

            String ans = "tree";
            int root = find(1);
            for (int i = 2; i <= n; i++) {
                if (root != find(i)) {
                    ans = "graph";
                    break;
                }
            }

            System.out.println(ans);
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}