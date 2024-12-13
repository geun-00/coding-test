import java.util.*;

class Solution {
    
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
        
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        
        Map<String, Node> map = new HashMap<>();
        
        Node prevNode = new Node(0, 0);
        map.put(prevNode.id, prevNode);
        
        int count = 0;

        for (int d : arrows) {
            for (int i = 0; i < 2; i++) {

                int nx = prevNode.x + dx[d];
                int ny = prevNode.y + dy[d];
                String newId = nx + "," + ny;

                //처음 이동한 좌표
                if (!map.containsKey(newId)) {
                    map.put(newId, new Node(nx, ny));
                }
                //이미 한번 이동한 좌표
                else if (!prevNode.connectedNode.contains(newId)) {
                    count++;
                }

                Node nextNode = map.get(newId);

                //두 좌표 연결
                prevNode.connectedNode.add(nextNode.id);
                nextNode.connectedNode.add(prevNode.id);

                prevNode = nextNode;
            }
        }                

        return count;
    }
}