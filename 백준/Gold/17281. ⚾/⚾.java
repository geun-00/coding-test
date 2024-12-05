import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] player;
    static int[] order = new int[9];
    static int n;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        player = new int[n][9];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[3] = 0;
        solve(1, (1 << 3));

        System.out.println(ans);
    }

    private static void solve(int nowOrder, int visit) {

        if (nowOrder == 9) {
            ans = Math.max(ans, play());
            return;
        }

        for (int i = 0; i < 9; i++) {

            if ((visit & (1 << i)) != 0) continue;

            order[i] = nowOrder;
            solve(nowOrder + 1, visit | (1 << i));
        }
    }

    private static int play() {

        int now = 0;
        int score = 0;

        for (int inning = 0; inning < n; inning++) {

            int out = 0;
            int base = 0;

            while (out < 3) {

                switch (player[inning][order[now]]) {

                    case 0:
                        out++;
                        break;
                    case 1: // 안타
                        score += (base >> 2) & 1;
                        base = ((base << 1) & 0b111) | 0b1;
                        break;

                    case 2: // 2루타
                        score += (base >> 1) & 1;
                        score += (base >> 2) & 1;
                        base = ((base << 2) & 0b111) | 0b10;
                        break;

                    case 3: // 3루타
                        score += base & 1;
                        score += (base >> 1) & 1;
                        score += (base >> 2) & 1;

                        base = 0b100;
                        break;

                    case 4: // 홈런
                        score += base & 1;
                        score += (base >> 1) & 1;
                        score += (base >> 2) & 1;
                        score += 1;

                        base = 0;
                        break;
                }

                now = (now + 1) % 9;
            }
        }

        return score;
    }
}