package Programmers.level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/67258#qna">프로그래머스 : Lv.3 : 보석 쇼핑</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B3%B4%EC%84%9D-%EC%87%BC%ED%95%91">velog</a>
 * @since 2024-08-01
 */
public class JewelShopping {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"})));
        System.out.println(Arrays.toString(solution(new String[]{"AA", "AB", "AC", "AA", "AC"})));
        System.out.println(Arrays.toString(solution(new String[]{"XYZ", "XYZ", "XYZ"})));
        System.out.println(Arrays.toString(solution(new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"})));
        System.out.println(Arrays.toString(solution(new String[]{"DIA", "EM", "EM", "RUB", "DIA"})));
    }

    private static int[] solution(String[] gems) {

        HashSet<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }

        int s = 0;
        int e = 0;
        int dist = Integer.MAX_VALUE;
        int[] result = new int[2];

        HashMap<String, Integer> map = new HashMap<>();

        while (e < gems.length) {

            map.put(gems[e], map.getOrDefault(gems[e], 0) + 1);
            e++;

            while (set.size() == map.size()) {
                if (e - s < dist) {
                    dist = e - s;
                    result[0] = s + 1;
                    result[1] = e;
                }
                map.put(gems[s], map.get(gems[s]) - 1);

                if (map.get(gems[s]) == 0) {
                    map.remove(gems[s]);
                }
                s++;
            }
        }

        return result;
    }
}
