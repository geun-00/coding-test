import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        
        ArrayList<String> result = new ArrayList<>();

        for (int size : course) {

            HashMap<String, Integer> combsCount = new HashMap<>();

            for (String order : orders) {
                char[] items = order.toCharArray();
                Arrays.sort(items);
                solve(items, size, combsCount);
            }

            int max = 0;
            for (int count : combsCount.values()) {
                if (count >= 2 && count > max) {
                    max = count;
                }
            }

            for (String key : combsCount.keySet()) {
                if (combsCount.get(key) == max && max >= 2) {
                    result.add(key);
                }
            }
        }

        Collections.sort(result);

        String[] ans = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
    
    public void solve(char[] items, int size, HashMap<String, Integer> combsCount) {

        ArrayList<String> combs = new ArrayList<>();

        getCombs(items, size,0,"", combs);

        for (String comb : combs) {
            combsCount.put(comb, combsCount.getOrDefault(comb, 0) + 1);
        }
    }
    
    public void getCombs(char[] items, int size, int start, String now,  ArrayList<String> combs) {
        if (size == 0) {
            combs.add(now);
            return;
        }

        for (int i = start; i < items.length; i++) {
            getCombs(items, size - 1, i + 1, now + items[i], combs);
        }
    }
}