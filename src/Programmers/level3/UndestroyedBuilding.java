package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/92344">프로그래머스 - Lv.3 : 파괴되지 않은 빌딩</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%8C%8C%EA%B4%B4%EB%90%98%EC%A7%80-%EC%95%8A%EC%9D%80-%EB%B9%8C%EB%94%A9">velog</a>
 *
 * @since 2024-08-16
 */
public class UndestroyedBuilding {
    public static void main(String[] args) {

        System.out.println(solution(
                new int[][]{
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5},
                        {5, 5, 5, 5, 5}
                }, new int[][]{
                        {1, 0, 0, 3, 4, 4},
                        {1, 2, 0, 2, 3, 2},
                        {2, 1, 0, 3, 1, 2},
                        {1, 0, 1, 3, 3, 1},
                }));
        System.out.println(solution(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }, new int[][]{
                        {1, 1, 1, 2, 2, 4},
                        {1, 0, 0, 1, 1, 2},
                        {2, 2, 0, 2, 0, 100}
                }));
    }

    private static int solution(int[][] board, int[][] skill) {

        int n = board.length;
        int m = board[0].length;

        int[][] arr = new int[n + 1][m + 1];

        for (int[] s : skill) {

            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = s[5] * ((type == 1) ? -1 : 1);

            arr[x1][y1] += degree;
            arr[x1][y2 + 1] += degree * -1;
            arr[x2 + 1][y1] += degree * -1;
            arr[x2 + 1][y2 + 1] += degree;

        }

        for (int i = 1; i < m; i++) {
            arr[0][i] += arr[0][i - 1];
        }

        for (int i = 1; i < n; i++) {
            arr[i][0] += arr[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + arr[i][j];
            }
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + arr[i][j] > 0) {
                    result++;
                }
            }
        }

        return result;
    }
}
