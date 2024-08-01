import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        HashSet<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }

        int s = 0;
        int e = 0;
        int dist = Integer.MAX_VALUE;
        int[] result = new int[2];

        HashMap<String, Integer> map = new HashMap<>();

        while (e != gems.length) {

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