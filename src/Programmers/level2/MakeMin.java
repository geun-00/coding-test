package Programmers.level2;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/12941">프로그래머스 - Lv.2 : 최솟값 만들기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B5%9C%EC%86%9F%EA%B0%92-%EB%A7%8C%EB%93%A4%EA%B8%B0">velog</a>
 * @since 2024-06-17
 */
public class MakeMin {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 4, 2}, new int[]{5, 4, 4}));
        System.out.println(solution(new int[]{1, 2}, new int[]{3, 4}));
    }

    private static int solution(int[] A, int[] B) {
        int result = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < A.length; i++) {
            result += A[i] * B[B.length - 1 - i];
        }

        return result;
    }
}
