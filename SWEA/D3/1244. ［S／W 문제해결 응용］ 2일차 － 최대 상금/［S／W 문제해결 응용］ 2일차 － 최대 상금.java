import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int res = -1;

            HashSet<State> visit = new HashSet<>();
            Queue<State> qu = new ArrayDeque<>();
            qu.offer(new State(String.valueOf(n), 0));

            while (!qu.isEmpty()) {

                State s = qu.poll();

                if (s.cnt == c) {
                    res = Math.max(res, Integer.parseInt(s.str));
                    continue;
                }

                char[] arr = s.str.toCharArray();

                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j < arr.length; j++) {
                        char[] clone = arr.clone();
                        char temp = clone[i];
                        clone[i] = clone[j];
                        clone[j] = temp;

                        String newStr = new String(clone);
                        if (visit.add(new State(newStr, s.cnt + 1))) {
                            qu.offer(new State(newStr, s.cnt + 1));
                        }
                    }

                }
            }

            System.out.println("#" + tc + " " + res);
        }
	}
}

class State {
    String str;
    int cnt;

    public State(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return cnt == state.cnt && Objects.equals(str, state.str);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, cnt);
    }
}