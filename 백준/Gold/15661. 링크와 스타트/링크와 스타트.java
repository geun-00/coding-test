import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < (1 << n) - 1; i++) {

            int[] team = new int[n];

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    team[j] = 1;
                }
            }

            solve(team);
        }

        System.out.println(min);

    }

    private static void solve(int[] team) {

        ArrayList<Integer> team1 = new ArrayList<>();
        ArrayList<Integer> team2 = new ArrayList<>();

        for (int i = 0; i < team.length; i++) {
            if (team[i] == 0) {
                team1.add(i);
            } else {
                team2.add(i);
            }
        }

        int team1Sum = 0;

        for (int i = 0; i < team1.size(); i++) {
            for (int j = 0; j < team1.size(); j++) {
                if (i != j) {
                    team1Sum += arr[team1.get(i)][team1.get(j)];
                }
            }
        }

        int team2Sum = 0;

        for (int i = 0; i < team2.size(); i++) {
            for (int j = 0; j < team2.size(); j++) {
                if (i != j) {
                    team2Sum += arr[team2.get(i)][team2.get(j)];
                }
            }
        }

        min = Math.min(min, Math.abs(team1Sum - team2Sum));
    }
}