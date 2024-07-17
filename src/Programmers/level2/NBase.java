package Programmers.level2;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/17687">프로그래머스 - Lv.2 : [3차] n진수 게임</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-3%EC%B0%A8-n%EC%A7%84%EC%88%98-%EA%B2%8C%EC%9E%84">velog</a>
 * @since 2024-07-16
 */
public class NBase {
    public static void main(String[] args) {
        System.out.println(solution(2, 4, 2, 1));
        System.out.println(solution(16, 16, 2, 1));
        System.out.println(solution(16, 16, 2, 2));
    }

    private static String solution(int n, int t, int m, int p) {

        StringBuilder game = new StringBuilder();

        for (int i = 0; i < t * m; i++) {
            game.append(Integer.toString(i, n).toUpperCase());
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < t; i++) {
            result.append(game.charAt(i * m + (p - 1)));
        }

        return result.toString();
    }
}
