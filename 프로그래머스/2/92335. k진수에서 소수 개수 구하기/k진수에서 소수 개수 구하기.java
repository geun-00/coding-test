class Solution {
    public int solution(int n, int k) {
        
        String s = Integer.toString(n, k);
        String[] split = s.split("0");

        int count = 0;

        for (String str : split) {
            if (str.isEmpty()) {
                continue;
            }

            if (isPrime(Long.parseLong(str))) {
                count++;
            }
        }

        return count;
    }
    
    public boolean isPrime(long n) {

        if (n == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}