import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dice;
    static int[] opposite = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dice = new int[n][6];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < 6; i++) {
            int maxSide = 0;
            for (int j = 0; j < 6; j++) {
                if (i == j || i == opposite[j]) continue;
                maxSide = Math.max(maxSide, dice[0][j]);
            }

            ans = Math.max(ans, solve(1, maxSide, dice[0][i]));
        }

        System.out.println(ans);
    }

    private static int solve(int depth, int sum, int prevTop) {
        if (depth == dice.length) {
            return sum;
        }

        int nextBottomIndex = -1;
        for (int i = 0; i < 6; i++) {
            if (dice[depth][i] == prevTop) {
                nextBottomIndex = i;
                break;
            }
        }

        int topIndex = opposite[nextBottomIndex];
        int maxSide = 0;
        for (int i = 0; i < 6; i++) {
            if (i == nextBottomIndex || i == topIndex) continue;
            maxSide = Math.max(maxSide, dice[depth][i]);
        }

        return solve(depth + 1, sum + maxSide, dice[depth][topIndex]);
    }
}