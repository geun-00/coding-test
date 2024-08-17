package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42897">프로그래머스 - Lv.3 : 도둑질</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8F%84%EB%91%91%EC%A7%88">velog</a>
 * @since 2024-08-17
 */
public class Stealing {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{1, 2, 3, 1}));
    }

    private static int solution(int[] money) {

        int n = money.length;

        int[] dp1 = new int[n - 1];
        dp1[0] = dp1[1] = money[0];

        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
        }

        int[] dp2 = new int[n];
        dp2[1] = money[1];

        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }

        return Math.max(dp1[dp1.length - 1], dp2[dp2.length - 1]);
    }
}
