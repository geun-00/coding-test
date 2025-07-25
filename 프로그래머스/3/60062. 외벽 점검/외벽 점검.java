class Solution {

    public int solution(int n, int[] weak, int[] dist) {
        int[] arr = new int[weak.length];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = weak[i + 1] - weak[i];
        }
        arr[arr.length - 1] = weak[0] + n - weak[weak.length - 1];

        boolean[] used = new boolean[dist.length];

        for (int i = 1; i <= dist.length; i++) {
            if (solve(i, 0, used, dist, new int[i], arr)) {
                return i;
            }
        }

        return -1;
    }

    public boolean solve(int limit, int depth, boolean[] used, int[] dist, int[] friends, int[] arr) {
        if (limit == depth) {
            return check(friends, arr);
        }

        for (int i = 0; i < dist.length; i++) {
            if (!used[i]) {
                used[i] = true;
                friends[depth] = dist[i];
                if (solve(limit, depth + 1, used, dist, friends, arr)) {
                    return true;
                }

                used[i] = false;
            }
        }

        return false;
    }

    public boolean check(int[] friends, int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int covered = 0;
            int start = i;

            for (int f : friends) {
                while (f - arr[start] >= 0) {
                    f -= arr[start++];
                    start %= length;
                    covered++;
                }

                start++;
                start %= length;
                covered++;

                if (covered >= length) return true;
            }
        }

        return false;
    }
}