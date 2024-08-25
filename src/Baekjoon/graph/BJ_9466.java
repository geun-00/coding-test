package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/9466">백준 9466번 - 그래프 탐색 : 텀 프로젝트</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-9466%EB%B2%88-%ED%85%80-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8">velog</a>
 *
 * @since 2024-08-24
 */
public class BJ_9466 {

    static int[] pick;
    static boolean[] team, visit;
    static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {

            int n = Integer.parseInt(br.readLine());

            count = 0;

            pick = new int[n + 1];
            team = new boolean[n + 1];
            visit = new boolean[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                pick[i] = Integer.parseInt(st.nextToken());

                //1인 팀인 경우
                if (pick[i] == i) {
                    team[i] = true;
                    count++;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!team[i]) {
                    dfs(i);
                }
            }

            sb.append(n - count).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int num) {

        //내가 선택한 학생이 팀을 이루지 않았을 때만 더 탐색한다.
        if (team[num]) {
            return;
        }

        //사이클 발생, 해당 번호는 팀이 완성되었고 팀에 속하는 학생 수를 증가시킨다.
        if (visit[num]) {
            team[num] = true;
            count++;
        }
        //사이클 발생 여부를 확인하기 위해 방문 표시
        else {
            visit[num] = true;
        }

        dfs(pick[num]);

        //다음 학생 확인을 위한 방문 초기화
        visit[num] = false;

        //dfs를 거치면서 확인해 본 학생은 팀에 속할 수도 있고, 아닐 수도 있다.
        //팀에 속하는 경우는 위에서 확인하였다.
        //즉 현재 학생이 팀에 속하든 속하지 않든 확인은 한 것이므로 중복 탐색을 방지하기 위함이다.
        team[num] = true;
    }
}
