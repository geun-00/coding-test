import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        tree = new int[4 * n];
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> powerMap = new HashMap<>();
        int power = 1;
        for (int i : sorted) {
            powerMap.put(i, power++);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int best = i + 1;
            power = powerMap.get(arr[i]);

            best -= query(tree, 1, power - 1, 1, n, 1);
            sb.append(best).append("\n");

            update(tree, power, 1, n, 1);
        }

        System.out.print(sb);
    }

    private static int query(int[] tree, int queryLeft, int queryRight, int nodeLeft, int nodeRight, int node) {
        if (queryLeft > nodeRight || queryRight < nodeLeft) {
            return 0;
        }

        if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
            return tree[node];
        }

        int mid = (nodeLeft + nodeRight) / 2;
        return query(tree, queryLeft, queryRight, nodeLeft, mid, node * 2) +
                query(tree, queryLeft, queryRight, mid + 1, nodeRight, node * 2 + 1);
    }

    private static void update(int[] tree, int power, int nodeLeft, int nodeRight, int node) {
        if (power > nodeRight || power < nodeLeft) {
            return;
        }

        if (nodeLeft == nodeRight) {
            tree[node] += 1;
            return;
        }

        int mid = (nodeLeft + nodeRight) / 2;
        update(tree, power, nodeLeft, mid, node * 2);
        update(tree, power, mid + 1, nodeRight, node * 2 + 1);

        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}