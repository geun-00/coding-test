class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;

        long sum1 = 0, sum2 = 0;
        int[] arr = new int[n * 2];

        for (int i = 0; i < n; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];

            arr[i] = queue1[i];
            arr[i + n] = queue2[i];
        }

        long total = sum1 + sum2;
        
        if (total % 2 != 0) return -1;

        long target = total / 2;

        int i = 0, j = n;
        long current = sum1;
        int ans = 0;

        while (i < n * 2 && j < n * 2) {
            if (current == target) {
                return ans;
            }

            if (current < target) {
                current += arr[j++];
            } else {
                current -= arr[i++];
            }

            ans++;
        }

        return -1;
    }
}