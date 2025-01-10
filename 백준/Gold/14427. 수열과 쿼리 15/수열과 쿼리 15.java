import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] tree = new int[4 * n];
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 0, n - 1, tree, arr);

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int query = Integer.parseInt(st.nextToken());

            if (query == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                arr[index] = value;
                update(1, index, 0, n - 1, tree, arr);
            } else {
                sb.append(tree[1] + 1).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static void update(int node, int index, int start, int end, int[] tree, int[] arr) {
        if (index < start || index > end) {
            return;
        }

        if (start == end) {
            tree[node] = start; // 리프 노드 업데이트
            return;
        }

        int mid = (start + end) / 2;

        update(node * 2, index, start, mid, tree, arr);
        update(node * 2 + 1, index, mid + 1, end, tree, arr);

        tree[node] = (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]])
            ? tree[node * 2]
            : tree[node * 2 + 1];
    }

    private static void init(int node, int start, int end, int[] tree, int[] arr) {

        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;

        init(node * 2, start, mid, tree, arr);
        init(node * 2 + 1, mid + 1, end, tree, arr);

        tree[node] = (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]])
            ? tree[node * 2]
            : tree[node * 2 + 1];
    }
}