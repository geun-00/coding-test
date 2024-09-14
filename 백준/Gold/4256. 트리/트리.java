import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] preOrder;
    static int[] inOrder;
    static int[] postOrder;
    static int index;

    static HashMap<Integer, Integer> inOrderMap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            preOrder = new int[n];
            inOrder = new int[n];

            inOrderMap = new HashMap<>();
            index = 0;

            //전위 순회
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            //중위 순회
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inOrder[i] = Integer.parseInt(st.nextToken());
                inOrderMap.put(inOrder[i], i);
            }

            postOrder = new int[n];
            solve(0, n - 1, 0, n - 1);

            for (int node : postOrder) {
                sb.append(node).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int pre_s, int pre_e, int in_s, int in_e) {
        if (pre_s > pre_e || in_s > in_e) {
            return;
        }

        int root = preOrder[pre_s];

        int rootIndex = inOrderMap.get(root);

        int leftSize = rootIndex - in_s;

        solve(pre_s + 1, pre_s + leftSize, in_s, rootIndex - 1);
        solve(pre_s + leftSize + 1, pre_e, rootIndex + 1, in_e);

        postOrder[index++] = root;
    }
}
