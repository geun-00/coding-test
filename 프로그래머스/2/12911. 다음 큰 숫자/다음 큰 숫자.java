class Solution {
    public int solution(int n) {
        int count = n;

        while (true) {
            count++;
            if (Integer.bitCount(count) == Integer.bitCount(n)) {
                return count;
            }
        }
    }
}