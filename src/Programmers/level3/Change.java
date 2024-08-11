package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12907">프로그래머스 - Lv.3 : 거스름돈</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B1%B0%EC%8A%A4%EB%A6%84%EB%8F%88">velog</a>
 * @since 2024-08-11
 */
public class Change {

    public static void main(String[] args) {

        System.out.println(solution(5, new int[]{1, 2, 5}));
    }

    private static int solution(int n, int[] money) {

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] += dp[i - m];
                dp[i] %= 1_000_000_007;
            }
        }

        return dp[n];

/*
        //시간 초과
        int[][] dp = new int[n + 1][money.length + 1];

        for (int i = 1; i <= money.length; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= money.length; j++) {
                if (i >= money[j - 1]) {
                    dp[i][j] = dp[i][j - 1] + dp[i - money[j - 1]][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                dp[i][j] %= 1_000_000_007;
            }
        }

        return dp[n][money.length];
*/
    }

}
