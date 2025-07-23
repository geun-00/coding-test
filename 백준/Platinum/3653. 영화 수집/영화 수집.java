import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tree, pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            tree = new int[4 * (n + m + 1)];
            pos = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                pos[i] = m + i;
                update(1, 1, (n + m), pos[i], 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++) {
                int num = Integer.parseInt(st.nextToken());

                int result = query(1, 1, (n + m), 1, pos[num] - 1);
                sb.append(result).append(" ");

                update(1, 1, (n + m), pos[num], 0);
                pos[num] = m - i + 1;
                update(1, 1, (n + m), pos[num], 1);
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) +
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int target, int value) {
        if (target < start || end < target) {
            return;
        }

        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, target, value);
        update(node * 2 + 1, mid + 1, end, target, value);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}