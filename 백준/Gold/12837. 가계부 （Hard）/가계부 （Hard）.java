import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new long[4 * n];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (query == 1) {
                update(p, x,1, 1, n);
            } else {
                sb.append(getResult(p, x, 1, 1, n)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void update(int p, int x, int node, int start, int end) {

        if (start > p || end < p) {
            return;
        }

        if (start == end) {
            tree[node] += x;
            return;
        }

        int mid = (start + end) / 2;

        update(p, x, node * 2, start, mid);
        update(p, x, node * 2 + 1, mid + 1, end);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long getResult(int left, int right, int node, int start, int end) {

        if (right < start || left > end) {
            return 0;
        }

        if (start >= left && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return getResult(left, right, node * 2, start, mid) +
               getResult(left, right, node * 2 + 1, mid + 1, end);
    }
}
