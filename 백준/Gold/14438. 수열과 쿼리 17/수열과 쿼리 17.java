import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] tree;
    static int leafIdx;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int level = 1;
        while ((1 << level) < n) {
            level++;
        }

        leafIdx = 1 << level;
        tree = new int[leafIdx * 2];

        Arrays.fill(tree, Integer.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            tree[leafIdx + i] = Integer.parseInt(st.nextToken());
        }

        int idx = tree.length - 1;

        while (idx > 1) {
            tree[idx / 2] = Math.min(tree[idx / 2], tree[idx]);
            idx--;
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

    private static int getMin(int s, int e) {

        int min = Integer.MAX_VALUE;

        while (s <= e) {

            if (s % 2 == 1) {
                min = Math.min(min, tree[s]);
                s++;
            }
            if (e % 2 == 0) {
                min = Math.min(min, tree[e]);
                e--;
            }

            s /= 2;
            e /= 2;
        }

        return min;
    }

    private static void update(int idx, int value) {

        tree[idx] = value;

        while (idx > 1) {
            idx /= 2;
            tree[idx] = Math.min(tree[idx * 2], tree[idx * 2 + 1]);
        }
    }
}