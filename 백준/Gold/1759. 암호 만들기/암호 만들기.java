import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static char[] arr, pwd;
    static int l, c;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new char[c];
        pwd = new char[l];

        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        solve(0, 0);

        System.out.print(sb);
    }

    private static void solve(int depth, int start) {

        if (depth == l) {

            int v = 0, c = 0;
            for (char ch : pwd) {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    v++;
                } else {
                    c++;
                }
            }

            if (v >= 1 && c >= 2) {
                sb.append(pwd).append("\n");
            }

            return;
        }

        for (int i = start; i < c; i++) {

            pwd[depth] = arr[i];
            solve(depth + 1, i + 1);
        }
    }
}