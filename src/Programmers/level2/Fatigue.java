package Programmers.level2;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/87946">프로그래머스 - Lv.2 : 피로도</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%94%BC%EB%A1%9C%EB%8F%84">velog</a>
 * @since 2024-07-13
 */
public class Fatigue {

    static int max = 0;
    static boolean[] visit;

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {
                {80, 20},
                {50, 40},
                {30, 10}
        };

        System.out.println(solution(k, dungeons));
    }

    private static int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);

        return max;
    }

    private static void dfs(int depth, int k, int[][] dungeons) {

        max = Math.max(max, depth);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k) {
                visit[i] = true;
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visit[i] = false;
            }
        }
    }
}
