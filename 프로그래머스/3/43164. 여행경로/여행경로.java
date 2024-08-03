import java.util.*;

class Solution {
    
    boolean[] visit;
    ArrayList<String> routes;
    
    public String[] solution(String[][] tickets) {
        
        visit = new boolean[tickets.length];
        routes = new ArrayList<>();

        dfs("ICN", "ICN", 0, tickets);

        routes.sort(null);

        return routes.get(0).split(" ");
    }
    
     public void dfs(String now, String path, int depth, String[][] tickets) {
        if (depth == tickets.length) {
            routes.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visit[i] && tickets[i][0].equals(now)) {
                visit[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth + 1, tickets);
                visit[i] = false;
            }
        }
    }
}