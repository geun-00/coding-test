import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
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