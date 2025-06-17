class Solution {
    public int solution(int[] ingredient) {
        int[] stack = new int[ingredient.length];
        int top = 0;
        int ans = 0;
        
        for (int n : ingredient) {
            stack[top++] = n;
            
            if (top >= 4) {
                if (stack[top - 4] == 1 &&
                   stack[top - 3] == 2 &&
                   stack[top - 2] == 3 &&
                   stack[top - 1] == 1) {
                    
                    ans++;
                    top -= 4;
                }
            }
        }
        
        return ans;
    }
}