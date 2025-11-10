import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());

            Team[] teams = new Team[n];
            for (int i = 0; i < n; i++) {
                Team team = new Team();
                team.id = i;
                team.solves = new int[k];
                teams[i] = team;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int id = Integer.parseInt(st.nextToken()) - 1;
                int number = Integer.parseInt(st.nextToken()) - 1;
                int score = Integer.parseInt(st.nextToken());

                Team team = teams[id];
                team.solves[number] = Math.max(score, team.solves[number]);
                team.count++;
                team.lastTime = i;
            }

            Arrays.sort(teams, (a, b) -> {
                int aTotal = a.getTotalScore();
                int bTotal = b.getTotalScore();

                if (aTotal != bTotal) {
                    return Integer.compare(bTotal, aTotal);
                }

                if (a.count != b.count) {
                    return Integer.compare(a.count, b.count);
                }

                return Integer.compare(a.lastTime, b.lastTime);
            });

            for (int i = 0; i < n; i++) {
                if (teams[i].id == t) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }

    static class Team {
        int[] solves;
        int id, count, lastTime;

        int getTotalScore() {
            int total = 0;
            for (int score : solves) {
                total += score;
            }
            return total;
        }
    }
}