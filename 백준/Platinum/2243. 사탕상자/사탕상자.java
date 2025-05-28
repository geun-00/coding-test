import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_TASTE = 1_000_000;
    static int[] tree = new int[4 * MAX_TASTE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int b = Integer.parseInt(st.nextToken());
                int taste = query(1, 1, MAX_TASTE, b);
                sb.append(taste).append("\n");
            }
            else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                update(1, 1, MAX_TASTE, b, c);
            }
        }

        System.out.print(sb);
    }

    private static int query(int node, int left, int right, int b) {
        if (left == right) {
            update(1, 1, MAX_TASTE, left, -1);
            return left;
        }

        int mid = (left + right) / 2;

        if (tree[node * 2] >= b) {
            return query(node * 2, left, mid, b);
        }

        return query(node * 2 + 1, mid + 1, right, b - tree[node * 2]);
    }

    private static void update(int node, int left, int right, int b, int c) {
        if (b < left || right < b) return;

        if (left == right) {
            tree[node] += c;
            return;
        }

        int mid = (left + right) / 2;
        update(node * 2, left, mid, b, c);
        update(node * 2 + 1, mid + 1, right, b, c);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}