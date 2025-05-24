class Solution {
    public int[] solution(int[] num_list, int n) {
        int len = num_list.length;
        int[] ans = new int[(len + n - 1) / n];
        
        for (int i = 0, j = 0; i < len; i += n, j++) {
            ans[j] = num_list[i];
        }
        
        return ans;
    }
}