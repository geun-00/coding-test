import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        solve(n, 0, "");

        if (list.size() < k) {
            System.out.println(-1);
            return;
        }

        System.out.println(list.get(k - 1));
    }

    private static void solve(int n, int sum, String str) {
        if (sum == n) {
            list.add(str.substring(0, str.length() - 1));
            return;
        }

        if (sum > n) {
            return;
        }

        solve(n, sum + 1, str + "1+");
        solve(n, sum + 2, str + "2+");
        solve(n, sum + 3, str + "3+");
    }
}