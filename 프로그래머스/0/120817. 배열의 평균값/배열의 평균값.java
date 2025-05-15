class Solution {
    public double solution(int[] numbers) {
        int sum = 0;
        for (int num : numbers) sum += num;
        
        return (sum * 1.0) / numbers.length;
    }
}