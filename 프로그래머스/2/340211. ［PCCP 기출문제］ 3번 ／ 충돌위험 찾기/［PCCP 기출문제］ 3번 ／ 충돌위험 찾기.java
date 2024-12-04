import java.util.*;

class Solution {
    
    static final int R = 100;
    static final int C = 100;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] points, int[][] routes) {
        
        int n = points.length;        
        int[] point = new int[n];
        
        for(int i = 0; i < n; i++) {
            int r = points[i][0] - 1;
            int c = points[i][1] - 1;
            
            point[i] = r * C + c;
        }
        
        int m = routes.length;
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

        while (flag) {

            flag = false;
            HashMap<Integer, Integer> map = new HashMap<>();            

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

        boolean[] visit = new boolean[R * C];
        visit[start_idx] = true;

        Queue<Integer> qu = new ArrayDeque<>();
        qu.offer(start_idx);

        //최단 경로를 추적하기 위한 배열
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
                prev[next] = now;
                qu.offer(next);
            }
        }

        int idx = target_idx;

        Deque<Integer> stk = new ArrayDeque<>();

        //도착지부터 역으로 왔던 경로대로 스택에 저장
        while (idx != start_idx) {
            stk.push(idx);
            idx = prev[idx];
        }

        //출발 지점이 겹치는 경우 제거
        if (!route.isEmpty() && route.peekLast() == start_idx) {
            route.pollLast();
        }

        //출발 지점도 스택에 저장
        stk.push(start_idx);

        //이동 순서대로 큐에 저장
        while(!stk.isEmpty()) {
            route.offer(stk.pop());
        }
    }
}