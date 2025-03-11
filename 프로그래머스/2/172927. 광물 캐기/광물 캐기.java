class Solution {
    int ans = Integer.MAX_VALUE;
    
    public int solution(int[] picks, String[] minerals) {
        solve(0, picks[0], picks[1], picks[2], minerals, 0);
        return ans;
    }
    
    public void solve(int pos, int dia, int iron, int stone, String[] minerals, int sum) {

        if ((pos >= minerals.length) || (dia == 0 && iron == 0 && stone == 0)) {
            ans = Math.min(ans, sum);
            return;
        }

        int len = Math.min(minerals.length, pos + 5);

        if (dia > 0) {
            solve(len, dia - 1, iron, stone, minerals, sum + (len - pos));
        }

        if (iron > 0) {
            int add = 0;
            for (int i = pos; i < len; i++) {
                if ("diamond".equals(minerals[i])) {
                    add += 5;
                } else {
                    add += 1;
                }
            }
            solve(len, dia, iron - 1, stone, minerals, sum + add);
        }

        if (stone > 0) {
            int add = 0;
            for (int i = pos; i < len; i++) {
                if ("diamond".equals(minerals[i])) {
                    add += 25;
                } else if ("iron".equals(minerals[i])) {
                    add += 5;
                } else {
                    add += 1;
                }
            }
            solve(len, dia, iron, stone - 1, minerals, sum + add);
        }
    }
}