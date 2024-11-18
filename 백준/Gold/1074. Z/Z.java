import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int r, c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve(n, 0, 0, 0);
    }

    private static void solve(int n, int x, int y, int count) {

        if (n == 0) {
            if (x == r && y == c) {
                System.out.println(count);
                System.exit(0);
            }
            return;
        }

        int half = (1 << (n - 1));

        //좌상단
        if (r < x + half && c < y + half) {
            solve(n - 1, x, y, count);
        }
        //우상단
        else if (r < x + half && c >= y + half) {
            solve(n - 1, x, y + half, count + half * half);
        }
        //좌하단
        else if (r >= x + half && c < y + half) {
            solve(n - 1, x + half, y, count + 2 * half * half);
        }
        //우하단
        else if (r >= x + half && c >= y + half) {
            solve(n - 1, x + half, y + half, count + 3 * half * half);
        }
    }
}