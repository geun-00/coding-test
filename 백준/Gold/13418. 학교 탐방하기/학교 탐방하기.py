import heapq
import sys

input = sys.stdin.readline


def kruskal(n, start, edges):
    parent = list(range(n))

    def find(a):
        if parent[a] == a:
            return a
        parent[a] = find(parent[a])
        return parent[a]

    def union(a, b):
        a = find(a)
        b = find(b)
        if a != b:
            parent[b] = a

    count = start

    while edges:
        w, a, b = heapq.heappop(edges)

        if find(a) != find(b):
            union(a, b)
            if w == 0:
                count += 1

    return count * count


n, m = map(int, input().split())
t1, t2, start = map(int, input().split())
start = 1 - start

min_edges = []
max_edges = []

for _ in range(m):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1

    heapq.heappush(min_edges, (-c, a, b))
    heapq.heappush(max_edges, (c, a, b))

min_res = kruskal(n, start, min_edges)
max_res = kruskal(n, start, max_edges)

print(max_res - min_res)