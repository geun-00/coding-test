package Programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/17684">프로그래머스 - Lv.2 : [3차] 압축</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-3%EC%B0%A8-%EC%95%95%EC%B6%95">velog</a>
 * @since 2024-07-20
 */
public class Compaction {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution("KAKAO")));
        System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(solution("ABABABABABABABAB")));

    }

    private static int[] solution(String msg) {

        HashMap<String, Integer> map = new HashMap<>();

        int idx;

        for (idx = 1; idx <= 26; idx++) {
            map.put(String.valueOf((char) ('A' + idx - 1)), idx);
        }

        ArrayList<Integer> list = new ArrayList<>();

        String w = "";
        for (char c : msg.toCharArray()) {
            String wc = w + c;

            if (map.containsKey(wc)) {
                w = wc;
            } else {
                list.add(map.get(w));
                map.put(wc, idx++);
                w = String.valueOf(c);
            }
        }

        //마지막 글자 처리
        if (!w.isEmpty()) {
            list.add(map.get(w));
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
