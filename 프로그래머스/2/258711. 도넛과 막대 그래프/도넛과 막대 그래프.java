class Solution {
    public int[] solution(int[][] edges) {
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[] in = new int[maxNode + 1];
        int[] out = new int[maxNode + 1];

        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
        }

        int[] ans = new int[4];
        for (int i = 1; i <= maxNode; i++) {
            if (in[i] == 0 && out[i] >= 2) {
                ans[0] = i;
                break;
            }
        }

        for (int i = 1; i <= maxNode; i++) {
            if (out[i] == 0 && in[i] > 0) {
                ans[2]++;
            } else if (out[i] >= 2 && in[i] >= 2) {
                ans[3]++;
            }
        }

        ans[1] = out[ans[0]] - ans[2] - ans[3];

        return ans;
    }
}