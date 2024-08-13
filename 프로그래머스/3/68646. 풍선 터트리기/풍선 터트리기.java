class Solution {
    public int solution(int[] a) {
        
        int n = a.length;
        if (n == 1) {
            return 1;
        }

        int[] left = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
        }

        int[] right = new int[n];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i > 0; i--) {
            right[i] = Math.min(right[i + 1], a[i]);
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] > left[i] && a[i] > right[i]) {
                continue;
            }
            result++;
        }

        return result;
    }
}