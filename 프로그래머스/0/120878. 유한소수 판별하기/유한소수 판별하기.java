class Solution {
    public int solution(int a, int b) {
        b /= getGCD(a, b);
        
        while (b % 2 == 0) b /= 2;
        while (b % 5 == 0) b /= 5;
        
        return (b == 1) ? 1 : 2;
    }
    
    public int getGCD(int a, int b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}