package Programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/42579">프로그래머스 - Lv.3 : 베스트앨범</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B2%A0%EC%8A%A4%ED%8A%B8%EC%95%A8%EB%B2%94">velog</a>
 *
 * @since 2024-07-31
 */
public class BestAlbum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                solution(
                        new String[]{"classic", "pop", "classic", "classic", "pop"},
                        new int[]{500, 600, 150, 800, 2500}
                )
        ));
    }

    private static int[] solution(String[] genres, int[] plays) {

        HashMap<String, Integer> total = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> songs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);

            if (songs.containsKey(genres[i])) {
                songs.get(genres[i]).put(i, plays[i]);
            } else {
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(i, plays[i]);

                songs.put(genres[i], map);
            }
        }

        ArrayList<String> list = new ArrayList<>(total.keySet());
        list.sort((o1, o2) -> total.get(o2) - total.get(o1));

        ArrayList<Integer> result = new ArrayList<>();

        for (String s : list) {
            HashMap<Integer, Integer> map = songs.get(s);

            ArrayList<Integer> list1 = new ArrayList<>(map.keySet());
            list1.sort((o1, o2) -> map.get(o2) - map.get(o1));

            result.add(list1.get(0));
            if (list1.size() > 1) {
                result.add(list1.get(1));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
