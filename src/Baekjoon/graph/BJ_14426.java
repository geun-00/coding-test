package Baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href = "https://www.acmicpc.net/problem/14226">백준 14426번 - 그래프 탐색 : 이모티콘</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-14426%EB%B2%88-%EC%9D%B4%EB%AA%A8%ED%8B%B0%EC%BD%98">velog</a>
 * @since 2024-12-22
 */
public class BJ_14426 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        boolean[][] visit = new boolean[s + 1][s + 1]; //[화면][클립보드]
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

            //1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if (!visit[emoticon][emoticon]) {
                visit[emoticon][emoticon] = true;
                qu.offer(new State(emoticon, emoticon, time + 1));
            }

            //2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            int nextEmoticon = emoticon + clipboard;

            if (clipboard > 0 && nextEmoticon <= s && !visit[nextEmoticon][clipboard]) {
                visit[nextEmoticon][clipboard] = true;
                qu.offer(new State(nextEmoticon, clipboard, time + 1));
            }

            //3. 화면에 있는 이모티콘 중 하나를 삭제한다.
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