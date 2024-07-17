package Programmers.level2;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/43165">프로그래머스 - Lv.2 : 타겟 넘버</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%83%80%EA%B2%9F-%EB%84%98%EB%B2%84">velog</a>
 * @since 2024-07-16
 */
public class TargetNum {

    static int count = 0;
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1, 1}, 3));
        count = 0;
        System.out.println(solution(new int[]{4, 1, 2, 1}, 4));
    }

    private static int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);

        return count;
    }

    private static void dfs(int depth, int[] numbers, int target, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                count++;
            }
            return;
        }

        dfs(depth + 1, numbers, target, sum + numbers[depth]);
        dfs(depth + 1, numbers, target, sum - numbers[depth]);
    }
}
