import java.util.*;

class Solution {
    int R = 50, C = 50;
    int[][] parent = new int[R][C];
    String[][] excel = new String[R][C];

    public String[] solution(String[] commands) {

        for (String[] row : excel) {
            Arrays.fill(row, "EMPTY");
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                parent[r][c] = r * C + c;
            }
        }

        List<String> ans = new ArrayList<>();

        for (String com : commands) {
            String[] arr = com.split(" ");
            switch (arr[0]) {
                case "UPDATE":
                    update(arr);
                    break;
                case "MERGE":
                    merge(arr);
                    break;
                case "UNMERGE":
                    unmerge(arr);
                    break;
                case "PRINT":
                    print(arr, ans);
                    break;
            }
        }

        return ans.toArray(String[]::new);
    }

    public void update(String[] arr) {
        if (arr.length == 4) {
            int r = Integer.parseInt(arr[1]) - 1;
            int c = Integer.parseInt(arr[2]) - 1;
            excel[parent[r][c] / C][parent[r][c] % C] = arr[3];
        }
        else {
            String value1 = arr[1];
            String value2 = arr[2];

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    int p = find(r, c);

                    int pr = p / C, pc = p % C;

                    if (!excel[pr][pc].equals("EMPTY") && value1.equals(excel[pr][pc])) {
                        excel[pr][pc] = value2;
                    }
                }
            }
        }
    }

    public void merge(String[] arr) {
        int r1 = Integer.parseInt(arr[1]) - 1;
        int c1 = Integer.parseInt(arr[2]) - 1;
        int r2 = Integer.parseInt(arr[3]) - 1;
        int c2 = Integer.parseInt(arr[4]) - 1;

        union(r1, c1, r2, c2);
    }

    private void unmerge(String[] arr) {
        int r = Integer.parseInt(arr[1]) - 1;
        int c = Integer.parseInt(arr[2]) - 1;

        int root = find(r, c);
        String value = excel[root / C][root % C];

        // for (int i = 0; i < R; i++) {
        //     for (int j = 0; j < C; j++) {
        //         find(i, j);
        //     }
        // }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (parent[i][j] == root) {
                    parent[i][j] = i * C + j;
                    excel[i][j] = "EMPTY";
                }
            }
        }

        excel[r][c] = value;
    }

    private void print(String[] arr, List<String> ans) {
        int r = Integer.parseInt(arr[1]) - 1;
        int c = Integer.parseInt(arr[2]) - 1;
        int p = find(r, c);
        ans.add(excel[p / C][p % C]);
    }

    public void union(int r1, int c1, int r2, int c2) {
        int a = find(r1, c1);
        int b = find(r2, c2);

        if (a == b) return;

        int ar = a / C, ac = a % C;
        int br = b / C, bc = b % C;

        // 어떤 값을 사용할지 결정
        String valA = excel[ar][ac];
        String valB = excel[br][bc];
        String newValue = !"EMPTY".equals(valA) ? valA : valB;

        // 병합
        parent[br][bc] = a;

        // 모든 셀의 parent 갱신
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (find(r, c) == b) {
                    parent[r][c] = a;
                }
            }
        }

        // 병합 후 루트에 값 저장
        excel[ar][ac] = newValue;
    }

    public int find(int r, int c) {
        if (parent[r][c] == r * C + c) {
            return r * C + c;
        }

        return parent[r][c] = find(parent[r][c] / C, parent[r][c] % C);
    }
}