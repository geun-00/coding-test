import java.util.*;

class Solution {
    
    HashMap<String, PriorityQueue<String>> graph;
    Deque<String> stack;
    
    public String[] solution(String[][] tickets) {
        
        graph = new HashMap<>();
        stack = new LinkedList<>();
        
        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).offer(ticket[1]);
        }

        dfs("ICN");
        
        return stack.toArray(new String[0]);
    }
    
    public void dfs(String now) {
        while (graph.containsKey(now) && !graph.get(now).isEmpty()) {
            dfs(graph.get(now).poll());
        }
        stack.push(now);
    }
}