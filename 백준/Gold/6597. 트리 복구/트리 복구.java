import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[] pre, in;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);

            pre = st.nextToken().toCharArray();
            in = st.nextToken().toCharArray();

            int n = pre.length - 1;

            solve(0, n, 0, n);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void solve(int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return;
        }

        char root = pre[preLeft];
        int index = inLeft;

        while (index <= inRight && in[index] != root) {
            index++;
        }

        int left = index - inLeft;

        solve(preLeft + 1, preLeft + left, inLeft, index - 1);
        solve(preLeft + left + 1, preRight, index + 1, inRight);

        sb.append(root);
    }
}