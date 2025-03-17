class Solution {
    
    int[] rate;
    int plus = -1, sales = -1;
    
    public int[] solution(int[][] users, int[] emoticons) {
        rate = new int[emoticons.length];
        solve(0, users, emoticons);
        return new int[]{plus, sales};
    }
    
    public void solve(int depth, int[][] users, int[] emoticons) {
        if (depth == rate.length) {

            int join = 0, count = 0;

            for (int[] user : users) {
                int sum = 0;

                for (int i = 0; i < rate.length; i++) {
                    if (rate[i] >= user[0]) {
                        sum += emoticons[i] * (100 - rate[i]) / 100;
                    }
                }
                if (sum >= user[1]) {
                    join++;
                } else {
                    count += sum;
                }
            }

            if (plus == join) {
                sales = Math.max(sales, count);
            } else if (plus < join) {
                plus = join;
                sales = count;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            rate[depth] = (i + 1) * 10;
            solve(depth + 1, users, emoticons);
        }
    }
}