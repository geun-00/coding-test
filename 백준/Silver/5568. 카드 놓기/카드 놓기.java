import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    static HashSet<Integer> set = new HashSet<>();
    static int[] arr;
    static boolean[] visit;
    static int n, k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        arr = new int[n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solve(0, "");

        System.out.println(set.size());
    }

    private static void solve(int depth, String num) {
        if (depth == k) {
            set.add(Integer.parseInt(num));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                solve(depth + 1, num + arr[i]);
                visit[i] = false;
            }
        }
    }
}