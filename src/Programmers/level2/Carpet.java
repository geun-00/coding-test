package Programmers.level2;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42842">프로그래머스 - Lv.2 : 카펫</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B9%B4%ED%8E%AB">velog</a>
 * @since 2024-06-23
 */
public class Carpet {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10, 2)));
        System.out.println(Arrays.toString(solution(8, 1)));
        System.out.println(Arrays.toString(solution(24, 24)));
    }

    private static int[] solution(int brown, int yellow) {

        int total = brown + yellow; //전체 넓이

        for (int height = 3; height <= Math.sqrt(total); height++) { //높이(세로)
            if (total % height == 0) {
                int width = total / height; //너비(가로)

                if ((height - 2) * (width - 2) == yellow) { //노란색 격자의 수 조건 확인
                    return new int[]{width, height};
                }
            }
        }

/*
        int half = (brown + 4) / 2;

        for (int height = 3; height < half; height++) {
            int width = half - height;

            if (width * height == brown + yellow) {
                return new int[]{width, height};
            }
        }
*/

/*
        int half = (brown + 4) / 2;

        int low = 3;
        int high = half / 2;

        while (low <= high) {
            int height = (low + high) / 2;

            int width = half - height;

            if (width * height < brown + yellow) {
                low = height + 1;
            } else if (width * height > brown + yellow) {
                high = height - 1;
            } else {
                return new int[]{width, height};
            }
        }
*/

        return null;
    }
}
