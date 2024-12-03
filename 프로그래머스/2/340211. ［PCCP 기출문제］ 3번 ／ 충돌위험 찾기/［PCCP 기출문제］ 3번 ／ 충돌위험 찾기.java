import java.util.*;

class Solution {
    
    static final int R = 100;
    static final int C = 100;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] points, int[][] routes) {
        
        int n = points.length;
        int m = routes.length;
        
        int[] point = new int[n];
        
        for(int i = 0; i < n; i++) {
            int r = points[i][0] - 1;
            int c = points[i][1] - 1;
            
            point[i] = r * C + c;
        }
        
        Deque<Integer>[] route = new ArrayDeque[m];
        
        for(int i = 0; i < m; i++) {

            route[i] = new ArrayDeque<>();

            for(int j = 0; j < routes[i].length - 1; j++) {
                int start = routes[i][j] - 1;
                int target = routes[i][j + 1] - 1;

                bfs(point[start], point[target], route[i]);
            }
        }

        boolean flag = true;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (flag) {

            flag = false;
            map.clear();

            for (Queue<Integer> qu : route) {
                if (!qu.isEmpty()) {
                    flag = true;
                    int pos = qu.poll();
                    map.put(pos, map.getOrDefault(pos, 0) + 1);
                }
            }

            for (int count : map.values()) {
                if (count > 1) {
                    ans++;
                }
            }
        }

        return ans;
    }
    
    public static void bfs(int start_idx, int target_idx, Deque<Integer> route) {

        int sx = start_idx / C;
        int sy = start_idx % C;

        boolean[] visit = new boolean[R * C];
        visit[start_idx] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start_idx);

        int[] prev = new int[R * C];

        while(!qu.isEmpty()) {

            int now = qu.poll();
            int x = now / C;
            int y = now % C;

            if(now == target_idx) break;

            for(int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * C + ny;

                if(nx < 0|| ny < 0 || nx >= R || ny >= C || visit[next]) continue;

                visit[next] = true;
                prev[next] = i;
                qu.offer(nx * C + ny);
            }
        }

        Deque<Integer> stk = new ArrayDeque<>();

        int x = target_idx / C;
        int y = target_idx % C;
        int idx = target_idx;

        while(!(x == sx && y == sy)) {
            stk.push(x * C + y);
            int dir = prev[idx];
            x -= dx[dir];
            y -= dy[dir];
            idx = x * C + y;
        }

        if (!route.isEmpty() && route.peekLast() == start_idx) {
            route.pollLast();
        }

        stk.push(start_idx);

        while(!stk.isEmpty()) {
            route.offer(stk.pop());
        }
    }
}