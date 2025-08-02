import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] order, kit;
    static int n, k;
    static boolean[] visit;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        order = new int[n];
        kit = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            kit[i] = Integer.parseInt(st.nextToken());
        }

        setOrder(0);
        System.out.println(ans);
    }

    private static void setOrder(int depth) {
        if (depth == n) {
            ans += solve();
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                order[depth] = i;
                setOrder(depth + 1);

                visit[i] = false;
            }
        }
    }

    private static int solve() {
        int weight = 500;
        for (int o : order) {
            weight += (kit[o] - k);

            if (weight < 500) {
                return 0;
            }
        }

        return 1;
    }

}