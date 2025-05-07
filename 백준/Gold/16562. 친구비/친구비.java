import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        parent = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;

            if (v > w) {
                int temp = v;
                v = w;
                w = temp;
            }

            union(v, w);
        }

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int root = find(i);
            cost[root] = Math.min(cost[root], arr[i]);
        }

        int sum = 0;
        for (int c : cost) {
            if (c != Integer.MAX_VALUE) {
                sum += c;
            }
        }

        System.out.println(sum <= k ? sum : "Oh no");
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}