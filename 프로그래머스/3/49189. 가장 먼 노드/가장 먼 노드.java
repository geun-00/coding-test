import java.util.*;

class Solution {
    
    int[] level;
    ArrayList<Integer>[] graph;
    boolean[] visit;
    
    public int solution(int n, int[][] edge) {
        
        graph = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        level = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            int n1 = edge[i][0];
            int n2 = edge[i][1];

            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        
        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(1, 0));
        visit[1] = true;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            level[now.num] = now.depth;

            for (int next : graph[now.num]) {
                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(new Node(next, now.depth + 1));
                }
            }
        }

        int maxLevel = Arrays.stream(level).max().getAsInt();

        return (int) Arrays.stream(level).filter(d -> d == maxLevel).count();
    }
}

class Node {
    int num, depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}