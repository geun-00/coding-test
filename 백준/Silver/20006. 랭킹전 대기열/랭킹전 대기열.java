import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Player>[] games = new List[p];

        for (int i = 0; i < p; i++) {
            games[i] = new ArrayList<>();
        }

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            Player player = new Player(level, nickname);

            for (List<Player> game : games) {
                if (game.isEmpty()) {
                    game.add(player);
                    break;
                }

                if (game.size() < m && Math.abs(game.get(0).level - level) <= 10) {
                    game.add(player);
                    break;
                }
            }
        }

        for (List<Player> game : games) {
            game.sort(Comparator.comparing(a -> a.nickname));
        }

        StringBuilder sb = new StringBuilder();
        for (List<Player> game : games) {
            if (game.isEmpty()) {
                break;
            }
            if (game.size() == m) {
                sb.append("Started!");
            } else {
                sb.append("Waiting!");
            }

            sb.append("\n");

            for (Player player : game) {
                sb.append(player.level).append(" ").append(player.nickname).append("\n");
            }
        }

        System.out.print(sb);
    }

    static class Player {
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }
    }
}