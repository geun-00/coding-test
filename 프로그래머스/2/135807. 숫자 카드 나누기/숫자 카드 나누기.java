class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = getGCD(arrayA);
        int gcdB = getGCD(arrayB);

        boolean a = check(gcdA, arrayB);
        boolean b = check(gcdB, arrayA);

        if (a && b) return Math.max(gcdA, gcdB);
        else if (a) return gcdA;
        else if (b) return gcdB;
        else return 0;
    }
    
    private boolean check(int num, int[] arr) {
        if (num == 1) {
            return false;
        }

        for (int n : arr) {
            if (n % num == 0) {
                return false;
            }
        }

        return true;
    }
    
    private int getGCD(int[] arr) {

        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);
        }
        return gcd;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}