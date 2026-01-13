import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> players = new HashMap<>();
        for (String person : participant) {
            players.put(person, players.getOrDefault(person, 0) + 1);
        }
        
        for (String person : completion) {
            players.put(person, players.get(person) - 1);
            
            if (players.get(person) == 0) {
                players.remove(person);
            }
        }
        
        return players.keySet().iterator().next();
    }
}