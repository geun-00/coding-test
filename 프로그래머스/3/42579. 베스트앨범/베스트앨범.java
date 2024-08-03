import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        // 노래 장르별 재생된 횟수의 총합
        HashMap<String, Integer> total = new HashMap<>();
        // 각 장르의 노래 고유 번호와 재생 횟수
        HashMap<String, HashMap<Integer, Integer>> songs = new HashMap<>();

        for (int i = 0; i < plays.length; i++) {
            
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, plays[i]);
            
            if (songs.containsKey(genres[i])) {
                songs.get(genres[i]).put(i, plays[i]);
            } else {
                songs.put(genres[i], map);
            }
        }

        // 장르별 재생된 횟수 총합을 기준으로 정렬
        ArrayList<String> list = new ArrayList<>(total.keySet());
        list.sort((o1, o2) -> total.get(o2) - total.get(o1));

        ArrayList<Integer> result = new ArrayList<>();

        for (String s : list) {
            HashMap<Integer, Integer> map = songs.get(s);

            // 내림차순 정렬한 고유 번호를 추출
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