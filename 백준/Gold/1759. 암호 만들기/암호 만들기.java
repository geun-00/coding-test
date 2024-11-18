import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char[] arr;
    static int l, c;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new char[c];

        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        solve("", 0, 0, 0);

        System.out.print(sb);
    }

    private static void solve(String pwd, int depth, int vc, int cc) {

        if (depth == l) {
            if (vc >= 1 && cc >= 2) {
                sb.append(pwd).append("\n");
            }
            return;
        }

        for (int i = 0; i < c; i++) {

            if (!pwd.isEmpty() && arr[i] <= pwd.charAt(pwd.length() - 1)) {
                continue;
            }

            char ch = arr[i];

            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                solve(pwd + ch, depth + 1, vc + 1, cc);
            } else {
                solve(pwd + ch, depth + 1, vc, cc + 1);
            }
        }
    }
}