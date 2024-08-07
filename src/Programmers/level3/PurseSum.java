package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/161988">프로그래머스 - Lv.3 : 연속 펄스 부분 수열의 합</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%97%B0%EC%86%8D-%ED%8E%84%EC%8A%A4-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9">velog</a>
 * @since 2024-08-07
 */
public class PurseSum {

    public static void main(String[] args) {

        System.out.println(solution(new int[]{2, 3, -6, 1, 3, -1, 2, 4}));
    }

    private static long solution(int[] sequence) {

        int n = sequence.length;

        int[] purse_1 = new int[n];
        int[] purse_2 = new int[n];

        for (int i = 0; i < n; i++) {
            purse_1[i] = sequence[i] * (i % 2 == 0 ? 1 : -1);
            purse_2[i] = sequence[i] * (i % 2 == 0 ? -1 : 1);
        }

        long max_1 = getMax(purse_1, n);
        long max_2 = getMax(purse_2, n);

        return Math.max(max_1, max_2);
    }

    private static long getMax(int[] purse, int n) {

        long[] dp = new long[n];
        dp[0] = purse[0];
        long max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(purse[i], dp[i - 1] + purse[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
