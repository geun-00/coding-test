package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12979">프로그래머스 - Lv.3 : 기지국 설치</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B8%B0%EC%A7%80%EA%B5%AD-%EC%84%A4%EC%B9%98">velog</a>
 * @since 2024-07-30
 */
public class StationInstall {

    public static void main(String[] args) {
        System.out.println(solution(11, new int[]{4, 11}, 1));
        System.out.println(solution(16, new int[]{9}, 2));
    }

    private static int solution(int n, int[] stations, int w) {

        int count = 0;

        int idx = 1;
        int range = w * 2 + 1;

        for (int s : stations) {
            if (s - w > idx) {
                int empty = s - w - idx;
                count += (int) Math.ceil((double) empty / range);
            }
            idx = s + w + 1;
        }

        if (idx <= n) {
            int empty = n - idx + 1;
            count += (int) Math.ceil((double) empty / range);
        }

        return count;
    }
}
