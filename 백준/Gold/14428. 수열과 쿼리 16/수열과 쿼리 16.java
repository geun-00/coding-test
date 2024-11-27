import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] tree;
    static int[] arr;
    static int leafIdx;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        arr = new int[n + 1];

        leafIdx = 1 << level;
        tree = new int[leafIdx * 2];

        arr[0] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            tree[leafIdx + i] = i + 1;
        }

        for (int i = leafIdx - 1; i > 0; i--) {
            tree[i] = getMinIndex(tree[i * 2], tree[i * 2 + 1]);
        }

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = a + leafIdx - 1;

            if (q == 1) {
                update(a, b);
            } else {

                b = b + leafIdx - 1;

                sb.append(getMin(a, b)).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int getMinIndex(int left, int right) {

        if (left == 0) return right;
        if (right == 0) return left;

        if (arr[left] == arr[right]) {
            return Math.min(left, right);
        }

        if (arr[left] < arr[right]) {
            return left;
        }

        return right;
    }

    private static int getMin(int s, int e) {

        int min = 0;

        while (s <= e) {

            if (s % 2 == 1) {
                min = getMinIndex(min, tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                min = getMinIndex(min, tree[e]);
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return min;
    }

    private static void update(int idx, int value) {

        arr[idx - leafIdx + 1] = value;

        while (idx > 1) {
            idx /= 2;
            tree[idx] = getMinIndex(tree[idx * 2], tree[idx * 2 + 1]);
        }
    }
}