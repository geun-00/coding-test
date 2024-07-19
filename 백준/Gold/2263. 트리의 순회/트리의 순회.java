import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] inOrder;
    static int[] postOrder;
    static int[] preOrder;
    static int index = 0;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        preOrder = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            map.put(inOrder[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, n - 1, 0, n - 1);

        StringBuilder sb = new StringBuilder();

        for (int i : preOrder) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    private static void solve(int ins, int ine, int posts, int poste) {

        if (ins <= ine && posts <= poste) {

            int rootValue = postOrder[poste];
            preOrder[index++] = rootValue;

            int rootIdx = map.get(rootValue);

            int leftSize = rootIdx - ins;

            solve(ins, rootIdx - 1, posts, posts + leftSize - 1);
            solve(rootIdx + 1, ine, posts + leftSize, poste - 1);
        }
    }
}
