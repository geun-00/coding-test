class Solution {
    public int solution(int[] arr) {
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {

            result = result * arr[i] / gcd(result, arr[i]);
        }

        return result;
    }
    
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}