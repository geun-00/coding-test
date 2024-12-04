package Baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * <a href = "https://www.acmicpc.net/problem/16165">백준 16165번 - 해시 : 걸그룹 마스터 준석이</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-16165%EB%B2%88-%EA%B1%B8%EA%B7%B8%EB%A3%B9-%EB%A7%88%EC%8A%A4%ED%84%B0-%EC%A4%80%EC%84%9D%EC%9D%B4">velog</a>
 * @since 2024-11-30
 */
public class BJ_16165 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, String> team = new HashMap<>();
        HashMap<String, TreeSet<String>> teamMembers = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String teamName = br.readLine();
            int member = Integer.parseInt(br.readLine());

            teamMembers.put(teamName, new TreeSet<>());

            for (int j = 0; j < member; j++) {

                String name = br.readLine();

                team.put(name, teamName);
                teamMembers.get(teamName).add(name);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {

            String name = br.readLine();
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                for (String member : teamMembers.get(name)) {
                    sb.append(member).append("\n");
                }
            }
            else {
                sb.append(team.get(name)).append("\n");
            }
        }

        System.out.print(sb);
    }
}