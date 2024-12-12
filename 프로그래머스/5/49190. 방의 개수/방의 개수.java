import java.util.*;

class Solution {
    
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    static class Node {
        
        int x, y;
        String id;
        Set<String> connectedNode;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.id = x + "," + y;
            this.connectedNode = new HashSet<>();
        }
    }
    
    public int solution(int[] arrows) {
        int count = 0;

        Map<String, Node> map = new HashMap<>();
        Node v = new Node(0, 0);
        map.put(v.id, v);

        for (int d : arrows) {
            for (int i = 0; i < 2; i++) {

                int x = v.x + dx[d];
                int y = v.y + dy[d];
                String id = x + "," + y;

                if (!map.containsKey(id)) {
                    map.put(id, new Node(x, y));
                } else if (!v.connectedNode.contains(id)) {
                    count++;
                }

                Node u = map.get(id);
                v.connectedNode.add(u.id);
                u.connectedNode.add(v.id);
                v = map.get(id);
            }
        }

        return count;
    }
}