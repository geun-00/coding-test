import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final char ZERO = '0';
    static final char POSITIVE = '+';
    static final char NEGATIVE = '-';

    static long[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {

            StringTokenizer st = new StringTokenizer(input);

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            tree = new long[4 * n];

            long[] arr = new long[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            init(1, 0, n - 1, arr);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();

                if ("C".equals(command)) {
                    int index = Integer.parseInt(st.nextToken()) - 1;
                    long value = Long.parseLong(st.nextToken());
                    update(1, 0, n - 1, index, value);
                } else {
                    int start = Integer.parseInt(st.nextToken()) - 1;
                    int end = Integer.parseInt(st.nextToken()) - 1;

                    long result = query(1, 0, n - 1, start, end);

                    if (result == 0) {
                        sb.append(ZERO);
                    } else if (result > 0) {
                        sb.append(POSITIVE);
                    } else {
                        sb.append(NEGATIVE);
                    }
                }
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static long query(int node, int start, int end, int left, int right) {

        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        return query(node * 2, start, mid, left, right) *
                query(node * 2 + 1, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int index, long value) {

        if (index < start || index > end) {
            return;
        }

        if (start == end) {
            tree[node] = getSign(value);
            return;
        }

        int mid = (start + end) / 2;

        update(node * 2, start, mid, index, value);
        update(node * 2 + 1, mid + 1, end, index, value);

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    private static void init(int node, int start, int end, long[] arr) {
        if (start == end) {
            tree[node] = getSign(arr[start]);
            return;
        }

        int mid = (start + end) / 2;
        init(node * 2, start, mid, arr);
        init(node * 2 + 1, mid + 1, end, arr);

        tree[node] = tree[node * 2] * tree[node * 2 + 1];
    }

    private static int getSign(long value) {
        if (value > 0) return 1;
        if (value < 0) return -1;
        return 0;
    }
}