import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();

        for (String r : record) {
            String[] arr = r.split(" ");

            String command = arr[0];
            if (command.equals("Enter") || command.equals("Change")) {
                map.put(arr[1], arr[2]);
            }
        }

        List<String> ans = new ArrayList<>();

        for (String r : record) {
            String[] arr = r.split(" ");

            String command = arr[0];
            if (command.equals("Enter")) {
                ans.add(map.get(arr[1]) + "님이 들어왔습니다.");
            } else if (command.equals("Leave")) {
                ans.add(map.get(arr[1]) + "님이 나갔습니다.");
            }
        }

        
        return ans.toArray(String[]::new);
    }
}