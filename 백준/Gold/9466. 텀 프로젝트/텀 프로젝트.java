import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] pick;
    static boolean[] team, visit;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            count = 0;

            pick = new int[n + 1];
            team = new boolean[n + 1];
            visit = new boolean[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                pick[i] = Integer.parseInt(st.nextToken());

                if (pick[i] == i) {
                    team[i] = true;
                    count++;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!team[i]) {
                    dfs(i);
                }
            }

            sb.append(n - count).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int num) {

        if (visit[num]) {
            team[num] = true;
            count++;
        } else {
            visit[num] = true;
        }

        if (!team[pick[num]]) {
            dfs(pick[num]);
        }

        visit[num] = false;
        team[num] = true;
    }
}
