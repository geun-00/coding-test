class Solution {
    public int solution(String[] board) {
        return solve(board);
    }

    public int solve(String[] board) {
        int x = 0, o = 0, empty = 0;

        for (String s : board) {
            for (char ch : s.toCharArray()) {
                if (ch == 'X') x++;
                else if (ch == 'O') o++;
                else empty++;
            }
        }

        if (x > o || o - x > 1) return 0;

        boolean xBingo = isBingo(board, 'X');
        boolean oBingo = isBingo(board, 'O');

        if (xBingo && oBingo) return 0;
        if (xBingo && x != o) return 0;
        if (oBingo && o != x + 1) return 0;
        return 1;
    }
    
    public boolean isBingo(String[] board, char target) {
        String joined = String.join("", board);
        char[] arr = joined.toCharArray();
        int[][] index = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
        };

        for (int[] a : index) {
            if (arr[a[0]] == target && arr[a[1]] == target && arr[a[2]] == target) {
                return true;
            }
        }

        return false;
    }
}