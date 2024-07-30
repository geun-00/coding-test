package Programmers.level3;

import java.util.Arrays;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42884">프로그래머스 - Lv.3 : 단속카메라</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8B%A8%EC%86%8D%EC%B9%B4%EB%A9%94%EB%9D%BC">velog</a>
 * @since 2024-07-29
 */
public class SurveillanceCamera {
    public static void main(String[] args) {

        System.out.println(solution(new int[][]
                {
                        {-20, -15},
                        {-14, -5},
                        {-18, -13},
                        {-5, -3}
                }
        ));
    }

    private static int solution(int[][] routes) {

        Arrays.sort(routes, (o1, o2) -> {
            return o1[1] - o2[1];
        });


        int count = 0;
        int last = -30001;

        for (int[] r : routes) {
            if (r[0] > last) {
                count++;
                last = r[1];
            }
        }

        return count;
    }
}
