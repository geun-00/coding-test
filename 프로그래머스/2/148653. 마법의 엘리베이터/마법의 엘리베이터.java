class Solution {
    public int solution(int storey) {
        int ans = 0;

        while (storey > 0) {
            int num = storey % 10;
            int temp = (storey / 10) % 10;

            if (num > 5 || (num == 5 && temp >= 5)) {
                ans += (10 - num);
                storey += (10 - num);
            } else {
                ans += num;
            }

            storey /= 10;
        }

        return ans;
    }
}