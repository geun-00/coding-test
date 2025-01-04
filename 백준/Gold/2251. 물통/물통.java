import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static boolean[][][] visit = new boolean[201][201][201];
    static int A, B, C;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        List<Integer> ans = new ArrayList<>();

        solve(0, 0, C, ans);

        StringBuilder sb = new StringBuilder();

        ans.stream()
            .sorted()
            .forEach(n -> sb.append(n).append(" "));

        System.out.print(sb);
    }

    private static void solve(int a, int b, int c, List<Integer> ans) {

        if (visit[a][b][c]) return;

        visit[a][b][c] = true;

        if (a == 0) {
            ans.add(c);
        }

        if (a + b > B) solve(a + b - B, B, c, ans);
        else solve(0, a + b, c, ans);

        if (a + c > C) solve(a + c - C, b, C, ans);
        else solve(0, b, a + c, ans);

        if (b + a > A) solve(A, b + a - A, c, ans);
        else solve(b + a, 0, c, ans);

        if (b + c > C) solve(a, b + c - C, C, ans);
        else solve(a, 0, b + c, ans);

        if (c + a > A) solve(A, b, c + a - A, ans);
        else solve(c + a, b, 0, ans);

        if (c + b > B) solve(a, B, c + b - B, ans);
        else solve(a, c + b, 0, ans);
    }
}