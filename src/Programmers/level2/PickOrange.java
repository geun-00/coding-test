package Programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/138476">프로그래머스 - Lv.2 : 귤 고르기</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B7%A4-%EA%B3%A0%EB%A5%B4%EA%B8%B0">velog</a>
 * @since 2024-06-27
 */
public class PickOrange {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3}));
        System.out.println(solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3}));
    }

    private static int solution(int k, int[] tangerine) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>(map.values());

        list.sort(Collections.reverseOrder());

        int count = 0;

        for (int i = 0; i < list.size(); i++) {
            count += list.get(i);
            if (count >= k) {
                return i + 1;
            }
        }

        return 0;
    }
}
