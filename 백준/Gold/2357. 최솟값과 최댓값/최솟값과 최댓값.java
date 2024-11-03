import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int size = 1;
        while ((1 << size) < n) {
            size++;
        }

        int leafNodeIdx = 1 << size;
        tree = new int[leafNodeIdx * 2][2];

        for (int i = 0; i < tree.length; i++) {
            tree[i][0] = Integer.MAX_VALUE;
            tree[i][1] = Integer.MIN_VALUE;
        }

        for (int i = leafNodeIdx; i < leafNodeIdx + n; i++) {
            tree[i][0]= tree[i][1] = Integer.parseInt(br.readLine());
        }

        int idx = tree.length - 1;
        while (idx > 0) {
            tree[idx / 2][0] = Math.min(tree[idx / 2][0], tree[idx][0]);
            tree[idx / 2][1] = Math.max(tree[idx / 2][1], tree[idx][1]);
            idx--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            a = a + leafNodeIdx - 1;
            b = b + leafNodeIdx - 1;

            sb.append(getMin(a, b)).append(" ").append(getMax(a, b)).append("\n");
        }

        System.out.print(sb);

    }

    private static int getMax(int s, int e) {
        int max = Integer.MIN_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                max = Math.max(max, tree[s][1]);
                s++;
            }
            if (e % 2 == 0) {
                max = Math.max(max, tree[e][1]);
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return max;
    }

    private static int getMin(int s, int e) {

        int min = Integer.MAX_VALUE;

        while (s <= e) {
            if (s % 2 == 1) {
                min = Math.min(min, tree[s][0]);
                s++;
            }
            if (e % 2 == 0) {
                min = Math.min(min, tree[e][0]);
                e--;
            }
            s /= 2;
            e /= 2;
        }

        return min;
    }
}
