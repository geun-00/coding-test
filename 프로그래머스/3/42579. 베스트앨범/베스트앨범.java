import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
         HashMap<String, Integer> total = new HashMap<>();
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