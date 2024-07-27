package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42898">프로그래머스 - Lv.3 : 등굣길</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%93%B1%EA%B5%A3%EA%B8%B8">velog</a>
 *
 * @since 2024-07-25
 */
public class WayToSchool {
    public static void main(String[] args) {
        System.out.println(solution(
                4,
                3,
                new int[][]
                        {
                                {2, 2},
                                {2, 2}
                        }
        ));

    }

    private static int solution(int m, int n, int[][] puddles) {

        int[][] dp = new int[n][m];

        for (int[] p : puddles) {
            int x = p[1] - 1;
            int y = p[0] - 1;

            dp[x][y] = -1;
        }

        int mod = 1_000_000_007;
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (dp[i][j] == -1) {
                    continue;
                }

                if (i > 0) {
                    if (dp[i - 1][j] > 0) {
                        dp[i][j] += dp[i - 1][j] % mod;
                    }
                }

                if (j > 0) {
                    if (dp[i][j - 1] > 0) {
                        dp[i][j] += dp[i][j - 1] % mod;
                    }
                }

                dp[i][j] %= mod;
            }
        }

        return dp[n - 1][m - 1];
    }
}
