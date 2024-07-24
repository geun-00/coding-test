package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43105">프로그래머스 - Lv.3 : 정수 삼각형</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A0%95%EC%88%98-%EC%82%BC%EA%B0%81%ED%98%95">velog</a>
 * @since 2024-07-22
 */
public class IntTriangle {
    public static void main(String[] args) {

        System.out.println(solution(new int[][]
                {
                        {7},
                        {3, 8},
                        {8, 1, 0},
                        {2, 7, 4, 4},
                        {4, 5, 2, 6, 5},
                }
        ));
    }

    private static int solution(int[][] triangle) {

        int len = triangle.length;

        int[][] dp = new int[len][len];

        //마지막 행 초깃값 저장
        for (int i = 0; i < len; i++) {
            dp[len - 1][i] = triangle[len - 1][i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }
}
