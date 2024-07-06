package Programmers.level2;

import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42578">프로그래머스 - Lv.2 : 의상</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9D%98%EC%83%81">velog</a>
 * @since 2024-07-05
 */

public class Clothes {
    public static void main(String[] args) {
        System.out.println(solution(new String[][]
                        {
                                {"yellow_hat", "headgear"},
                                {"blue_sunglasses", "eyewear"},
                                {"green_turban", "headgear"}
                        }
                )
        );

        System.out.println(solution(new String[][]
                        {
                                {"yellow_hat", "face"},
                                {"blue_sunglasses", "face"},
                                {"green_turban", "face"}
                        }
                )
        );
    }

    private static int solution(String[][] clothes) {

        HashMap<String, Integer> map = new HashMap<>();

        for (String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }

        int result = 1;

        for (int value : map.values()) {
            result *= value + 1;
        }

        return result - 1;
    }
}
