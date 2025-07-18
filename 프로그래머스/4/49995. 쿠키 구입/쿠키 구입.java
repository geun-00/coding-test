class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        for (int m = 0; m < cookie.length - 1; m++) {
            int left = m;
            int right = m + 1;
            int leftSum = cookie[left];
            int rightSum = cookie[right];
            
            while (true) {
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                
                if (left > 0 && leftSum <= rightSum) {
                    left--;
                    leftSum += cookie[left];
                } else if (right < cookie.length - 1 && rightSum <= leftSum) {
                    right++;
                    rightSum += cookie[right];
                } else {
                    break;
                }
            }
        }
        
        return answer;
    }
}