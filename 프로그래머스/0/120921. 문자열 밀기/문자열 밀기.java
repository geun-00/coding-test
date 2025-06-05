class Solution {
    public int solution(String A, String B) {
        int count = 0;
        int len = A.length();
        
        while (count < A.length()) {
            if (A.equals(B)) {
                return count;
            }
            count++;
            A = A.substring(len - 1) + A.substring(0, len -1);
        }
        
        return -1;
    }
}