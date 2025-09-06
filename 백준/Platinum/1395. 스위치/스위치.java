import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] tree, lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tree = new long[4 * n];
        lazy = new long[4 * n];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            if (t == 0) {
                update(left, right, 0, n - 1, 1);
            } else {
                sb.append(query(left, right, 0, n - 1, 1)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static long query(int left, int right, int start, int end, int node) {
        updateLazy(node, start, end);

        if (end < left || start > right) {
            return 0;
        }

        if (start >= left && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(left, right, start, mid, node * 2) + query(left, right, mid + 1, end, node * 2 + 1);
    }

    private static void update(int left, int right, int nodeStart, int nodeEnd, int node) {
        updateLazy(node, nodeStart, nodeEnd);

        if (left > nodeEnd || right < nodeStart) {
            return;
        }

        if (left <= nodeStart && right >= nodeEnd) {
            lazy[node] ^= 1;
            updateLazy(node, nodeStart, nodeEnd);
            return;
        }

        int mid = (nodeStart + nodeEnd) / 2;

        update(left, right, nodeStart, mid, node * 2);
        update(left, right, mid + 1, nodeEnd, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static void updateLazy(int node, int start, int end) {
        if (lazy[node] == 1) {
            tree[node] = (end - start + 1) - tree[node];

            if (start != end) {
                lazy[node * 2] ^= 1;
                lazy[node * 2 + 1] ^= 1;
            }

            lazy[node] = 0;
        }
    }
}