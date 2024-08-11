package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12904">프로그래머스 - Lv.3 : 가장 긴 팰린드롬</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC">velog</a>
 * @since 2024-08-08
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(solution("abcdcba"));
        System.out.println(solution("abacde"));
    }

    private static int solution(String s) {

        int n = s.length();

        boolean[][] dp = new boolean[n][n];
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLen = 2;
            }
        }

        for (int length = 3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    maxLen = length;
                }
            }
        }


        return maxLen;
    }
}
