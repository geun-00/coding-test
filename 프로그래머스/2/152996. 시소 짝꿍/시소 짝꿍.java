class Solution {
    public long solution(int[] weights) {
        long[] arr = new long[2001];

        for (int w : weights) {
            arr[w]++;
        }

        long ans = 0;

        for (int i = 100; i <= 1000; i++) {
            long cur = arr[i];

            if (cur > 0) {

                //nC2
                if (cur > 1) ans += cur * (cur - 1) / 2;

                if (i * 3 % 2 == 0) ans += cur * arr[i * 3 / 2];
                if (i * 4 % 3 == 0) ans += cur * arr[i * 4 / 3];
                if (i * 4 % 2 == 0) ans += cur * arr[i * 4 / 2];
            }
        }

        return ans;
    }
}