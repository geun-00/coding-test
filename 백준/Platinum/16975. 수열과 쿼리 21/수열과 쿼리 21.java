import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new long[4 * n];
        lazy = new long[4 * n];

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(arr, 0, n - 1, 1);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());

            if (q == 1) {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                long k = Long.parseLong(st.nextToken());
                update(left, right, 0, n - 1, k, 1);
            }
            else {
                int x = Integer.parseInt(st.nextToken()) - 1;
                sb.append(query(0, n - 1, x, 1))
                  .append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void init(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        init(arr, start, mid, node * 2);
        init(arr, mid + 1, end, node * 2 + 1);
    }

    private static void update(int left, int right, int start, int end, long k, int node) {
        update_lazy(node, start, end);

        if (left > end || right < start) {
            return;
        }

        if (left <= start && right >= end) {
            tree[node] += (end - start + 1) * k;
            if (start != end) {
                lazy[node * 2] += k;
                lazy[node * 2 + 1] += k;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(left, right, start, mid, k, node * 2);
        update(left, right, mid + 1, end, k, node * 2 + 1);
    }

    private static long query(int start, int end, int x, int node) {
        update_lazy(node, start, end);
        if (x < start || x > end) {
            return 0;
        }
        if (start == end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(start, mid, x, node * 2) + query(mid + 1, end, x, node * 2 + 1);
    }

    private static void update_lazy(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }
    }
}