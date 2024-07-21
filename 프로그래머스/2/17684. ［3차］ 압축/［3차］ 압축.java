import java.util.*;

class Solution {
    public int[] solution(String msg) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        int idx;
        
        for(idx = 1; idx <= 26; idx++){
            map.put(String.valueOf((char)('A' + idx - 1)), idx);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        String w = "";
        
        for(char c : msg.toCharArray()){
            String wc = w + c;
            
            if (map.containsKey(wc)){
                w = wc;
            } else { 
               list.add(map.get(w));
               map.put(wc, idx++);
               w = String.valueOf(c);
            }
        }
        
        if(!w.isEmpty()){
            list.add(map.get(w));
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}