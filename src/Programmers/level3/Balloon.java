package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/68646">프로그래머스 - Lv.3 : 풍선 터트리기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%92%8D%EC%84%A0-%ED%84%B0%ED%8A%B8%EB%A6%AC%EA%B8%B0">velog</a>
 *
 * @since 2024-08-13
 */
public class Balloon {
    public static void main(String[] args) {

        System.out.println(solution(new int[]{9, -1, -5}));
        System.out.println(solution(new int[]{-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}));
    }

    private static int solution(int[] a) {

        int n = a.length;
        if (n == 1) {
            return 1;
        }

        int[] left = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
        }

        int[] right = new int[n];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i > 0; i--) {
            right[i] = Math.min(right[i + 1], a[i]);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] > left[i] && a[i] > right[i]) {
                continue;
            }
            result++;
        }

        return result;
    }
}
