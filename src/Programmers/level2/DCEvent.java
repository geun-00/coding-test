package Programmers.level2;

import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/131127">프로그래머스 - Lv.2 : 할인 행사</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%95%A0%EC%9D%B8-%ED%96%89%EC%82%AC">velog</a>
 * @since 2024-07-01
 */
public class DCEvent {

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}));

        System.out.println(solution(new String[]{"apple"},
                new int[]{10},
                new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}));
    }

    private static int solution(String[] want, int[] number, String[] discount) {

        int count = 0;

        for (int i = 0; i <= discount.length - 10; i++) {

            HashMap<String, Integer> map = new HashMap<>();

            for (int j = i; j < i + 10; j++) {
                map.put(discount[j], map.getOrDefault(discount[j], 0) + 1);
            }

            if (check(want, number, map)) {
                count++;
            }
        }

        return count;
    }

    private static boolean check(String[] want, int[] number, HashMap<String, Integer> map) {

        for (int i = 0; i < want.length; i++) {
            int num = number[i];

            if (map.getOrDefault(want[i], 0) != num) {
                return false;
            }
        }

        return true;
    }
}
