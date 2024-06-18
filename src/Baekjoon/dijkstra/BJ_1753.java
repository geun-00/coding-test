package Baekjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href = "https://www.acmicpc.net/problem/1753">백준 1753번 - 다익스트라 : 최단경로</a>
 * <br>
 * <a href = "https://velog.io/@jky00914/%EB%B0%B1%EC%A4%80-1753%EB%B2%88-%EC%B5%9C%EB%8B%A8%EA%B2%BD%EB%A1%9C">velog</a>
 * @since 2024-06-18
 */
public class BJ_1753 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); //정점 개수
        int E = Integer.parseInt(st.nextToken()); //간선 개수

        int k = Integer.parseInt(br.readLine()); //시작 정점

        ArrayList<Node>[] graph = new ArrayList[V + 1]; //방향 그래프
        boolean[] visit = new boolean[V + 1]; //방문 처리 배열
        int[] dist = new int[V + 1]; //거리 저장 배열

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE; //거리 배열 초기 큰수로 초기화
        }

        for (int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w)); //u에서 v로 가중치 w만큼 노드 저장
        }

        Queue<Node> qu = new PriorityQueue<>(); //가중치가 가장 작은 노드부터 처리하기 위해 우선순위 큐 사용
        qu.offer(new Node(k, 0)); //최초 시작 노드 번호 k와 가중치 0 에서 시작

        dist[k] = 0;

        while (!qu.isEmpty()) {
            Node now = qu.poll();

            if (visit[now.adj]) {
                continue;
            }
            visit[now.adj] = true;

            for (Node next : graph[now.adj]) { //인접 노드 순회

                //인접 노드 거리보다 현재 노드 + 에지 가중치가 더 작으면 업데이트
                if (dist[next.adj] > dist[now.adj] + next.w) {

                    dist[next.adj] = dist[now.adj] + next.w;
                    qu.offer(new Node(next.adj, dist[next.adj]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= V; i++) {
            sb.append(visit[i] ? dist[i] : "INF").append("\n"); //방문처리가 되지 않았으면 경로가 존재하지 않는다.
        }

        System.out.println(sb);
    }

    /**
     * 우선순위 큐에 저장할 노드 클래스
     */
    static class Node implements Comparable<Node> {

        int adj, w; //인접 노드, 가중치

        public Node(int adj, int w) {
            this.adj = adj;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) { //거리 오름차순 정렬
            return this.w - o.w;
        }
    }
}