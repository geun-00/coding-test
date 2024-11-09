import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr;
    static int[] team;
    static int[] startTeam;
    static int[] linkTeam;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        team = new int[n];
        startTeam = new int[n / 2];
        linkTeam = new int[n / 2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select(0, 0);

        System.out.println(min);
    }

    private static void select(int start, int depth) {
        if (depth >= n / 2) {
            solve();
            return;
        }

        for (int i = start; i < n; i++) {
            team[i] = 1;
            select(i + 1, depth + 1);
            team[i] = 0;
        }
    }

    private static void solve() {

        int startIdx = 0;
        int linkIdx = 0;

        for (int i = 0; i < n; i++) {
            if (team[i] == 1) startTeam[startIdx++] = i;
            else linkTeam[linkIdx++] = i;
        }

        int start = 0;
        int link = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i != j) {
                    start += arr[startTeam[i]][startTeam[j]];
                    link += arr[linkTeam[i]][linkTeam[j]];
                }
            }
        }

        int diff = Math.abs(start - link);
        min = Math.min(min, diff);
    }
}