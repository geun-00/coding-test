package Programmers.level3;

/**
 * <a href = "https://school.programmers.co.kr/learn/courses/30/lessons/49191">프로그래머스 - Lv.3 : 순위</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%88%9C%EC%9C%84">velog</a>
 * @since 2024-08-12
 */
public class Rank {

    public static void main(String[] args) {

        System.out.println(solution(5, new int[][]{
                {4, 3},
                {4, 2},
                {3, 2},
                {1, 2},
                {2, 5}
        }));
    }

    private static int solution(int n, int[][] results) {

        int[][] rank = new int[n + 1][n + 1];

        for (int[] result : results) {
            int a = result[0];
            int b = result[1];
            rank[a][b] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int s = 1; s <= n; s++) {
                for (int e = 1; e <= n; e++) {
                    //이행 관계 정의
                    if (rank[s][k] == 1 && rank[k][e] == 1) {
                        rank[s][e] = 1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                //내가 상대를 이기던, 상대가 나를 이기던 승패를 따질 수 있을 때
                if (rank[i][j] == 1 || rank[j][i] == 1) {
                    count++;
                }
            }
            //자기 자신을 제외하고 모든 선수와 승패를 따질 수 있다.
            if (count == n - 1) {
                result++;
            }
        }

        return result;
    }
}
