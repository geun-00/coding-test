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

            int t = Integer.parseInt(st.nextToken());

            if (t == 1) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                update(left, right, 0, n - 1, c, 1);
            } else {
                int target = Integer.parseInt(st.nextToken());

                sb.append(query(target, 0, n - 1, 1)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static long query(int target, int start, int end, int node) {
        updateLazy(node, start, end);
        
        if (end < target || start > target) {
            return 0;
        }

        if (start == end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(target, start, mid, node * 2) + query(target, mid + 1, end, node * 2 + 1);
    }

    private static void init(int[] arr, int start, int end, int node) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;

        init(arr, start, mid, node * 2);
        init(arr, mid + 1, end, node * 2 + 1);

        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private static void update(int left, int right, int nodeStart, int nodeEnd, long c, int node) {
        updateLazy(node, nodeStart, nodeEnd);

        if (left > nodeEnd || right < nodeStart) {
            return;
        }

        if (left <= nodeStart && right >= nodeEnd) {
            int range = (nodeEnd - nodeStart + 1);

            if (range % 2 == 1) {
                tree[node] ^= c;
            }

            if (nodeStart != nodeEnd) {
                lazy[node * 2] ^= c;
                lazy[node * 2 + 1] ^= c;
            }

            return;
        }

        int mid = (nodeStart + nodeEnd) / 2;

        update(left, right, nodeStart, mid, c, node * 2);
        update(left, right, mid + 1, nodeEnd, c, node * 2 + 1);

        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    private static void updateLazy(int node, int start, int end) {
        if (lazy[node] != 0) {
            int range = (end - start + 1);

            if (range % 2 == 1) {
                tree[node] ^= lazy[node];
            }

            if (start != end) {
                lazy[node * 2] ^= lazy[node];
                ;
                lazy[node * 2 + 1] ^= lazy[node];
                ;
            }

            lazy[node] = 0;
        }
    }
}