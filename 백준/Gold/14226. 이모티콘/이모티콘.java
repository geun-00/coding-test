import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        boolean[][] visit = new boolean[s + 1][s + 1]; //[이모티콘][클립보드]
        visit[1][0] = true;

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(1, 0, 0));

        while (!qu.isEmpty()) {

            State state = qu.poll();

            int emoticon = state.emoticon;
            int clipboard = state.clipboard;
            int time = state.time;

            if (emoticon == s) {
                System.out.println(time);
                return;
            }

            if (!visit[emoticon][emoticon]) {
                visit[emoticon][emoticon] = true;
                qu.offer(new State(emoticon, emoticon, time + 1));
            }

            int nextEmoticon = emoticon + clipboard;

            if (clipboard > 0 && nextEmoticon <= s && !visit[nextEmoticon][clipboard]) {
                visit[nextEmoticon][clipboard] = true;
                qu.offer(new State(nextEmoticon, clipboard, time + 1));
            }

            nextEmoticon = emoticon - 1;

            if (nextEmoticon > 0 && !visit[nextEmoticon][clipboard]) {
                visit[nextEmoticon][clipboard] = true;
                qu.offer(new State(nextEmoticon, clipboard, time + 1));
            }
        }

    }

    static class State {
        int emoticon;
        int clipboard;
        int time;

        public State(int emoticon, int clipboard, int time) {
            this.emoticon = emoticon;
            this.clipboard = clipboard;
            this.time = time;
        }
    }
}