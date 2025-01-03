import sys
from heapq import heappush, heappop

input = sys.stdin.readline

n, m, k = map(int, input().split())
parent = [i for i in range(0, n)]

for num in map(int, input().split()):
    parent[num - 1] = -1

qu = []
for _ in range(m):
    u, v, w = map(int, input().split())
    heappush(qu, (w, u - 1, v - 1))


def find(a):
    if parent[a] == -1:
        return -1
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


def union(a, b):
    a = find(a)
    b = find(b)

    if a != b:
        if a == -1:
            parent[b] = -1
        elif b == -1:
            parent[a] = -1
        else:
            parent[b] = a
                    

ans = 0

while qu:
    cost, u, v = heappop(qu)

    if find(u) != find(v):
        union(u, v)
        ans += cost

print(ans)
