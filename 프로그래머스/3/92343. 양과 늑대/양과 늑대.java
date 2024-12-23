import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        
        int n = info.length;

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            tree[u].add(v);
        }

        Set<Integer> nodes = new HashSet<>();
        nodes.add(0);

        return dfs(nodes, tree, info, 0, 0);
    }
    
    public int dfs(Set<Integer> nodes, List<Integer>[] tree, int[] info, int sheep, int wolf) {

        int max = sheep;

        for (int node : nodes) {

            int nextSheep = sheep;
            int nextWolf = wolf;

            if (info[node] == 0) {
                nextSheep++;
            } else {
                nextWolf++;
            }

            if(nextSheep <= nextWolf) continue;

            Set<Integer> nextNodes = getNextNodes(nodes, node, tree);
            
            max = Math.max(max, dfs(nextNodes, tree, info, nextSheep, nextWolf));
        }

        return max;
    }

    public Set<Integer> getNextNodes(Set<Integer> nodes, int node, List<Integer>[] tree) {

        Set<Integer> nextNodes = new HashSet<>(nodes);
        nextNodes.remove(node); //부모 노드는 제거

        for (int child : tree[node]) {
            nextNodes.add(child);
        }

        return nextNodes;
    }
}